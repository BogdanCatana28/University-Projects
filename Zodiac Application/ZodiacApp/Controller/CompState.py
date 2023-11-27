import pandas as pd
import pynecone as pc
import plotly.graph_objects as go
from ..Model import BaseState

class CompState(BaseState.State):
    """The app state."""

    sign: str

    @pc.var
    def get_data(self) -> pd.DataFrame:
        if not self.sign:
            return None

        result = pc.session().query(BaseState.Love.comp_sign, BaseState.Love.rate).filter(
            BaseState.Love.sign.contains(self.sign)
        )
        return pd.DataFrame(result, columns=['Sign', 'Compatibility'])

    @pc.var
    def gen_chart(self) -> go.Figure:
        data = self.get_data
        if data is None or data.empty:
            return go.Figure()

        fig = go.Figure(data=[go.Pie(labels=data['Sign'], values=data['Compatibility'])])
        return fig
