# Лабораторная работа №2. Алгоритмы поиска на графах

Необходимо придумать для себя граф с количеством вершин, большим шести, произвести поиск на графе согласно своему варианту и разработать программное средство для реализации этого поиска.

Преподаватель не уточнял, для какого вида графов (орграф или неоргаф) необходимо выполнять задание, поэтому далее будут представлены решения для обоих видов.


## Варианты заданий:
Вариант №1, №4, №7, ... - Поиск в ширину (ПВШ)

Вариант №2, №5, №8, ... - Поиск в глубину (ПВГ)

Вариант №3, №6, №9, ... - Поиск в глубину (ПВГ) с классификацией дуг


## Реализация поиска в глубину (ПВГ)

### Ручная
<details>
  <summary>Фото</summary>


</details>

### Программный код
Для работы программы потребуется:

- python 3.11
  - `networkx`
  - `matplotlib`


<details>
  <summary>main.py</summary>

```python
import graph


def print_info(info: list[list[int or str]]) -> None:
    for i in range(len(info)):
        if i == 0:
            print('     ', end='')
        elif i == 1:
            print('num: ', end='')
        elif i == 2:
            print('ftr: ', end='')
        elif i == 3:
            print('tn:  ', end='')
        elif i == 4:
            print('tk:  ', end='')

        for element in info[i]:
            if len(str(element)) > 1:
                print(f'{element}', end='  ')
            else:
                print(f' {element}', end='  ')
        print()


if __name__ == '__main__':

    # Вставьте сюда свой граф, представленный списками смежности:
    A = {
        1: [2, 6],
        2: [1, 3, 7, 8],
        3: [2, 8],
        4: [5, 8, 9, 10],
        5: [4, 10],
        6: [1, 7],
        7: [2, 6, 8],
        8: [2, 3, 4, 7, 9],
        9: [4, 8],
        10: [4, 5]
    }

    # Если Ваш граф неориентированный, используйте строку (1);
    # Если ваш граф ориентированный, используйте строку (2):
    G = graph.UndirectedGraph(A)  # (1)
    # G = graph.DirectedGraph(A)  # (2)
    G.draw()

    info, tree = G.depth_search()
  
    tree.draw()
    print_info(info)

```

</details>
<details>
  <summary>graph.py</summary>

