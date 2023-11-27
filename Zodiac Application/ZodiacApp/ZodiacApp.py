import pynecone as pc
from .Model import BaseState
from .Presentation import LoginPage, RegisterPage, HoroscopePage, IndexPage, CompPage


# Add state and page to the app.
app = pc.App(state=BaseState.State)
app.add_page(IndexPage.indexPage, route="/", title="Your Zodiac App")
app.add_page(LoginPage.loginPage, route="/login", title="Your Zodiac App")
app.add_page(RegisterPage.signUpPage, route="/signup", title="Your Zodiac App")
app.add_page(HoroscopePage.horoscopePage, route="/horoscope", title="Your Zodiac App")
app.add_page(CompPage.compPage, route="/comp", title="Your Zodiac App")
app.compile()