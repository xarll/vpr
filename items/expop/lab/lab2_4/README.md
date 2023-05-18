# Лабораторная работа №4. Задача о максимальном потоке.

Необходимо придумать для себя два (?) графа (ориентированный и неориентированный) (?), с количествами вершин, большими шести, произвести поиск на графах согласно своему варианту и разработать программное средство для реализации этого поиска.


## Варианты заданий:
Номер Вашего варианта совпадает с Вашим порядковым номером в списке группы. Алгоритм на реализацию определяется следующим образом:

- Алгоритм Форда-Фалкерсона - варианты №1, №5, №10, ...

- Алгоритм Эдмондса-Карпа - варианты №2, №7, №12, ... 

- Алгоритм Диница - варианты №3, №8, №13, ...

- Алгоритм проталкивания предпотока - варианты №4, №9, №14, ...

- Алгоритм "поднять-в-начало" - варианты №5, №10, №15, ...



## Реализация алгоритма Эдмондса-Карпа

### Ручная
<details>

  <summary>Фото</summary>
  
  ![1]()

  ![2]()
  
</details>




### Программный код (Прокопенко)
Для работы программы потребуется:

- Python 3.11:
  - `networkx`
  - `matplotlib`


<details>
  <summary>main.py</summary>

```python
import graph, utils


if __name__ == '__main__':
    # Вставьте свой неориентированный граф, представленный матрицей пропускных способностей:
    C = {
        1: {1: 0, 2: 3, 3: 0, 4: 0, 5: 0, 6: 5, 7: 0, 8: 0, 9: 0, 10: 0},
        2: {1: 3, 2: 0, 3: 2, 4: 0, 5: 0, 6: 0, 7: 1, 8: 4, 9: 0, 10: 0},
        3: {1: 0, 2: 2, 3: 0, 4: 0, 5: 0, 6: 0, 7: 0, 8: 10, 9: 0, 10: 0},
        4: {1: 0, 2: 0, 3: 0, 4: 0, 5: 7, 6: 0, 7: 0, 8: 1, 9: 9, 10: 1},
        5: {1: 0, 2: 0, 3: 0, 4: 7, 5: 0, 6: 0, 7: 0, 8: 0, 9: 0, 10: 12},
        6: {1: 5, 2: 0, 3: 0, 4: 0, 5: 0, 6: 0, 7: 4, 8: 0, 9: 0, 10: 0},
        7: {1: 0, 2: 1, 3: 0, 4: 0, 5: 0, 6: 4, 7: 0, 8: 5, 9: 0, 10: 0},
        8: {1: 0, 2: 4, 3: 10, 4: 1, 5: 0, 6: 0, 7: 5, 8: 0, 9: 3, 10: 0},
        9: {1: 0, 2: 0, 3: 0, 4: 9, 5: 0, 6: 0, 7: 0, 8: 3, 9: 0, 10: 0},
        10: {1: 0, 2: 0, 3: 0, 4: 1, 5: 12, 6: 0, 7: 0, 8: 0, 9: 0, 10: 0}
    }

    G = graph.UndirectedGraph(C)
    G.draw()

    # Вставьте свой неориентированный граф, представленный матрицей пропускных способностей:
    N, info = G.edmonds_karp_max_flow(1, 10)
    utils.print_info(info)
    N.draw()


    print()

    # Вставьте свой ориентированный граф, представленный матрицей пропускных способностей:
    C = {
        1: {1: 0, 2: 3, 3: 0, 4: 0, 5: 0, 6: 5, 7: 0, 8: 0, 9: 0, 10: 0},
        2: {1: 0, 2: 0, 3: 2, 4: 0, 5: 0, 6: 0, 7: 1, 8: 0, 9: 0, 10: 0},
        3: {1: 0, 2: 0, 0: 0, 4: 0, 5: 0, 6: 0, 7: 0, 8: 10, 9: 0, 10: 0},
        4: {1: 0, 2: 0, 3: 0, 4: 0, 5: 7, 6: 0, 7: 0, 8: 1, 9: 0, 10: 1},
        5: {1: 0, 2: 0, 3: 0, 4: 0, 5: 0, 6: 0, 7: 0, 8: 0, 9: 0, 10: 12},
        6: {1: 0, 2: 0, 3: 0, 4: 0, 5: 0, 6: 0, 7: 4, 8: 0, 9: 0, 10: 0},
        7: {1: 0, 2: 0, 3: 0, 4: 0, 5: 0, 6: 0, 7: 0, 8: 5, 9: 0, 10: 0},
        8: {1: 0, 2: 4, 3: 0, 4: 0, 5: 0, 6: 0, 7: 0, 8: 0, 9: 3, 10: 0},
        9: {1: 0, 2: 0, 3: 0, 4: 9, 5: 0, 6: 0, 7: 0, 8: 0, 9: 0, 10: 0},
        10: {1: 0, 2: 0, 3: 0, 4: 0, 5: 0, 6: 0, 7: 0, 8: 0, 9: 0, 10: 0}
    }

    G = graph.DirectedGraph(C)
    G.draw()

    # Параметрами метода edmonds_karp_max_flow можно указать вершины, которые будут источником и стоком потока:
    N, info = G.edmonds_karp_max_flow(1, 10)
    utils.print_info(info)
    N.draw()

```