```python
import matplotlib.pyplot as plt
import networkx as nx


class UndirectedGraph:
    def __init__(
            self,
            adjacency_matrix: dict[int or str: list[int or str]],
            with_labels=True,
            node_size=1000,
            node_color='gray',
            font_size=12,
            font_color='black',
            font_weight='bold',
            width=1,
            edge_color='black',
            alpha=1
    ) -> None:
        self._adjacency_matrix = adjacency_matrix.copy()
        self._with_labels = with_labels
        self._node_size = node_size
        self._node_color = node_color
        self._font_size = font_size
        self._font_color = font_color
        self._font_weight = font_weight
        self._width = width
        self._edge_color = edge_color
        self._alpha = alpha

        self._graph = nx.Graph()

        self._node_style = {}

        for key in self._adjacency_matrix.keys():
            for node in self._adjacency_matrix[key]:
                self._graph.add_edge(key, node)

    @property
    def adjacency_matrix(self) -> dict[int or str: list[int or str]]:
        return self._adjacency_matrix.copy()

    @adjacency_matrix.setter
    def adjacency_matrix(self, adjacency_matrix: dict[int or str: list[int or str]]) -> None:
        self._adjacency_matrix = adjacency_matrix.copy()
        self._graph.clear()

        for key in self._adjacency_matrix:
            for node in self._adjacency_matrix[key]:
                self._graph.add_edge(key, node)

    @property
    def nodes(self):
        return sorted(list(self._graph.nodes).copy())

    def clear(self) -> None:
        self._graph.clear()
        self._adjacency_matrix.clear()

    def draw(self) -> None:
        nx.draw(
            self._graph,
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
        for node, style in self._node_style.items():
            nx.draw_networkx_nodes(
                self._graph,
                nodelist=[node],
                **style,
            )
        plt.show()

    def _depth_search_step(
            self,
            node: int or str,
            num: dict[int or str: int],
            ftr: dict[int or str: int or str],
            start_time: dict[int or str: int],
            end_time: dict[int or str: int],
            time: int,
            k:  int
    ) -> [int]:
        time += 1
        start_time[node] = time
        num[node] = k
        k += 1

        for neighbour in self._adjacency_matrix[node]:
            if num[neighbour] == 0:
                ftr[neighbour] = node
                time, k = self._depth_search_step(neighbour, num, ftr, start_time, end_time, time, k)

        time += 1
        end_time[node] = time

        return time, k

    def depth_search(self) -> list[list[int or str]]:
        num = {}
        nodes = list(self._adjacency_matrix.keys())
        for node in nodes:
            num[node] = 0

        ftr = num.copy()
        start_time = num.copy()
        end_time = num.copy()

        time = 0
        k = 1

        for node in self._adjacency_matrix.keys():
            if num[node] == 0:
                time, k = self._depth_search_step(node, num, ftr, start_time, end_time, time, k)

        for key in start_time.keys():
            if start_time[key] == 1:
                ftr[key] = '*'

        search_info = [
            self.nodes,
            list(num.values()),
            list(ftr.values()),
            list(start_time.values()),
            list(end_time.values())
        ]

        depth_tree_adjacency_matrix = {}
        for node in self.nodes:
            depth_tree_adjacency_matrix[node] = []

        for i in range(len(search_info[0])):
            if search_info[2][i] != '*':
                depth_tree_adjacency_matrix[search_info[0][i]].append(search_info[2][i])
                depth_tree_adjacency_matrix[search_info[2][i]].append(search_info[0][i])

        tree = UndirectedGraph(depth_tree_adjacency_matrix)

        return search_info, tree


class DirectedGraph:
    def __init__(
            self,
            adjacency_matrix: dict[int or str: list[int or str]],
            with_labels=True,
            node_size=1000,
            node_color='gray',
            font_size=12,
            font_color='black',
            font_weight='bold',
            width=1,
            edge_color='black',
            arrowsize=20,
            arrowstyle='->',
            arrows=True,
            alpha=1
    ) -> None:
        self._adjacency_matrix = adjacency_matrix.copy()
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
        self._alpha = alpha

        self._graph = nx.DiGraph()

        self._node_style = {}

        for key in self._adjacency_matrix.keys():
            for node in self._adjacency_matrix[key]:
                self._graph.add_edge(key, node)

    @property
    def adjacency_matrix(self) -> dict[int or str: list[int or str]]:
        return self._adjacency_matrix.copy()

    @adjacency_matrix.setter
    def adjacency_matrix(self, adjacency_matrix: dict[int or str: list[int or str]]) -> None:
        self._adjacency_matrix = adjacency_matrix.copy()
        self._graph.clear()

        for key in self._adjacency_matrix:
            for node in self._adjacency_matrix[key]:
                self._graph.add_edge(key, node)

    @property
    def nodes(self):
        return sorted(list(self._graph.nodes).copy())

    def clear(self) -> None:
        self._graph.clear()
        self._adjacency_matrix.clear()

    def draw(self) -> None:
        nx.draw(
            self._graph,
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
            alpha=self._alpha,
        )
        for node, style in self._node_style.items():
            nx.draw_networkx_nodes(
                self._graph,
                nodelist=[node],
                **style,
            )
        plt.show()

    def _depth_search_step(
            self,
            node: int or str,
            num: dict[int or str: int],
            ftr: dict[int or str: int or str],
            start_time: dict[int or str: int],
            end_time: dict[int or str: int],
            time: int,
            k:  int
    ) -> [int]:
        time += 1
        start_time[node] = time
        num[node] = k
        k += 1

        for neighbour in self._adjacency_matrix[node]:
            if num[neighbour] == 0:
                ftr[neighbour] = node
                time, k = self._depth_search_step(neighbour, num, ftr, start_time, end_time, time, k)

        time += 1
        end_time[node] = time

        return time, k

    def depth_search(self) -> list[list[int or str]]:
        num = {}
        nodes = list(self._adjacency_matrix.keys())
        for node in nodes:
            num[node] = 0

        ftr = num.copy()
        start_time = num.copy()
        end_time = num.copy()

        time = 0
        k = 1

        for node in self._adjacency_matrix.keys():
            if num[node] == 0:
                time, k = self._depth_search_step(node, num, ftr, start_time, end_time, time, k)

        for key in start_time.keys():
            if start_time[key] == 1:
                ftr[key] = '*'

        search_info = [
            self.nodes,
            list(num.values()),
            list(ftr.values()),
            list(start_time.values()),
            list(end_time.values())
        ]

        depth_tree_adjacency_matrix = {}
        for node in self.nodes:
            depth_tree_adjacency_matrix[node] = []

        for i in range(len(search_info[0])):
            if search_info[2][i] != '*':
                depth_tree_adjacency_matrix[search_info[2][i]].append(search_info[0][i])

        tree = DirectedGraph(depth_tree_adjacency_matrix)

        return search_info, tree

```

</details>


*Авторство: **Прокопенко Д.О.***
