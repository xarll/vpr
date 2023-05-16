# Лабораторная работа №2. Алгоритмы построения остова графа минимальной стоимости.

Необходимо придумать для себя два графа (ориентированный и неориентированный), с количествами вершин, большими шести, произвести поиск на графах согласно своему варианту и разработать программное средство для реализации этого поиска.


## Варианты заданий:
Номер Вашего варианта совпадает с Вашим порядковым номером в списке группы. Алгоритм на реализацию определяется следующим образом:

- Алгоритм Прима - варианты №1, №3, №5, ...

- Алгоритм Краскала - варианты №2, №4, №6, ... 



## Реализация алгоритма Прима

### Ручная
<details>

  <summary>Фото</summary>
  
  ![1]

  ![2]
  
</details>




### Программный код
Для работы программы потребуется:

- Python 3.11:
  - `networkx`
  - `matplotlib`


<details>
  <summary>main.py</summary>

```python
import graph, utils


if __name__ == '__main__':
    # Вставьте свой неориентированный граф, представленный списками ребер и их весов:
    U = {
        (1, 2): 3,
        (1, 6): 5,

        (2, 1): 3,
        (2, 3): 2,
        (2, 7): 1,
        (2, 8): 4,

        (3, 2): 2,
        (3, 8): 10,

        (4, 5): 7,
        (4, 8): 1,
        (4, 9): 9,
        (4, 10): 1,

        (5, 4): 7,
        (5, 10): 12,

        (6, 1): 5,
        (6, 7): 4,

        (7, 2): 1,
        (7, 6): 4,
        (7, 8): 5,

        (8, 2): 4,
        (8, 3): 10,
        (8, 4): 1,
        (8, 7): 5,
        (8, 9): 3,

        (9, 4): 9,
        (9, 8): 3,

        (10, 4): 1,
        (10, 5): 12
    }

    G = graph.UndirectedGraph(U)
    G.draw()

    # Параметром метода prim_minimum_tree можно указать вершину, с которой хотите начать построение минимального остова:
    info, tree = G.prim_minimum_tree()
    utils.print_info(info)
    tree.draw()

    print()

    # Вставьте свой ориентированный граф, представленный списками ребер и их весов:
    U = {
        (1, 2): 3,
        (1, 6): 5,

        (2, 3): 2,
        (2, 7): 1,

        (3, 8): 10,

        (4, 5): 7,
        (4, 8): 1,
        (4, 10): 1,

        (5, 10): 12,

        (6, 7): 4,

        (7, 8): 5,

        (8, 2): 4,
        (8, 9): 3,

        (9, 4): 9
    }

    G = graph.DirectedGraph(U)
    G.draw()

    # Параметром метода prim_minimum_tree можно указать вершину, с которой хотите начать построение минимального остова:
    info, tree = G.prim_minimum_tree()
    utils.print_info(info)
    tree.draw()

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
            edge_dictionary: dict[(int or str, int or str): int],
            with_labels=True,
            node_size=500,
            node_color='gray',
            font_size=12,
            font_color='black',
            font_weight='bold',
            width=1,
            edge_color='black',
            alpha=1,
            label_font_size=15,
            label_font_color='black',
            label_font_weight='bold',
            label_rotate=True,
            label_verticalalignment='bottom'
    ) -> None:
        self._edge_dictionary = edge_dictionary.copy()
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

        for edge in self._edge_dictionary.keys():
            self._graph.add_edge(edge[0], edge[1], weight=self._edge_dictionary[edge])

    @property
    def edge_dictionary(self) -> dict[(int or str, int or str): int]:
        return self._edge_dictionary.copy()

    @edge_dictionary.setter
    def edge_dictionary(self, edge_dictionary: dict[(int or str, int or str): int]) -> None:
        self._edge_dictionary = edge_dictionary.copy()
        self._graph.clear()

        for edge in self._edge_dictionary.keys():
            self._graph.add_edge(edge[0], edge[1], weight=self._edge_dictionary[edge])

    @property
    def nodes(self):
        return sorted(list(self._graph.nodes).copy())

    def clear(self) -> None:
        self._graph.clear()
        self._edge_dictionary.clear()

    def draw(self) -> None:
        self._pos = nx.circular_layout(self._graph)

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
            edge_labels=nx.get_edge_attributes(self._graph, 'weight'),
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

    def _prim_minimum_tree_component(
            self,
            keys: dict[int or str: int or float],
            ftr: dict[int or str: int or str],
            num: dict[int or str: int]
    ) -> None:
        time = 1

        set = utils.Set(self.nodes)
        while not set.empty():
            min_keys = float('inf')
            min_node: int or str
            for node in keys.keys():
                if keys[node] < min_keys and node in set:
                    min_node = node
                    min_keys = keys[node]

            try:
                node_source = set.extract(value=min_node)
            except:
                return

            for node_destination in set:
                if (node_source, node_destination) in self._edge_dictionary.keys() and \
                        self._edge_dictionary[(node_source, node_destination)] < keys[node_destination]:
                    ftr[node_destination] = node_source
                    time += 1
                    num[node_destination] = time
                    keys[node_destination] = self._edge_dictionary[(node_source, node_destination)]

    def prim_minimum_tree(self, start_node: int or str = None):
        if start_node and start_node not in list(self._graph.nodes):
            raise Exception('Node not in list')

        if not start_node:
            start_node = random.choice(self.nodes)

        keys = {}
        for node in self.nodes:
            keys[node] = float('inf')
        keys[start_node] = 0

        ftr = {start_node: 0}
        num = {start_node: 1}

        self._prim_minimum_tree_component(keys, ftr, num)

        for node in keys.keys():
            if keys[node] == float('inf'):
                keys[node] = 0
                ftr[node] = 0
                num[node] = 1
                self._prim_minimum_tree_component(keys, ftr, num)

        for key in ftr.keys():
            if ftr[key] == 0:
                ftr[key] = '*'

        cost = 0
        for node_destination in ftr.keys():
            if ftr[node_destination] == '*' or node_destination == '*':
                continue

            cost += self._edge_dictionary[(ftr[node_destination], node_destination)]

        tree_info = {
            'node': self.nodes,
            'num': list(num.values()),
            'ftr': list(ftr.values()),
            'key': list(keys.values()),
            'cost': [cost]
        }

        tree_edge_dictionary = {}
        for node_destination in ftr.keys():
            if ftr[node_destination] == '*' or node_destination == '*':
                continue

            tree_edge_dictionary[(ftr[node_destination], node_destination)] = \
                self._edge_dictionary[(ftr[node_destination], node_destination)]

        tree = UndirectedGraph(tree_edge_dictionary)

        return tree_info, tree


class DirectedGraph:
    def __init__(
            self,
            edge_dictionary: dict[(int or str, int or str): int],
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
            label_font_size=15,
            label_font_color='black',
            label_font_weight='bold',
            label_rotate=True,
            label_verticalalignment='bottom'
    ) -> None:
        self._edge_dictionary = edge_dictionary.copy()
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

        for edge in self._edge_dictionary.keys():
            self._graph.add_edge(edge[0], edge[1], weight=self._edge_dictionary[edge])

    @property
    def edge_dictionary(self) -> dict[(int or str, int or str): int]:
        return self._edge_dictionary.copy()

    @edge_dictionary.setter
    def edge_dictionary(self, edge_dictionary: dict[(int or str, int or str): int]) -> None:
        self._edge_dictionary = edge_dictionary.copy()
        self._graph.clear()

        for edge in self._edge_dictionary.keys():
            self._graph.add_edge(edge[0], edge[1], weight=self._edge_dictionary[edge])

    @property
    def nodes(self):
        return sorted(list(self._graph.nodes).copy())

    def clear(self) -> None:
        self._graph.clear()
        self._edge_dictionary.clear()

    def draw(self) -> None:
        self._pos = nx.circular_layout(self._graph)

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
            edge_labels=nx.get_edge_attributes(self._graph, 'weight'),
            font_size=self._label_font_size,
            font_color=self._label_font_color,
            font_weight=self._label_font_weight,
            rotate=self._label_rotate,
            verticalalignment=self._label_verticalalignment
        )

        for node, style in self._node_style.items():
            nx.draw_networkx_nodes(
                self._graph,
                nodelist=[node],
                **style,
            )
        plt.show()

    def _prim_minimum_tree_component(
            self,
            keys: dict[int or str: int or float],
            ftr: dict[int or str: int or str],
            num: dict[int or str: int],
    ) -> None:
        time = 1

        set = utils.Set(self.nodes)
        while not set.empty():
            min_keys = float('inf')
            min_node: int or str
            for node in keys.keys():
                if keys[node] < min_keys and node in set:
                    min_node = node
                    min_keys = keys[node]

            try:
                node_source = set.extract(value=min_node)
            except:
                return

            for node_destination in set:
                if (node_source, node_destination) in self._edge_dictionary.keys() and \
                        self._edge_dictionary[(node_source, node_destination)] < keys[node_destination]:
                    ftr[node_destination] = node_source
                    time += 1
                    num[node_destination] = time
                    keys[node_destination] = self._edge_dictionary[(node_source, node_destination)]

    def prim_minimum_tree(self, start_node: int or str = None):
        if start_node and start_node not in list(self._graph.nodes):
            raise Exception('Node not in list')

        if not start_node:
            start_node = random.choice(self.nodes)

        keys = {}
        for node in self.nodes:
            keys[node] = float('inf')
        keys[start_node] = 0

        ftr = {start_node: 0}
        num = {start_node: 1}

        self._prim_minimum_tree_component(keys, ftr, num)

        for node in keys.keys():
            if keys[node] == float('inf'):
                keys[node] = 0
                ftr[node] = 0
                num[node] = 1
                self._prim_minimum_tree_component(keys, ftr, num)

        for key in ftr.keys():
            if ftr[key] == 0:
                ftr[key] = '*'

        cost = 0
        for node_destination in ftr.keys():
            if ftr[node_destination] == '*' or node_destination == '*':
                continue

            cost += self._edge_dictionary[(ftr[node_destination], node_destination)]

        tree_info = {
            'node': self.nodes,
            'num': list(num.values()),
            'ftr': list(ftr.values()),
            'key': list(keys.values()),
            'cost': [cost]
        }

        tree_edge_dictionary = {}
        for node_destination in ftr.keys():
            if ftr[node_destination] == '*' or node_destination == '*':
                continue

            tree_edge_dictionary[(ftr[node_destination], node_destination)] = \
                self._edge_dictionary[(ftr[node_destination], node_destination)]

        tree = DirectedGraph(tree_edge_dictionary)

        return tree_info, tree

```