</details>


<details>
  <summary>graph.py</summary>

```python
import random

import matplotlib.pyplot as plt
import networkx as nx

import utils


class UndirectedGraph:
    def __init__(
            self,
            capacity_matrix: dict[int or str: dict[int or str: int]],
            with_labels=True,
            node_size=500,
            node_color='gray',
            font_size=12,
            font_color='black',
            font_weight='bold',
            width=1,
            edge_color='black',
            alpha=1,
            label_font_size=8,
            label_font_color='black',
            label_font_weight='bold',
            label_rotate=True,
            label_verticalalignment='bottom'
    ) -> None:
        self._capacity_matrix = capacity_matrix.copy()
        self._with_labels = with_labels
        self._node_size = node_size
        self._node_color = node_color
        self._font_size = font_size
        self._font_color = font_color
        self._font_weight = font_weight
        self._width = width
        self._edge_color = edge_color
        self._alpha = alpha
        self._label_font_size = label_font_size
        self._label_font_color = label_font_color
        self._label_font_weight = label_font_weight
        self._label_rotate = label_rotate
        self._label_verticalalignment = label_verticalalignment

        self._graph = nx.Graph()
        self._pos = None

        self._node_style = {}

        for node in self._capacity_matrix.keys():
            self._graph.add_node(node)

        for node_source in self._capacity_matrix.keys():
            for node_destination in self._capacity_matrix[node_source]:
                if self._capacity_matrix[node_source][node_destination] == 0:
                    continue

                self._graph.add_edge(
                    node_source,
                    node_destination,
                    capacity=str(self._capacity_matrix[node_source][node_destination])
                )

    @property
    def capacity_matrix(self) -> dict[int or str: dict[int or str: int]]:
        return self._capacity_matrix.copy()

    @capacity_matrix.setter
    def capacity_matrix(self, capacity_matrix: dict[int or str: dict[int or str: int]], ) -> None:
        self._capacity_matrix = capacity_matrix.copy()
        self._graph.clear()

        for node in self._capacity_matrix.keys():
            self._graph.add_node(node)

        for node_source in self._capacity_matrix.keys():
            for node_destination in self._capacity_matrix[node_source]:
                if self._capacity_matrix[node_source][node_destination] == 0:
                    continue

                self._graph.add_edge(
                    node_source,
                    node_destination,
                    capacity=str(self._capacity_matrix[node_source][node_destination])
                )

    @property
    def nodes(self):
        return sorted(list(self._graph.nodes).copy())

    def copy(self):
        return UndirectedGraph(self._capacity_matrix)

    def clear(self) -> None:
        self._graph.clear()
        self._capacity_matrix.clear()

    def draw(self) -> None:
        self._pos = nx.spring_layout(self._graph)

        nx.draw(
            self._graph,
            self._pos,
            with_labels=self._with_labels,
            node_size=self._node_size,
            node_color=self._node_color,
            font_size=self._font_size,
            font_color=self._font_color,
            font_weight=self._font_weight,
            width=self._width,
            edge_color=self._edge_color,
            alpha=self._alpha,
        )

        nx.draw_networkx_edge_labels(
            self._graph,
            self._pos,
            edge_labels=nx.get_edge_attributes(self._graph, 'capacity'),
            font_size=self._label_font_size,
            font_color=self._label_font_color,
            font_weight=self._label_font_weight,
            rotate=self._label_rotate,
            verticalalignment=self._label_verticalalignment,
        )

        for node, style in self._node_style.items():
            nx.draw_networkx_nodes(
                self._graph,
                nodelist=[node],
                **style,
            )
        plt.show()

    def _width_search(self, flow_matrix: dict[int or str: dict[int or str: int]], start_node: int or str,
                      end_node: int or str):

        queue = utils.Queue()

        paths = {start_node: []}

        queue.insert(start_node)

        if start_node == end_node:
            return paths[start_node]

        while not queue.empty():
            source_node = queue.pop()
            for neighbour in self._capacity_matrix[source_node]:
                if neighbour not in paths and \
                        self._capacity_matrix[source_node][neighbour] - flow_matrix[source_node][neighbour] > 0:
                    paths[neighbour] = paths[source_node] + [(source_node, neighbour)]
                    if neighbour == end_node:
                        return paths[neighbour]
                    queue.insert(neighbour)

        return None

    def edmonds_karp_max_flow(self, source_node: int or str = None, sink_node: int or str = None):
        if source_node and sink_node:
            if source_node not in self.nodes or sink_node not in self.nodes:
                raise Exception('Node not in list')

            if source_node == sink_node:
                raise Exception('Source and sink nodes are the same')

        if not source_node:
            while True:
                source_node = random.choice(self.nodes)
                if source_node != sink_node:
                    break

        if not sink_node:
            while True:
                sink_node = random.choice(self.nodes)
                if source_node != sink_node:
                    break

        if not source_node and sink_node:
            while True:
                source_node = random.choice(self.nodes)
                sink_node = random.choice(self.nodes)
                if source_node != sink_node:
                    break

        flow_matrix = {}
        for node in self.nodes:
            flow_matrix[node] = {}

        for src_node in self._capacity_matrix.keys():
            for dst_node in self._capacity_matrix[src_node]:
                flow_matrix[src_node][dst_node] = 0

        path = self._width_search(flow_matrix, source_node, sink_node)

        while path is not None:
            flow = min(self._capacity_matrix[start_node][neighbour] - flow_matrix[start_node][neighbour] for
                       start_node, neighbour in path)
            for start_node, neighbour in path:
                flow_matrix[start_node][neighbour] += flow
                flow_matrix[neighbour][start_node] -= flow
            path = self._width_search(flow_matrix, source_node, sink_node)

        max_flow_value = sum(flow_matrix[source_node][node] for node in self.nodes)

        capacities = nx.get_edge_attributes(self._graph, 'capacity')
        for src_node in flow_matrix.keys():
            for dst_node in flow_matrix[src_node]:
                if (src_node, dst_node) in capacities:
                    capacities[(src_node, dst_node)] += f'({str(abs(flow_matrix[src_node][dst_node]))})'

        flow_network = self.copy()
        nx.set_edge_attributes(flow_network._graph, capacities, 'capacity')

        flow_matrix['flow'] = max_flow_value
        flow_matrix['node'] = flow_network.nodes
        flow_matrix['source'] = source_node
        flow_matrix['sink'] = sink_node

        return flow_network, flow_matrix.copy()


class DirectedGraph:
    def __init__(
            self,
            capacity_matrix: dict[int or str: dict[int or str: int]],
            with_labels=True,
            node_size=500,
            node_color='gray',
            font_size=12,
            font_color='black',
            font_weight='bold',
            width=1,
            edge_color='black',
            arrows=True,
            alpha=1,
            label_font_size=8,
            label_font_color='black',
            label_font_weight='bold',
            label_rotate=True,
            label_verticalalignment='bottom'
    ) -> None:
        self._capacity_matrix = capacity_matrix.copy()
        self._with_labels = with_labels
        self._node_size = node_size
        self._node_color = node_color
        self._font_size = font_size
        self._font_color = font_color
        self._font_weight = font_weight
        self._width = width
        self._edge_color = edge_color
        self._arrows = arrows
        self._alpha = alpha
        self._label_font_size = label_font_size
        self._label_font_color = label_font_color
        self._label_font_weight = label_font_weight
        self._label_rotate = label_rotate
        self._label_verticalalignment = label_verticalalignment

        self._graph = nx.DiGraph()
        self._pos = None

        self._node_style = {}

        for node in self._capacity_matrix.keys():
            self._graph.add_node(node)

        for node_source in self._capacity_matrix.keys():
            for node_destination in self._capacity_matrix[node_source]:
                if self._capacity_matrix[node_source][node_destination] == 0:
                    continue

                self._graph.add_edge(
                    node_source,
                    node_destination,
                    capacity=str(self._capacity_matrix[node_source][node_destination])
                )

    @property
    def capacity_matrix(self) -> dict[int or str: dict[int or str: int]]:
        return self._capacity_matrix.copy()

    @capacity_matrix.setter
    def capacity_matrix(self, capacity_matrix: dict[int or str: dict[int or str: int]]) -> None:
        self._capacity_matrix = capacity_matrix.copy()
        self._graph.clear()

        for node in self._capacity_matrix.keys():
            self._graph.add_node(node)

        for node_source in self._capacity_matrix.keys():
            for node_destination in self._capacity_matrix[node_source]:
                if self._capacity_matrix[node_source][node_destination] == 0:
                    continue

                self._graph.add_edge(
                    node_source,
                    node_destination,
                    capacity=str(self._capacity_matrix[node_source][node_destination])
                )

    @property
    def nodes(self):
        return sorted(list(self._graph.nodes).copy())

    def copy(self):
        return DirectedGraph(self._capacity_matrix)

    def clear(self) -> None:
        self._graph.clear()
        self._capacity_matrix.clear()

    def draw(self) -> None:
        self._pos = nx.spring_layout(self._graph)

        nx.draw(
            self._graph,
            self._pos,
            with_labels=self._with_labels,
            node_size=self._node_size,
            node_color=self._node_color,
            font_size=self._font_size,
            font_color=self._font_color,
            font_weight=self._font_weight,
            width=self._width,
            edge_color=self._edge_color,
            arrows=self._arrows,
            alpha=self._alpha,
        )

        nx.draw_networkx_edge_labels(
            self._graph,
            self._pos,
            edge_labels=nx.get_edge_attributes(self._graph, 'capacity'),
            font_size=self._label_font_size,
            font_color=self._label_font_color,
            font_weight=self._label_font_weight,
            rotate=self._label_rotate,
            verticalalignment=self._label_verticalalignment,
        )

        for node, style in self._node_style.items():
            nx.draw_networkx_nodes(
                self._graph,
                nodelist=[node],
                **style,
            )
        plt.show()

    def _width_search(self, flow_matrix: dict[int or str: dict[int or str: int]], start_node: int or str,
                      end_node: int or str):

        queue = utils.Queue()

        paths = {start_node: []}

        queue.insert(start_node)

        if start_node == end_node:
            return paths[start_node]

        while not queue.empty():
            source_node = queue.pop()
            for neighbour in self._capacity_matrix[source_node]:
                if neighbour not in paths and \
                        self._capacity_matrix[source_node][neighbour] - flow_matrix[source_node][neighbour] > 0:
                    paths[neighbour] = paths[source_node] + [(source_node, neighbour)]
                    if neighbour == end_node:
                        return paths[neighbour]
                    queue.insert(neighbour)

        return None

    def edmonds_karp_max_flow(self, source_node: int or str = None, sink_node: int or str = None):
        if source_node and sink_node:
            if source_node not in self.nodes or sink_node not in self.nodes:
                raise Exception('Node not in list')

            if source_node == sink_node:
                raise Exception('Source and sink nodes are the same')

        if not source_node:
            while True:
                source_node = random.choice(self.nodes)
                if source_node != sink_node:
                    break

        if not sink_node:
            while True:
                sink_node = random.choice(self.nodes)
                if source_node != sink_node:
                    break

        if not source_node and sink_node:
            while True:
                source_node = random.choice(self.nodes)
                sink_node = random.choice(self.nodes)
                if source_node != sink_node:
                    break

        flow_matrix = {}
        for node in self.nodes:
            flow_matrix[node] = {}

        for src_node in self._capacity_matrix.keys():
            for dst_node in self._capacity_matrix[src_node]:
                flow_matrix[src_node][dst_node] = 0

        path = self._width_search(flow_matrix, source_node, sink_node)

        while path is not None:
            flow = min(self._capacity_matrix[start_node][neighbour] - flow_matrix[start_node][neighbour] for
                       start_node, neighbour in path)
            for start_node, neighbour in path:
                flow_matrix[start_node][neighbour] += flow
                flow_matrix[neighbour][start_node] -= flow
            path = self._width_search(flow_matrix, source_node, sink_node)

        max_flow_value = sum(flow_matrix[source_node][node] for node in self.nodes)

        capacities = nx.get_edge_attributes(self._graph, 'capacity')
        for src_node in flow_matrix.keys():
            for dst_node in flow_matrix[src_node]:
                if (src_node, dst_node) in capacities:
                    capacities[(src_node, dst_node)] += f'({str(flow_matrix[src_node][dst_node])})'

        flow_network = self.copy()
        nx.set_edge_attributes(flow_network._graph, capacities, 'capacity')

        flow_matrix['flow'] = max_flow_value
        flow_matrix['node'] = flow_network.nodes
        flow_matrix['source'] = source_node
        flow_matrix['sink'] = sink_node

        return flow_network, flow_matrix.copy()

```

