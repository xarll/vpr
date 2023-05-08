# Лабораторная работа №1. Алгоритмы поиска на графах.

Необходимо придумать для себя два графа (ориентированный и неориентированный), с количествами вершин, большими шести, произвести поиск на графах согласно своему варианту и разработать программное средство для реализации этого поиска.


## Варианты заданий:
Номер Вашего варианта совпадает с Вашим порядковым номером в списке группы. Алгоритм на реализацию определяется следующим образом:

- Поиск в ширину (ПВШ) - варианты №1, №4, №7, ...

- Поиск в глубину (ПВГ) - варианты №2, №5, №8, ... 

- Поиск в глубину (ПВГ) с классификацией дуг - варианты №3, №6, №9, ...



## Реализация поиска в ширину (ПВШ)

### Ручная
<details>

  <summary>Фото</summary>
  
  ![1](https://user-images.githubusercontent.com/102437629/234269509-3b9cfd39-def0-4816-8981-f50146f3de60.jpg)

  ![2](https://user-images.githubusercontent.com/102437629/234269534-8373855e-a80f-4e6e-abfa-19a3d4a94772.jpg)
  
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
    # Вставьте свой неориентированный граф, представленный списками смежности:
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

    G = graph.UndirectedGraph(A)
    G.draw()

    # Параметром метода width_search укажите вершину, с которой хотите начать поиск в глубину
    info, tree = G.width_search(1)
    utils.print_info(info)
    tree.draw()

    print()

    # Вставьте свой ориентированный граф, представленный списками смежности:
    A = {
        1: [2, 6],
        2: [3, 7],
        3: [8],
        4: [5, 8, 10],
        5: [10],
        6: [7],
        7: [8],
        8: [2, 9],
        9: [4],
        10: []
    }

    G = graph.DirectedGraph(A)
    G.draw()

    # Параметром метода width_search укажите вершину, с которой хотите начать поиск в глубину
    info, tree = G.width_search(1)
    utils.print_info(info)
    tree.draw()

```

</details>


<details>
  <summary>graph.py</summary>

```python
import matplotlib.pyplot as plt
import networkx as nx

import utils


class UndirectedGraph:
    def __init__(
            self,
            adjacency_matrix: dict[int or str: list[int or str]],
            with_labels=True,
            node_size=500,
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
            self._graph.add_node(key)

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

        for key in self._adjacency_matrix.keys():
            self._graph.add_node(key)

        for key in self._adjacency_matrix.keys():
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

    def width_search(self, start_node: int or str):
        if start_node not in list(self._graph.nodes):
            raise Exception('Node not in list')

        queue = utils.Queue()
        queue_copy = queue.copy()

        num = {}
        nodes = list(self._adjacency_matrix.keys())
        for node in nodes:
            num[node] = -1

        ftr = num.copy()

        queue.insert(start_node)
        queue_copy.insert(start_node)
        num[start_node] = 0
        ftr[start_node] = 0

        while not queue.empty():
            node = queue.pop()
            for neighbour in self._adjacency_matrix[node]:
                if num[neighbour] == -1:
                    queue.insert(neighbour)
                    queue_copy.insert(neighbour)
                    ftr[neighbour] = node
                    num[neighbour] = num[node] + 1

        for node in self._adjacency_matrix.keys():
            if node == start_node:
                continue

            if num[node] == -1:
                num[node] = 0
                ftr[node] = 0
                queue.insert(node)
                queue_copy.insert(node)

                while not queue.empty():
                    queue_node = queue.pop()
                    for neighbour in self._adjacency_matrix[queue_node]:
                        if num[neighbour] == -1:
                            queue.insert(neighbour)
                            queue_copy.insert(neighbour)
                            ftr[neighbour] = queue_node
                            num[neighbour] = num[queue_node] + 1

        for key in ftr.keys():
            if ftr[key] == 0:
                ftr[key] = '*'

        search_info = {
            'nodes': self.nodes,
            'num': list(num.values()),
            'ftr': list(ftr.values()),
            'queue': list(queue_copy)
        }

        width_tree_adjacency_matrix = {}
        for node in self.nodes:
            width_tree_adjacency_matrix[node] = []

        for i in range(len(search_info['nodes'])):
            if search_info['ftr'][i] != '*':
                width_tree_adjacency_matrix[search_info['nodes'][i]].append(search_info['ftr'][i])
                width_tree_adjacency_matrix[search_info['ftr'][i]].append(search_info['nodes'][i])

        tree = UndirectedGraph(width_tree_adjacency_matrix)

        return search_info, tree


class DirectedGraph:
    def __init__(
            self,
            adjacency_matrix: dict[int or str: list[int or str]],
            with_labels=True,
            node_size=500,
            node_color='gray',
            font_size=12,
            font_color='black',
            font_weight='bold',
            width=1,
            edge_color='black',
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
        self._arrows = arrows
        self._alpha = alpha

        self._graph = nx.DiGraph()

        self._node_style = {}

        for key in self._adjacency_matrix.keys():
            self._graph.add_node(key)

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

    def width_search(self, start_node: int or str):
        if start_node not in list(self._graph.nodes):
            raise Exception('Node not in list')

        queue = utils.Queue()
        queue_copy = queue.copy()

        num = {}
        nodes = list(self._adjacency_matrix.keys())
        for node in nodes:
            num[node] = -1

        ftr = num.copy()

        queue.insert(start_node)
        queue_copy.insert(start_node)
        num[start_node] = 0
        ftr[start_node] = 0

        while not queue.empty():
            node = queue.pop()
            for neighbour in self._adjacency_matrix[node]:
                if num[neighbour] == -1:
                    queue.insert(neighbour)
                    queue_copy.insert(neighbour)
                    ftr[neighbour] = node
                    num[neighbour] = num[node] + 1

        for node in self._adjacency_matrix.keys():
            if node == start_node:
                continue

            if num[node] == -1:
                num[node] = 0
                ftr[node] = 0
                queue.insert(node)
                queue_copy.insert(node)

                while not queue.empty():
                    queue_node = queue.pop()
                    for neighbour in self._adjacency_matrix[queue_node]:
                        if num[neighbour] == -1:
                            queue.insert(neighbour)
                            queue_copy.insert(neighbour)
                            ftr[neighbour] = queue_node
                            num[neighbour] = num[queue_node] + 1

        for key in ftr.keys():
            if ftr[key] == 0:
                ftr[key] = '*'

        search_info = {
            'nodes': self.nodes,
            'num': list(num.values()),
            'ftr': list(ftr.values()),
            'queue': list(queue_copy)
        }

        width_tree_adjacency_matrix = {}
        for node in self.nodes:
            width_tree_adjacency_matrix[node] = []

        for i in range(len(search_info['nodes'])):
            if search_info['ftr'][i] != '*':
                width_tree_adjacency_matrix[search_info['ftr'][i]].append(search_info['nodes'][i])

        tree = DirectedGraph(width_tree_adjacency_matrix)

        return search_info, tree

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

### Программный код (Бояршинов)

Репозиторий: [JKearnsl/search_in_graph](https://github.com/JKearnsl/search_in_graph)


## Реализация поиска в глубину (ПВГ)

### Ручная
<details>

  <summary>Фото</summary>
  
  ![1](https://user-images.githubusercontent.com/102437629/233969850-d1c80630-8c33-4e50-a9fc-5ded6337188a.jpg)
  
  ![2](https://user-images.githubusercontent.com/102437629/233969909-76d1f209-9686-47ae-a966-9b3475c11c56.jpg)
  
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
    # Вставьте свой неориентированный граф, представленный списками смежности:
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

    G = graph.UndirectedGraph(A)
    G.draw()

    # Параметром метода depth_search укажите вершину, с которой хотите начать поиск в глубину
    info, tree = G.depth_search(1)
    utils.print_info(info)
    tree.draw()

    print()

    # Вставьте свой ориентированный граф, представленный списками смежности:
    A = {
        1: [2, 6],
        2: [3, 7],
        3: [8],
        4: [5, 8, 10],
        5: [10],
        6: [7],
        7: [8],
        8: [2, 9],
        9: [4],
        10: []
    }

    G = graph.DirectedGraph(A)
    G.draw()

    # Параметром метода depth_search укажите вершину, с которой хотите начать поиск в глубину
    info, tree = G.depth_search(1)
    utils.print_info(info)
    tree.draw()

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
            node_size=500,
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
            self._graph.add_node(key)

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

        for key in self._adjacency_matrix.keys():
            self._graph.add_node(key)

        for key in self._adjacency_matrix.keys():
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
            k: int
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

    def depth_search(self, start_node: int or str):
        if start_node not in list(self._graph.nodes):
            raise Exception('Node not in list')

        num = {}
        nodes = list(self._adjacency_matrix.keys())
        for node in nodes:
            num[node] = 0

        ftr = num.copy()
        start_time = num.copy()
        end_time = num.copy()

        time = 0
        k = 1

        time, k = self._depth_search_step(start_node, num, ftr, start_time, end_time, time, k)

        for node in self._adjacency_matrix.keys():
            if num[node] == 0:
                time, k = self._depth_search_step(node, num, ftr, start_time, end_time, time, k)

        for key in ftr.keys():
            if ftr[key] == 0:
                ftr[key] = '*'

        search_info = {
            'nodes': self.nodes,
            'num': list(num.values()),
            'ftr': list(ftr.values()),
            'tn': list(start_time.values()),
            'tk': list(end_time.values())
        }

        depth_tree_adjacency_matrix = {}
        for node in self.nodes:
            depth_tree_adjacency_matrix[node] = []

        for i in range(len(search_info['nodes'])):
            if search_info['ftr'][i] != '*':
                depth_tree_adjacency_matrix[search_info['nodes'][i]].append(search_info['ftr'][i])
                depth_tree_adjacency_matrix[search_info['ftr'][i]].append(search_info['nodes'][i])

        tree = UndirectedGraph(depth_tree_adjacency_matrix)

        return search_info, tree


class DirectedGraph:
    def __init__(
            self,
            adjacency_matrix: dict[int or str: list[int or str]],
            with_labels=True,
            node_size=500,
            node_color='gray',
            font_size=12,
            font_color='black',
            font_weight='bold',
            width=1,
            edge_color='black',
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
        self._arrows = arrows
        self._alpha = alpha

        self._graph = nx.DiGraph()

        self._node_style = {}

        for key in self._adjacency_matrix.keys():
            self._graph.add_node(key)

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
            k: int
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

    def depth_search(self, start_node: int or str) -> list[list[int or str]]:
        if start_node not in list(self._graph.nodes):
            raise Exception('Node not in list')

        num = {}
        nodes = list(self._adjacency_matrix.keys())
        for node in nodes:
            num[node] = 0

        ftr = num.copy()
        start_time = num.copy()
        end_time = num.copy()

        time = 0
        k = 1

        time, k = self._depth_search_step(start_node, num, ftr, start_time, end_time, time, k)

        for node in self._adjacency_matrix.keys():
            if num[node] == 0:
                time, k = self._depth_search_step(node, num, ftr, start_time, end_time, time, k)

        for key in ftr.keys():
            if ftr[key] == 0:
                ftr[key] = '*'

        search_info = {
            'nodes': self.nodes,
            'num': list(num.values()),
            'ftr': list(ftr.values()),
            'tn': list(start_time.values()),
            'tk': list(end_time.values())
        }

        depth_tree_adjacency_matrix = {}
        for node in self.nodes:
            depth_tree_adjacency_matrix[node] = []

        for i in range(len(search_info['nodes'])):
            if search_info['ftr'][i] != '*':
                depth_tree_adjacency_matrix[search_info['ftr'][i]].append(search_info['nodes'][i])

        tree = DirectedGraph(depth_tree_adjacency_matrix)

        return search_info, tree

```

</details>


<details>
  <summary>utils.py</summary>

```python
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

### Программный код (Бояршинов)
  
Репозиторий: [JKearnsl/search_in_graph](https://github.com/JKearnsl/search_in_graph)

## Реализация поиска в глубину (ПВГ) с классификацией дуг

### Ручная
<details>

  <summary>Фото</summary>
  
  ![1](https://user-images.githubusercontent.com/102437629/234335845-d5829ff0-d413-4562-a7a5-5af5a11d8458.jpg)

  ![2](https://user-images.githubusercontent.com/102437629/234335958-2facc8bc-d046-4c81-bd1c-ed060c82c057.jpg)
  
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
    # Вставьте свой неориентированный граф, представленный списками смежности:
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

    G = graph.UndirectedGraph(A)
    G.draw()

    # Параметром метода depth_classification_search укажите вершину, с которой хотите начать поиск в глубину
    info, tree = G.depth_classification_search(1)
    utils.print_info(info)
    tree.draw()

    print()

    # Вставьте свой ориентированный граф, представленный списками смежности:
    A = {
        1: [2, 6],
        2: [3, 7],
        3: [8],
        4: [5, 8, 10],
        5: [10],
        6: [7],
        7: [8],
        8: [2, 9],
        9: [4],
        10: []
    }

    G = graph.DirectedGraph(A)
    G.draw()

    # Параметром метода depth_classification_search укажите вершину, с которой хотите начать поиск в глубину
    info, tree = G.depth_classification_search(1)
    utils.print_info(info)
    tree.draw()

```

</details>


<details>
  <summary>graph.py</summary>
  
```python
import matplotlib.pyplot as plt
import networkx as nx

import utils


class UndirectedGraph:
    def __init__(
            self,
            adjacency_matrix: dict[int or str: list[int or str]],
            with_labels=True,
            node_size=500,
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
            self._graph.add_node(key)

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

        for key in self._adjacency_matrix.keys():
            self._graph.add_node(key)

        for key in self._adjacency_matrix.keys():
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

    def _depth_classification_search_step(
            self,
            node: int or str,
            num: dict[int or str: int],
            ftr: dict[int or str: int or str],
            start_time: dict[int or str: int],
            end_time: dict[int or str: int],
            UB: utils.Set,
            UT: utils.Set,
            time: int,
            k: int
    ):
        time += 1
        start_time[node] = time
        num[node] = k
        k += 1

        for neighbour in self._adjacency_matrix[node]:
            if start_time[neighbour] == 0:
                ftr[neighbour] = node

                UT.insert((node, neighbour))
                time, k = self._depth_classification_search_step(neighbour, num, ftr, start_time, end_time, UB, UT,
                                                                 time, k)
            elif start_time[neighbour] < start_time[node] and ftr[node] != neighbour:
                UB.insert((node, neighbour))

        time += 1
        end_time[node] = time

        return time, k

    def depth_classification_search(self, start_node: int or str):
        if start_node not in list(self._graph.nodes):
            raise Exception('Node not in list')

        UB = utils.Set()
        UT = UB.copy()

        num = {}
        nodes = list(self._adjacency_matrix.keys())
        for node in nodes:
            num[node] = 0

        ftr = num.copy()
        start_time = num.copy()
        end_time = num.copy()

        k = 1
        time = 0

        time, k = self._depth_classification_search_step(start_node, num, ftr, start_time, end_time, UB, UT, time, k)

        for node in self._adjacency_matrix.keys():
            if start_time[node] == 0:
                time, k = self._depth_classification_search_step(node, num, ftr, start_time, end_time, UB, UT, time, k)

        for key in ftr.keys():
            if ftr[key] == 0:
                ftr[key] = '*'

        search_info = {
            'nodes': self.nodes,
            'num': list(num.values()),
            'ftr': list(ftr.values()),
            'tn': list(start_time.values()),
            'tk': list(end_time.values()),
            'UB': list(UB),
            'UT': list(UT)
        }

        depth_tree_adjacency_matrix = {}
        for node in self.nodes:
            depth_tree_adjacency_matrix[node] = []

        for i in range(len(search_info['nodes'])):
            if search_info['ftr'][i] != '*':
                depth_tree_adjacency_matrix[search_info['nodes'][i]].append(search_info['ftr'][i])
                depth_tree_adjacency_matrix[search_info['ftr'][i]].append(search_info['nodes'][i])

        tree = UndirectedGraph(depth_tree_adjacency_matrix)

        return search_info, tree


class DirectedGraph:
    def __init__(
            self,
            adjacency_matrix: dict[int or str: list[int or str]],
            with_labels=True,
            node_size=500,
            node_color='gray',
            font_size=12,
            font_color='black',
            font_weight='bold',
            width=1,
            edge_color='black',
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
        self._arrows = arrows
        self._alpha = alpha

        self._graph = nx.DiGraph()

        self._node_style = {}

        for key in self._adjacency_matrix.keys():
            self._graph.add_node(key)

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

    def _depth_classification_search_step(
            self,
            node: int or str,
            num: dict[int or str: int],
            ftr: dict[int or str: int or str],
            start_time: dict[int or str: int],
            end_time: dict[int or str: int],
            UB: utils.Set,
            UC: utils.Set,
            UF: utils.Set,
            UT: utils.Set,
            time: int,
            k: int
    ):
        time += 1
        start_time[node] = time
        num[node] = k
        k += 1

        for neighbour in self._adjacency_matrix[node]:
            if start_time[neighbour] == 0:
                ftr[neighbour] = node

                UT.insert((node, neighbour))
                time, k = self._depth_classification_search_step(neighbour, num, ftr, start_time, end_time, UB, UC, UF,
                                                                 UT, time, k)
            elif start_time[neighbour] > start_time[node]:
                UF.insert((node, neighbour))
            elif end_time[neighbour] == 0:
                UB.insert((node, neighbour))
            else:
                UC.insert((node, neighbour))

        time += 1
        end_time[node] = time

        return time, k

    def depth_classification_search(self, start_node: int or str):
        if start_node not in list(self._graph.nodes):
            raise Exception('Node not in list')

        UB = utils.Set()
        UC = UB.copy()
        UF = UB.copy()
        UT = UB.copy()

        num = {}
        nodes = list(self._adjacency_matrix.keys())
        for node in nodes:
            num[node] = 0

        ftr = num.copy()
        start_time = num.copy()
        end_time = num.copy()

        k = 1
        time = 0

        time, k = self._depth_classification_search_step(start_node, num, ftr, start_time, end_time, UB, UC, UF, UT,
                                                         time, k)

        for node in self._adjacency_matrix.keys():
            if start_time[node] == 0:
                time, k = self._depth_classification_search_step(node, num, ftr, start_time, end_time, UB, UC, UF, UT,
                                                                 time, k)

        for key in ftr.keys():
            if ftr[key] == 0:
                ftr[key] = '*'

        search_info = {
            'nodes': self.nodes,
            'num': list(num.values()),
            'ftr': list(ftr.values()),
            'tn': list(start_time.values()),
            'tk': list(end_time.values()),
            'UB': list(UB),
            'UC': list(UC),
            'UF': list(UF),
            'UT': list(UT)
        }

        depth_tree_adjacency_matrix = {}
        for node in self.nodes:
            depth_tree_adjacency_matrix[node] = []

        for i in range(len(search_info['nodes'])):
            if search_info['ftr'][i] != '*':
                depth_tree_adjacency_matrix[search_info['ftr'][i]].append(search_info['nodes'][i])

        tree = DirectedGraph(depth_tree_adjacency_matrix)

        return search_info, tree

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
            result = self._set[self._index]
            self._index += 1
            return result

        raise StopIteration


class Set:
    def __init__(self, elements: list = None) -> None:
        if elements is None:
            self._elements = []
        else:
            self._elements = elements.copy()

    def __len__(self) -> int:
        return len(self._elements)

    def __str__(self) -> str:
        return str(self._elements)

    def __getitem__(self, index) -> any:
        return self._elements[index]

    def __iter__(self) -> SetIterator:
        return SetIterator(self)

    def copy(self):
        return Set(self._elements.copy())

    def insert(self, value: any or list) -> None:
        if isinstance(value, (list, dict)):
            self._elements = value.copy()
        elif isinstance(value, (int, float, str, tuple)):
            if value not in self._elements:
                self._elements.append(value)

    def extract(self, index) -> any:
        return self._elements.pop(index)

    def empty(self) -> bool:
        return len(self._elements) == 0

    def clear(self) -> None:
        self._elements.clear()

    def unite(self, other):
        result = self._elements.copy()
        for element in other._elements:
            if element not in self._elements:
                result.append(element)

        return Set(result)

    def intersect(self, other):
        result = []
        for element in self._elements:
            if element in other._elements:
                result.append(element)

        return Set(result)

    def subtract(self, other):
        result = []
        for element in self._elements:
            if element not in other._elements:
                result.append(element)

        return Set(result)

    def subtract_symmetrically(self, other):
        result = []
        for element in self._elements:
            if element not in other._elements:
                result.append(element)

        for element in other._elements:
            if element not in self._elements:
                result.append(element)

        return Set(result)
                

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


*Авторство: **Прокопенко Д.О.***
