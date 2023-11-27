import re

import pynecone as pc
from ..Model import BaseState

pattern = r"\d{2}/\d{2}/\d{4}"

class AuthState(BaseState.State):
    password: str
    confirm_password: str
    birthday : str

    def signup(self):
        """Sign up a user."""
        with pc.session() as session:
            if self.password != self.confirm_password:
                return pc.window_alert("Passwords do not match.")
            if session.exec(BaseState.User.select.where(BaseState.User.username == self.username)).first():
                return pc.window_alert("Username already exists.")
            if self.password == "" or self.username == "" or self.confirm_password == "":
                return pc.window_alert("Sign up fields cannot be empty!")
            if not re.match(pattern, self.birthday):
                return pc.window_alert("Birthday date is not correct!")
            day, month, year = map(int, self.birthday.split('/'))
            if day < 1 or day > 31 or month < 1 or month > 12:
                return pc.window_alert("Birthday date is not correct!")
            get_date = [int(s) for s in str(self.birthday).split("/") if s.isdigit()]
            day = get_date[0]
            month = get_date[1]
            if (day >= 21 and month == 3) or (day <= 19 and month == 4):
                zodiac = "Aries"
            elif (day >= 20 and month == 4) or (day <= 20 and month == 5):
                zodiac = "Taurus"
            elif (day >= 21 and month == 5) or (day <= 20 and month == 6):
                zodiac = "Gemini"
            elif (day >= 21 and month == 6) or (day <= 22 and month == 7):
                zodiac = "Cancer"
            elif (day >= 23 and month == 7) or (day <= 23 and month == 8):
                zodiac = "Leo"
            elif (day >= 23 and month == 8) or (day <= 22 and month == 9):
                zodiac = "Virgo"
            elif (day >= 23 and month == 9) or (day <= 22 and month == 10):
                zodiac = "Libra"
            elif (day >= 23 and month == 10) or (day <= 21 and month == 11):
                zodiac = "Scorpio"
            elif (day >= 22 and month == 11) or (day <= 21 and month == 21):
                zodiac = "Sagittarius"
            elif (day >= 22 and month == 12) or (day <= 19 and month == 1):
                zodiac = "Capricorn"
            elif (day >= 20 and month == 1) or (day <= 18 and month == 2):
                zodiac = "Aquarius"
            else:
                zodiac = "Pisces"

            user = BaseState.User(username=self.username, password=self.password, birthday=self.birthday, zodiac_sign=zodiac)
            session.add(user)
            session.commit()
            self.logged_in = True
            return pc.redirect("/horoscope")

    def login(self):
        """Log in a user."""

        with pc.session() as session:
            user = session.exec(
                BaseState.User.select.where(BaseState.User.username == self.username)
            ).first()
            if user and user.password == self.password:
                self.logged_in = True
                return pc.redirect("/horoscope")
            elif self.username == "" or self.password == "":
                return pc.window_alert("Please fill your login credentials!")
            else:
                return pc.window_alert("Invalid username or password!")