</details>


<details>
  <summary>utils.py</summary>
  
```python
class QueueIterator:
    def __init__(self, queue):
        self._queue = queue
        self._index = 0

    def __next__(self):
        if self._index < len(self._queue):
            result = self._queue._elements[self._index]
            self._index += 1
            return result

        raise StopIteration


class Queue:
    def __init__(self, elements: list = None) -> None:
        if elements is None:
            self._elements = []
        else:
            self._elements = elements.copy()

    def __len__(self) -> int:
        return len(self._elements)

    def __str__(self) -> str:
        return str(self._elements)

    def __iter__(self):
        return QueueIterator(self)

    def copy(self):
        return Queue(self._elements.copy())

    def insert(self, value: any or list) -> None:
        if isinstance(value, (list, dict)):
            self._elements = value.copy()
        elif isinstance(value, (int, float, str, tuple)):
            self._elements.append(value)

    def pop(self) -> any:
        return self._elements.pop(0)

    def empty(self) -> bool:
        return len(self._elements) == 0

    def clear(self) -> None:
        self._elements.clear()


def print_info(info: dict[int or str: dict[int or str: int]]) -> None:
    print('\t', end='')
    for node in info['node']:
        print(f'{node}     ', end='')

    print()
    print()

    for node_source in info['node']:
        print(node_source, end=' ' * (6 - len(str(node_source))))
        for node_destination in info[node_source]:
            if len(str(info[node_source][node_destination])) == 1:
                print(f'  {str(info[node_source][node_destination])}', end='   ')

            if len(str(info[node_source][node_destination])) == 2:
                print(f' {str(info[node_source][node_destination])}', end='   ')

            if len(str(info[node_source][node_destination])) == 3:
                print(f'{str(info[node_source][node_destination])}', end='   ')

        print()

    print()

    print(f'Source node: {info["source"]}')
    print(f'Sink node: {info["sink"]}')
    print(f'Max flow amount: {info["flow"]}')
  
```

</details>


*При обнаружении каких-либо недочетов и багов **ОБЯЗАТЕЛЬНО И СРОЧНО** писать в ВК: https://vk.com/prokdo*
  

*Авторство: **Прокопенко Д.О.***
