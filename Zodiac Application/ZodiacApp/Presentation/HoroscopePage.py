import pynecone as pc
from ..Controller import HoroscopeState


def horoscopePage():
    return pc.center(
        pc.vstack(
            pc.heading("Take a glimpse into the astrological forecast of your zodiac!", font_size="3em",
                    background_image = "linear-gradient(271.68deg, #ffffff 0.75%, #FFFF00 88.52%)",
                    background_clip = "text",
                    font_weight = "bold",
                    text_align="center"),
            pc.vstack(
                pc.select(
                    ["Yesterday", "Today", "Tomorrow"],
                    placeholder="Select a day.",
                    bg="white",
                    on_change=HoroscopeState.HoroscopeState.set_day
                ),
                pc.hstack(
                    pc.text("Want to see how the other zodiac signs do today? Select the one of your choice:",
                            background_image="linear-gradient(271.68deg, #ffffff 0.75%, #FFFF00 88.52%)",
                            background_clip="text",
                            font_weight="bold",),
                    pc.select(
                        ["Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"],
                        placeholder="Select a sign.",
                        bg="white",
                        on_change=HoroscopeState.HoroscopeState.set_sign
                    ),
                ),
                pc.text(HoroscopeState.HoroscopeState.get_horoscope, font_size="1.5em",
                        background_image=HoroscopeState.HoroscopeState.zodiac_colors,
                        background_clip="text",
                        font_weight="bold",
                        text_align="center",
                        ),
                pc.text("Check our compatibiliy feature as well below!", font_size="1em", as_i="i",
                        background_image="linear-gradient(271.68deg, #ffffff 0.75%, #FFFF00 88.52%)",
                        background_clip="text",
                        font_weight="bold",
                        ),
                pc.hstack(
                    pc.link(
                        pc.button(
                            "Go to..",
                            variant="outline",
                            border_radius="1em",
                            bg="white",
                        ),
                        href="/comp"
                    ),
                ),
                padding="2em",
                shadow="lg",
                border_radius="lg",
            ),
            width="60%",
        ),
        width="100%",
        height="100vh",
        background="radial-gradient(circle at 22% 11%, rgba(0, 0, 0, 0.2), hsla(0, 0%, 100%, 0) 19%), radial-gradient(circle at 82% 25%, rgba(0, 0, 0, 0.18), hsla(0, 0%, 100%, 0) 35%), radial-gradient(circle at 25% 61%, rgba(0, 0, 0, 0.28), hsla(0, 0%, 100%, 0) 55%), radial-gradient(circle at 52% 48%, #1f1f6e, hsla(0, 0%, 100%, 0) 65%), radial-gradient(circle at 75% 18%, #ffffff, hsla(0, 0%, 100%, 0) 40%), radial-gradient(circle at 45% 75%, #1f1f6e, hsla(0, 0%, 100%, 0) 70%)" )