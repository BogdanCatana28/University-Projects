import pynecone as pc
from ..Model import BaseState
from ..Controller import AuthState


def loginPage():
    return pc.center(
        pc.vstack(
            pc.heading("Get straight into action!", font_size="3em",
                    background_image="linear-gradient(271.68deg, #ffffff 0.75%, #FFFF00 88.52%)",
                    background_clip="text",
                    font_weight="bold",),
            pc.vstack(
                pc.input(placeholder="Username", on_blur=BaseState.State.set_username, bg="white"),
                pc.input(placeholder="Password", on_blur=AuthState.AuthState.set_password, type_="password", bg="white"),
                pc.button(
                    "Login",
                    variant="outline",
                    border_radius="1em",
                    on_click=AuthState.AuthState.login,
                    bg="white",
                ),
                padding="2em",
                shadow="lg",
                border_radius="lg",
            ),
        ),
        width="100%",
        height="100vh",
        background="radial-gradient(circle at 22% 11%, rgba(0, 0, 0, 0.2), hsla(0, 0%, 100%, 0) 19%), radial-gradient(circle at 82% 25%, rgba(0, 0, 0, 0.18), hsla(0, 0%, 100%, 0) 35%), radial-gradient(circle at 25% 61%, rgba(0, 0, 0, 0.28), hsla(0, 0%, 100%, 0) 55%), radial-gradient(circle at 52% 48%, #1f1f6e, hsla(0, 0%, 100%, 0) 65%), radial-gradient(circle at 75% 18%, #ffffff, hsla(0, 0%, 100%, 0) 40%), radial-gradient(circle at 45% 75%, #1f1f6e, hsla(0, 0%, 100%, 0) 70%)"    )