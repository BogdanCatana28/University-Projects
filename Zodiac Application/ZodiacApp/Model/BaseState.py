import pynecone as pc


class User(pc.Model, table=True):
    """A table of Users."""

    username: str
    password: str
    birthday: str
    zodiac_sign : str

class Love(pc.Model, table=True):
    sign: str
    comp_sign: str
    rate: int

class State(pc.State):
    """The base state for the app."""

    username: str
    logged_in: bool = False