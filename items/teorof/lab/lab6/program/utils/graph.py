import networkx as nx
import matplotlib.pyplot as plt


class Graph:
    def __init__(
            self,
            with_labels=True,
            node_size=500,
            node_color='orange',
            font_size=12,
            font_color='black',
            font_weight='bold',
            width=1,
            edge_color='black',
            arrowsize=20,
            arrowstyle='->',
            arrows=True,
            connectionstyle='arc3,rad=0.1',
            alpha=0.6,
            label_font_size=15,
            label_font_color='black',
            label_font_weight='bold',
            label_rotate=True,
            label_verticalalignment='bottom',
    ):
        self._with_labels = with_labels
        self._node_size = node_size
        self._node_color = node_color
        self._font_size = font_size
        self._font_color = font_color
        self._font_weight = font_weight
        self._width = width
        self._edge_color = edge_color
        self._arrowsize = arrowsize
        self._arrowstyle = arrowstyle
        self._arrows = arrows
        self._connectionstyle = connectionstyle
        self._alpha = alpha
        self._label_font_size = label_font_size
        self._label_font_color = label_font_color
        self._label_font_weight = label_font_weight
        self._label_rotate = label_rotate
        self._label_verticalalignment = label_verticalalignment

        self._G = nx.DiGraph()
        self._pos = None

        self._node_style = {}

    def add_edge(self, from_vertex: str, to_vertex: str, label: str = None) -> None:
        if not label:
            self._G.add_edge(from_vertex, to_vertex)
            return

        labels = nx.get_edge_attributes(self._G, 'label')
        if (from_vertex, to_vertex) in labels:
            labels[(from_vertex, to_vertex)] += f", {label}"
        self._G.add_edge(from_vertex, to_vertex, label=label)
        nx.set_edge_attributes(self._G, labels, 'label')

    def style_node(self, node: str, **kwargs) -> None:
        """
        Раскраска вершин

        Например: style_node('S1', node_color='red', node_size=1000)

        :param node:
        :param kwargs:
        :return:
        """
        self._node_style[node] = kwargs

    def draw(self) -> None:
        self._pos = nx.spring_layout(self._G)

        nx.draw(
            self._G,
            self._pos,
            with_labels=self._with_labels,
            node_size=self._node_size,
            node_color=self._node_color,
            font_size=self._font_size,
            font_color=self._font_color,
            font_weight=self._font_weight,
            width=self._width,
            edge_color=self._edge_color,
            arrowsize=self._arrowsize,
            arrowstyle=self._arrowstyle,
            arrows=self._arrows,
            connectionstyle=self._connectionstyle,
            alpha=self._alpha,
        )
        nx.draw_networkx_edge_labels(
            self._G,
            self._pos,
            edge_labels=nx.get_edge_attributes(self._G, 'label'),
            font_size=self._label_font_size,
            font_color=self._label_font_color,
            font_weight=self._label_font_weight,
            rotate=self._label_rotate,
            verticalalignment=self._label_verticalalignment,
        )
        for node, style in self._node_style.items():
            nx.draw_networkx_nodes(
                self._G,
                self._pos,
                nodelist=[node],
                **style,
            )
        plt.show()