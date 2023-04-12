# Минимизация автомата распознователя

Лаб работа выполняется на листочке

## Варианты заданий
<details>

  <summary>Фото</summary>
  
![483jlVq8H4g](https://user-images.githubusercontent.com/60810530/230284508-a5eb8f7a-323f-4fc3-a6fd-6fa6ca3734b6.jpg)
![diATONU8aoY (1)](https://user-images.githubusercontent.com/60810530/230284522-dff03608-6b37-4121-9844-3e3763c14710.jpg)
![BzP7f5l_e94](https://user-images.githubusercontent.com/60810530/230284538-e58bedf0-2696-492a-b9ae-c1674c092554.jpg)
![1-0NXABdjr0](https://user-images.githubusercontent.com/60810530/230284547-c235ebdf-d457-4e7c-a222-967796e79899.jpg)
![G91HfU9iJrI](https://user-images.githubusercontent.com/60810530/230284554-75bee157-f5ab-4ae1-9a86-9a43f2b9863b.jpg)
![aEom_frm2Oo](https://user-images.githubusercontent.com/60810530/230284566-9104ab6c-5c8c-4e44-a86f-4f77710a2552.jpg)
![HvtfAWrbQoc](https://user-images.githubusercontent.com/60810530/230284575-b8335bfd-052a-4b82-b1e4-7c44ca8f6332.jpg)
![_Vip6gti66k](https://user-images.githubusercontent.com/60810530/230284577-a623d05c-fc79-48f0-ba3d-6b93af3b4f9d.jpg)

</details>  

## Алгоритм (ручная запись)
1. Проверить, является ли детерминированным
2. Удалить недостижимые вершины
3. Разбить на классы эквивалентности
4. Строим граф



## Реализация варианта #2

### Ручная
<details>
  <summary>Фото</summary>
  
  PS: в п.3 я ошибся, последний класс: `[['q4', 'q5'], ['q0'], ['q3', 'q2', 'q1']]`

  ![image](https://user-images.githubusercontent.com/76239707/230787270-cdceddda-3a27-469b-97e3-1d2b1d9b757d.png)
  ![image](https://user-images.githubusercontent.com/76239707/230787275-7a98676d-824e-4a28-8bde-f87a7f1e8a84.png)
  ![image](https://user-images.githubusercontent.com/76239707/230787280-587dc5a3-6424-428b-8e9f-9ebc9169ce06.png)

</details>




## Реализация варианта #5

### Ручная
<details>
  <summary>Фото</summary>

  ![image](https://user-images.githubusercontent.com/76239707/230280641-969b8b64-89ab-4d01-814a-c579b2651c89.png)
  ![image](https://user-images.githubusercontent.com/76239707/230280669-7ff6618a-0fd9-4721-a63f-eb5566e7ded3.png)

</details>



## Общая программная реализация

### Программный код


Вам потребуется:
- `python 3.11`
  - `matplotlib`
  - `networkx`


<details>
  <summary>main.py</summary>
  
  Если используете pycharm, то включите эмуляцию командной строки
  
  ```Python
  """

Лаб №6

Автор: Бог Войны

Тема: Минимизация автомата распознователя


"""
import copy
import logging
import random

from utils.utils import clear_term, remove_unreachable_vertices, print_graph, partitioning_into_equivalence_classes


def main():
    logging.basicConfig(level=logging.INFO, format='%(levelname)s | %(message)s')
    alphabet = []
    end_vertex: list[str] = list()
    start_vertex = None
    graph_data = list()
    print("Введите алфавит. Напишите 'end' для завершения ввода")
    while True:
        value = input("Введите символ: ").replace(' ', '')

        if not value.isdigit():
            value = value.lower()

        if value == 'end':
            break
        if len(value) != 1:
            print("Введено не одно значение")
            continue
        alphabet.append(value)

    while True:
        count_of_vertex = input("Введите кол-во вершин: ")
        if not count_of_vertex.isdigit():
            print("Введено не число")
            continue
        count_of_vertex = int(count_of_vertex)
        if count_of_vertex < 1:
            print("Введено не положительное число")
            continue
        break

    # ----------------- Ввод графа -----------------
    """
        Тут происходит ввод графа. Эту часть я советую
        кастомизировать под себя. Например, можно сделать
        так, чтобы пользователь вводил граф в виде:
        Q0 -> Q1, Q2, Q3
        Q1 -> Q2, Q3
        Q2 -> Q3

        и тд

        На данный момент имею зависимость utils.clear_term()
        для очищения терминала

    """

    main_buffer = "\nВведите граф\n" \
                  "Вершины: Q0, Q1, ..., Q{n}\n" \
                  "Введите 'end' для завершения ввода\n" \
                  "Введите 'eps' для eps значения\n\n"
    while True:
        clear_term()
        buffer = main_buffer + "\n Из узла: "
        data = []
        for msg in ["{!s} в: ", "{!s} с весом: ", "{!s}"]:
            value = input(buffer).replace(' ', '')

            if not value.isdigit():
                value = value.lower()

            buffer += msg.format(value)
            clear_term()

            if value == "end":
                data.clear()
                break

            if len(data) in [0, 1]:
                if value not in [f"q{i}" for i in range(count_of_vertex)]:
                    print("Введена не вершина")
                    continue
            elif len(data) == 2:
                if value == "eps":
                    value = None
                elif value not in alphabet:
                    print("Введен не символ алфавита")
                    continue

            data.append(value)

        main_buffer = buffer
        print(buffer)

        if data:
            graph_data.append(
                {
                    "from": data[0],
                    "to": data[1],
                    "weight": data[2]
                }
            )
        else:
            break

    print("Graph_data: ", graph_data)

    # ----------------- Начальная вершина eps автомата -----------------
    print("\nВведите начальную вершину eps автомата. Например: Q1.")
    while True:
        var = input("Ввод: ").replace(" ", "").lower()
        if var not in [f"q{i}" for i in range(count_of_vertex)]:
            print("Введена не вершина")
            continue
        start_vertex = var
        break

    # ----------------- Конечные вершины eps автомата -----------------
    print("\nВведите конечные вершины eps автомата. Например: Q1. Напишите 'end' для завершения ввода")
    while True:
        var = input("Введите конечную вершину eps автомата: ").replace(" ", "").lower()
        if var == 'end':
            break
        if var not in [f"q{i}" for i in range(count_of_vertex)]:
            print("Введена не вершина")
            continue
        end_vertex.append(var)

    # ----------------- Вывод графа -----------------
    print_graph(graph_data, start_vertex, end_vertex)
    print("Graph_data: ", graph_data)

    # ---------------- Удаление недостижимых вершин -------------------
    new_graph: list[dict[str]] = []
    remove_unreachable_vertices(graph_data, new_graph, start_vertex)
    graph_data = new_graph
    all_vertex = list(set(el["from"] for el in graph_data))  # todo: использовать выше

    new_end_vertex = []
    for vertex in end_vertex:
        if vertex in all_vertex:
            new_end_vertex.append(vertex)
    end_vertex = new_end_vertex

    # ----------------- Вывод графа -----------------
    print_graph(graph_data, start_vertex, end_vertex)

    # --------------- Детерминирован ли -------------
    is_determined = True
    for vertex in all_vertex:
        list_of_weight = [route["weight"] for route in graph_data if route["from"] == vertex]
        if None in list_of_weight:
            """
            Если eps в списке весов маршрутов вершины
            
            """
            list_of_weight.remove(None)

        if sorted(alphabet) != sorted(list_of_weight):
            is_determined = False

    if is_determined:
        print("Автомат детерминирован!")
    else:
        print("Автомат недетерминирован")

    # ------- Разбитие автомата на классы эквивалентности --------
    equivalence_classes: list[list[str]] = [end_vertex, [el for el in all_vertex if el not in end_vertex]]
    classes = partitioning_into_equivalence_classes(equivalence_classes, graph_data, alphabet)
    print(f"Класс: {classes} является финальным")

    # ------------- Построение графа ---------------
    result_graph_data = copy.deepcopy(graph_data)
    for _class in classes:
        class_mask = f"[{random.choice(_class)}]"
        for relation in result_graph_data:
            if relation["from"] in _class:
                relation["from"] = class_mask
            if relation["to"] in _class:
                relation["to"] = class_mask

    result_start_vertex = f"[{start_vertex}]"
    result_end_vertex = f"[{end_vertex[0]}]"

    print_graph(result_graph_data, result_start_vertex, [result_end_vertex])


if __name__ == '__main__':
    main()

  ```

</details>


<details>
  <summary>utils/utils.py</summary>
  
  Комментарии можете удалить, также сменить нейминг переменных
  
  ```Python
  import copy
import logging
import os
import platform

from utils.graph import Graph


def remove_unreachable_vertices(graph_data: list[dict[str]], new_graph: list[dict[str]], start_vertex: str):
    for route in [el for el in graph_data if el["from"] == start_vertex]:
        new_graph.append(route)

        if route["to"] in [el["from"] for el in new_graph]:
            continue

        remove_unreachable_vertices(graph_data, new_graph, route["to"])


def partitioning_into_equivalence_classes(
        classes: list[list[str]],
        graph_data: list[dict[str]],
        alphabet: list[str]
) -> list[list[str]]:
    iters = 0
    while True:
        logging.info(f"Ξ({iters}) = {classes}")
        current_classes: list[list[str]] = copy.deepcopy(classes)
        for _class in classes:
            if len(_class) == 1:
                continue

            exclude_rels: list[dict[str, list]] = []
            for vertex in _class:
                for alpha in alphabet:

                    if [el for el in exclude_rels if vertex in el["vertex"]]:
                        continue

                    dest_vertex = [el["to"] for el in graph_data if el["from"] == vertex and el["weight"] == alpha][0]
                    if dest_vertex in _class:
                        continue

                    """
                    На этом этапе мы находимся на исключении: 
                    текущий dest_vertex принадлежит другому классу
                    
                    """

                    dest_class = [_ for _ in classes if dest_vertex in _][0]
                    _ = [el for el in exclude_rels if el["_class"] == dest_class]
                    dest_class_in_exclude_rels = None if not _ else _[0]

                    if dest_class_in_exclude_rels:
                        dest_class_in_exclude_rels["vertex"].append(vertex)
                    else:
                        exclude_rels.append(dict(vertex=[vertex], _class=dest_class))

            """
            Разбиваем класс на подклассы
            
            """
            for rel in exclude_rels:
                for el in rel["vertex"]:
                    current_classes[classes.index(_class)].remove(copy.copy(el))
                current_classes.append(rel["vertex"])

        """
        Мы удаляем пустые классы которые могли образоваться на делении класса на подклассы
        и сравниваем предыдущий класс с текущим
        
        """
        for el in range(current_classes.count([])):
            current_classes.remove([])

        if classes != current_classes:
            iters += 1
            classes = current_classes
            continue

        return current_classes


def print_graph(graph_data: list[dict[str]], start_vertex: str = None, end_vertex: list[str] = None):
    if end_vertex is None:
        end_vertex = []

    graph = Graph()
    for row in graph_data:
        label = row["weight"]
        if label is None:
            label = "eps"
        graph.add_edge(row["from"], row["to"], label)

    for el in end_vertex:
        graph.style_node(el, node_color="red", alpha=0.5)

    if start_vertex:
        graph.style_node(start_vertex, node_color="green", alpha=0.5, node_size=1000)
    graph.draw()


def clear_term():
    if not os.getenv('TERM', None):
        os.putenv('TERM', 'xterm')

    if platform.system() == 'Windows':
        os.system('cls')
    else:
        os.system('clear')

  ```

</details>


<details>
  <summary>utils/graph.py</summary>
  
  Стили графа меняйте под себя, меняйте цвет
  
  ```Python
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
  ```

</details>

Код на гитхаб: [тык](https://github.com/xarll/vpr/tree/main/items/teorof/lab/lab6/program)
