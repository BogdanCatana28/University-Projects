import itertools as it
import plotly.graph_objects as go
import pandas as pd

lst = [2, 5, 5, 3, 1, 5]

i = 0
while i < len(lst) - 1:
    if (lst[i] < 4 and lst[i + 1] >= 4) or (lst[i] >= 4 and lst[i + 1] < 4):
        lst.insert(i + 1, 4)
        i += 1
    i += 1


print(lst)
data = pd.DataFrame({'Duration': lst, 'Dream': range(len(lst))})
xcol = data['Dream']
ycol = data['Duration']
x_pairs = it.pairwise(xcol)
y_pairs = it.pairwise(ycol)
colors = ['red' if any([i < 4 for i in y_values]) else 'blue' for y_values in it.pairwise(ycol)]
fig = go.Figure()
for x, y, color in zip(x_pairs, y_pairs, colors):
    fig.add_trace(
        go.Scatter(
            x=x,
            y=y,
            mode='lines+markers',
            line={'color': color},
            hovertemplate='<b>Dream</b>: %{x}<br>'
        )
    )
fig.update_layout(showlegend=False)
fig.show()