</details>


<details>
  <summary>utils.py</summary>
  
```python
class SetIterator:
    def __init__(self, set):
        self._set = set
        self._index = 0

    def __next__(self):
        if self._index < len(self._set):
            result = self._set._elements[self._index]
            self._index += 1
            return result

        raise StopIteration


class Set:
    def __init__(self, elements: list or set = None) -> None:
        if elements is None:
            self._elements = []
        else:
            self._elements = elements.copy()

    def __len__(self) -> int:
        return len(self._elements)

    def __str__(self) -> str:
        return str(self._elements)

    def __iter__(self):
        return SetIterator(self)

    def copy(self):
        return Set(self._elements.copy())

    def insert(self, value: any) -> None:
        if value not in self._elements:
            self._elements.append(value)

    def extract(self, index: int = None, value: any = None) -> any:
        if index and value:
            raise('Too many arguments')
        elif index:
            return self._elements.pop(index)
        else:
            value_index = self._elements.index(value)
            return self._elements.pop(value_index)

    def empty(self) -> bool:
        return len(self._elements) == 0

    def clear(self) -> None:
        self._elements.clear()


def print_info(info: dict[str: list[int or str]]) -> None:
    for key in info.keys():
        if key == 'nodes':
            print(' ' * 7, end='')
        else:
            print(f'{key}:', end='')
            print(' ' * (6 - len(key)), end='')

        for element in info[key]:
            if len(str(element)) > 1:
                print(f'{element}', end='  ')
            else:
                print(f' {element}', end='  ')
        print()

```

</details>



## Реализация алгоритма Краскала

### Ручная
<details>

  <summary>Фото</summary>
  
  ![1]
  
  ![2]
  
</details>




### Программный код
Для работы программы потребуется:

- Python 3.11:
  - `networkx`
  - `matplotlib`


<details>
  <summary>main.py</summary>

```python
sample
```

</details>


<details>
  <summary>graph.py</summary>

```python
sample
```

</details>


<details>
  <summary>utils.py</summary>

```python
sample
```

</details>

  
 *При обнаружении каких-либо недочетов и багов ОБЯЗАТЕЛЬНО И СРОЧНО писать в ВК: https://vk.com/prokdo


*Авторство: **Прокопенко Д.О.***
