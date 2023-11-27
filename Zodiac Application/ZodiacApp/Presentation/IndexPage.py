import pynecone as pc


def indexPage():
    return pc.center(
        pc.vstack(
            pc.heading("Welcome to the perfect place to find out about your Zodiac Sign!", font_size="3em",
                    background_image="linear-gradient(271.68deg, #ffffff 0.75%, #FFFF00 88.52%)",
                    background_clip="text",
                    font_weight="bold", ),
            pc.heading("Take a look at what we offer", font_size="2em",
                       background_image="linear-gradient(271.68deg, #ffffff 0.75%, #FFFF00 88.52%)",
                       background_clip="text",
                       font_weight="bold", ),
            pc.hstack(
                pc.text(
                    "See your horoscope for today", font_size="1.5em",
                    background_image="linear-gradient(271.68deg, #ffffff 0.75%, #FFFF00 88.52%)",
                    background_clip="text",
                    font_weight="bold", text_align="center",
                    variant="outline",  border_radius="1em", border_width="medium", border_color="white", width="8em", height="6.5em"
                ),
                pc.text(
                    "Did you miss a day? We got u covered!", font_size="1.5em",
                    background_image="linear-gradient(271.68deg, #ffffff 0.75%, #FFFF00 88.52%)",
                    background_clip="text",
                    font_weight="bold", text_align="center",
                    variant="outline",  border_radius="1em", border_width="medium", border_color="white", width="8em", height="6.5em"
                ),
                pc.text(
                    "Interested in other Zodiac Signs? We got this too!", font_size="1.5em",
                    background_image="linear-gradient(271.68deg, #ffffff 0.75%, #FFFF00 88.52%)",
                    background_clip="text",
                    font_weight="bold", text_align="center",
                    variant="outline",  border_radius="1em", border_width="medium", border_color="white", width="8em", height="6.5em"
                ),
                pc.text(
                    "You just care about your love life? Check your compatibilities!", font_size="1.5em",
                    background_image="linear-gradient(271.68deg, #ffffff 0.75%, #FFFF00 88.52%)",
                    background_clip="text",
                    font_weight="bold", text_align="center",
                    variant="outline",  border_radius="1em", border_width="medium", border_color="white", width="8em", height="6.5em"
                ),
            ),
            pc.text("Get into your account to start exploring! Or go ahead and create one!", font_size="1em",
                    background_image="linear-gradient(271.68deg, #ffffff 0.75%, #FFFF00 88.52%)",
                    background_clip="text", as_i="i"),
            pc.flex(
                pc.link(
                    pc.button(
                        "Login",
                        variant="outline",
                        border_radius="1em",
                        bg="white",
                    ),
                    href="/login",
                ),
                pc.spacer(),
                pc.text(""),
                pc.spacer(),
                pc.link(
                    pc.button(
                        "Sign Up",
                        variant="outline",
                        border_radius="1em",
                        bg="white",
                    ),
                    href="/signup",
                ),
            ),
        ),
        width="100%",
        height="100vh",
        background="radial-gradient(circle at 22% 11%, rgba(0, 0, 0, 0.2), hsla(0, 0%, 100%, 0) 19%), radial-gradient(circle at 82% 25%, rgba(0, 0, 0, 0.18), hsla(0, 0%, 100%, 0) 35%), radial-gradient(circle at 25% 61%, rgba(0, 0, 0, 0.28), hsla(0, 0%, 100%, 0) 55%), radial-gradient(circle at 52% 48%, #1f1f6e, hsla(0, 0%, 100%, 0) 65%), radial-gradient(circle at 75% 18%, #ffffff, hsla(0, 0%, 100%, 0) 40%), radial-gradient(circle at 45% 75%, #1f1f6e, hsla(0, 0%, 100%, 0) 70%)"    )