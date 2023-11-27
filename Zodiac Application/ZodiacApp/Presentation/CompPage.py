import pynecone as pc
from ..Controller import CompState

def compPage():
    return pc.center(
        pc.vstack(
            pc.heading("Let's see how do you get along with the others!", font_size="3em",
                    background_image = "linear-gradient(271.68deg, #ffffff 0.75%, #FFFF00 88.52%)",
                    background_clip = "text",
                    font_weight = "bold",
                    text_align="center"),
            pc.vstack(
                pc.text("Choose a zodiac sign to get started:",
                        background_image="linear-gradient(271.68deg, #ffffff 0.75%, #FFFF00 88.52%)",
                        background_clip="text",
                        font_weight="bold", ),
                pc.select(
                    ["Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius",
                     "Capricorn", "Aquarius", "Pisces"],
                    placeholder="Select a zodiac sign.",
                    bg="white",
                    on_change=CompState.CompState.set_sign
                ),
                pc.plotly(data=CompState.CompState.gen_chart, layout={"width": "550", "height": "550"}),
                padding="2em",
                shadow="lg",
                border_radius="lg",
            ),
            width="60%",
        ),
        width="100%",
        height="100vh",
        background="radial-gradient(circle at 22% 11%, rgba(0, 0, 0, 0.2), hsla(0, 0%, 100%, 0) 19%), radial-gradient(circle at 82% 25%, rgba(0, 0, 0, 0.18), hsla(0, 0%, 100%, 0) 35%), radial-gradient(circle at 25% 61%, rgba(0, 0, 0, 0.28), hsla(0, 0%, 100%, 0) 55%), radial-gradient(circle at 52% 48%, #1f1f6e, hsla(0, 0%, 100%, 0) 65%), radial-gradient(circle at 75% 18%, #ffffff, hsla(0, 0%, 100%, 0) 40%), radial-gradient(circle at 45% 75%, #1f1f6e, hsla(0, 0%, 100%, 0) 70%)" )