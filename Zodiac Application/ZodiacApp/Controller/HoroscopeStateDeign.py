import pynecone as pc
import requests
from bs4 import BeautifulSoup
import re
from ..Model import BaseState


def writing_color_dec(sign):
    def wrapper(func):
        def inner(*args, **kwargs):
            if not sign:
                color_scheme = "linear-gradient(271.68deg, #ffffff 0.75%, #ffff00 88.52%)"
            elif sign == "Aries":
                color_scheme = "linear-gradient(271.68deg, #ff0000 0.75%, #ffa500 88.52%)"
            elif sign == "Taurus":
                color_scheme = "linear-gradient(271.68deg, #00ff00 0.75%, #ffc0cb 88.52%)"
            elif sign == "Gemini":
                color_scheme = "linear-gradient(271.68deg, #ffff00 0.75%, #0000ff 88.52%)"
            elif sign == "Cancer":
                color_scheme = "linear-gradient(271.68deg, #c0c0c0 0.75%, #ffffff 88.52%)"
            elif sign == "Leo":
                color_scheme = "linear-gradient(271.68deg, #ffd700 0.75%, #800080 88.52%)"
            elif sign == "Virgo":
                color_scheme = "linear-gradient(271.68deg, #a52a2a 0.75%, #00ff00 88.52%)"
            elif sign == "Libra":
                color_scheme = "linear-gradient(271.68deg, #ffc0cb 0.75%, #add8e6 88.52%)"
            elif sign == "Scorpio":
                color_scheme = "linear-gradient(271.68deg, #000000 0.75%, #ff0000 88.52%)"
            elif sign == "Sagittarius":
                color_scheme = "linear-gradient(271.68deg, #800080 0.75%, #00008b 88.52%)"
            elif sign == "Capricorn":
                color_scheme = "linear-gradient(271.68deg, #808080 0.75%, #a52a2a 88.52%)"
            elif sign == "Aquarius":
                color_scheme = "linear-gradient(271.68deg, #0000ff 0.75%, #30d5c8 88.52%)"
            else:
                color_scheme = "linear-gradient(271.68deg, #90ee90 0.75%, #7fffd4 88.52%)"

            return color_scheme
        return inner
    return wrapper


class TodayHoroscope:
    def get_day(self):
        if self.day and self.day == "Today":
            request_day = 'today'

        return request_day


class YesterdayHoroscope:
    def get_day(self):
        if self.day and self.day == "Yesterday":
            request_day = 'yesterday'

        return request_day


class TomorrowHoroscope:
    def get_day(self):
        if self.day and self.day == "Tomorrow":
            request_day = 'tomorrow'

        return request_day


class HoroscopeStateDesign(BaseState.State):
    """The app state."""

    day: str
    sign: str
    text: str = "Loading horoscope..."
    color_scheme: str

    instance = None

    def __new__(cls, *args, **kwargs):
        if not cls.instance:
            cls.instance = super().__new__(cls)
        return cls.instance

    def factory_day(self, day="Today"):
        days = {
            "Today": TodayHoroscope(),
            "Yesterday": YesterdayHoroscope(),
            "Tomorrow": TomorrowHoroscope()
        }
        return days[day]

    def get_day(self):
        pass

    @pc.var
    def get_horoscope(self) -> str:
        factory_day = self.factory_day(str(self.day))
        request_day = factory_day.get_day
        request_sign = self.sign

        zodiac_signs = {
            "Aries": 1,
            "Taurus": 2,
            "Gemini": 3,
            "Cancer": 4,
            "Leo": 5,
            "Virgo": 6,
            "Libra": 7,
            "Scorpio": 8,
            "Sagittarius": 9,
            "Capricorn": 10,
            "Aquarius": 11,
            "Pisces": 12,
        }

        if not request_sign:
            return self.text

        result = requests.get(
            f"https://www.horoscope.com/us/horoscopes/general/horoscope-general-daily-{request_day}.aspx?sign={zodiac_signs[request_sign]}")
        soup = BeautifulSoup(result.content, 'html.parser')
        data = soup.find('div', attrs={'class': 'main-horoscope'})
        horoscope_text = data.p.text
        sentences = re.split(r'(?<!\w\.\w.)(?<![A-Z][a-z]\.)(?<=\.|\?)\s', horoscope_text)

        self.text = ""
        for sentence in sentences:
            self.text = self.text + sentence + "\n"

        return self.text

    @pc.var
    @writing_color_dec(str(sign))
    def zodiac_colors(self):
        return self.color_scheme
