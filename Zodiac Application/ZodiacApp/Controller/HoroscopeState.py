import pynecone as pc
import requests
from bs4 import BeautifulSoup
import re
from ..Model import BaseState

class HoroscopeState(BaseState.State):
    """The app state."""

    day: str
    sign: str
    text : str = "Loading horoscope..."
    color_scheme : str

    @pc.var
    def get_horoscope(self) -> str:
        request_day = 'today'
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

        if self.day and self.day == "Today":
            request_day = 'today'
        if self.day and self.day == "Yesterday":
            request_day = 'yesterday'
        if self.day and self.day == "Tomorrow":
            request_day = 'tomorrow'

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
    def zodiac_colors(self):

        if not self.sign:
            self.color_scheme = "linear-gradient(271.68deg, #ffffff 0.75%, #ffff00 88.52%)"
        elif self.sign == "Aries":
            self.color_scheme = "linear-gradient(271.68deg, #ff0000 0.75%, #ffa500 88.52%)"
        elif self.sign == "Taurus":
            self.color_scheme = "linear-gradient(271.68deg, #00ff00 0.75%, #ffc0cb 88.52%)"
        elif self.sign == "Gemini":
            self.color_scheme = "linear-gradient(271.68deg, #ffff00 0.75%, #0000ff 88.52%)"
        elif self.sign == "Cancer":
            self.color_scheme = "linear-gradient(271.68deg, #c0c0c0 0.75%, #ffffff 88.52%)"
        elif self.sign == "Leo":
            self.color_scheme = "linear-gradient(271.68deg, #ffd700 0.75%, #800080 88.52%)"
        elif self.sign == "Virgo":
            self.color_scheme = "linear-gradient(271.68deg, #a52a2a 0.75%, #00ff00 88.52%)"
        elif self.sign == "Libra":
            self.color_scheme = "linear-gradient(271.68deg, #ffc0cb 0.75%, #add8e6 88.52%)"
        elif self.sign == "Scorpio":
            self.color_scheme = "linear-gradient(271.68deg, #000000 0.75%, #ff0000 88.52%)"
        elif self.sign == "Sagittarius":
            self.color_scheme = "linear-gradient(271.68deg, #800080 0.75%, #00008b 88.52%)"
        elif self.sign == "Capricorn":
            self.color_scheme = "linear-gradient(271.68deg, #808080 0.75%, #a52a2a 88.52%)"
        elif self.sign == "Aquarius":
            self.color_scheme = "linear-gradient(271.68deg, #0000ff 0.75%, #30d5c8 88.52%)"
        else:
            self.color_scheme = "linear-gradient(271.68deg, #90ee90 0.75%, #7fffd4 88.52%)"

        return self.color_scheme
