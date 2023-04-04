# Детерминизация (Программно)

Нужно написать программу, которая будет выполнять лаб #4, но также иметь возможность проверить, является ли пользовательское слово допустимым или нет


## Алгоритм (ручная запись)
1. Построить ε-замыкания
2. Отобразить таблицу переходов автомата
3. По таблице граф
4. Строим детерминированный автомат
5. Строим его граф
6. Проверяем пользовательское слово


## Реализация

Препод разрешил пропустить пункт 1, однако это -10 баллов, из-за чего представляю 2 реализации

Для работы программы потребуется:

- python3.11
  - `networkx[default]`
  - `matplotlib`
  - `prettytable`
  
### Пункты 2-6 (Частичная реализация)

Пользователь вводит алфавит, таблицу 2, конечные вершины
Вы теряете 10 баллов при сдаче

<details>
  <summary>utils.py</summary>

```python
import logging

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


def get_d_data(
        start_vertex: list[str],
        end_vertex: list[str],
        alphabet: list[str],
        table_data: list[dict[str]]
) -> tuple[list[str], list[str], list[dict[str]]]:
    p_table_data: list[dict[str]] = []
    queue = [{"index": 0, "data": start_vertex}]
    p_start_vertex = ["P0"]
    p_end_vertex = []

    index = 0
    while queue:
        current_p = queue.pop(0)
        p_index = current_p["index"]
        logging.info(f"> P{p_index} = {current_p['data']}")

        if current_p["data"]:
            for el in end_vertex:
                if el in current_p["data"]:
                    p_end_vertex.append(f"P{p_index}")
                    break

        for symbol in alphabet:
            new_p_data = list()
            for s in current_p["data"]:
                d_row: dict = [row for row in table_data if row["from"] == s][0]
                data = d_row["to"][alphabet.index(symbol)]
                if data is None:
                    continue
                if type(data) is list:
                    for el in data:
                        if el not in new_p_data:
                            new_p_data.append(el)
                else:
                    new_p_data.append(data)
                logging.info(f"\t({s}; {symbol})-> {data}")

            if not new_p_data:
                logging.info(f"(P{p_index}; {symbol})-> ∅")
                p_table_data.append({"from": f'P{p_index}', "to": new_p_data, "mask": f"∅", "symbol": symbol})
                continue

            if new_p_data in [p["to"] for p in p_table_data]:
                view_index = int([p["mask"] for p in p_table_data if p["to"] == new_p_data][0][1:])
            else:
                index += 1
                view_index = index

                if new_p_data and view_index not in [p["index"] for p in queue]:
                    queue.append({"index": view_index, "data": new_p_data})

            logging.info(f"(P{p_index}; {symbol})-> {new_p_data} == P{view_index}")

            p_table_data.append({"from": f'P{p_index}', "to": new_p_data, "mask": f"P{view_index}", "symbol": symbol})

    return p_start_vertex, p_end_vertex, p_table_data


def check_word(input_value, p_start_vertex, p_end_vertex, p_table_data):
    current_p = p_start_vertex[0]
    for symbol in input_value:
        row = [row for row in p_table_data if row["from"] == current_p and row["symbol"] == symbol][0]
        if row["mask"] == "∅":
            return False
        current_p = row["mask"]
    return current_p in p_end_vertex
```

</details>
<details>
  <summary>main.py</summary>

```python
import logging

from prettytable import PrettyTable

from utils import Graph, get_d_data, check_word


def main():
    logging.basicConfig(level=logging.INFO, format='%(levelname)s | %(message)s')
    alphabet = []
    print("Введите алфавит. Напишите 'end' для завершения ввода")
    while True:
        value = input("Введите символ: ")
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

    # -------------- Конечные вершины --------------
    print("Введите конечные вершины. Напишите 'end' для завершения ввода")
    end_vertex = []
    while True:
        vertex = input("Введите конечную вершину: ")
        if vertex.replace(" ", "").lower() == 'end':
            break
        if vertex not in [f"S{i}" for i in range(count_of_vertex)]:
            print("Введена не вершина")
            continue
        end_vertex.append(vertex)

    # ----------------- Ввод таблицы -----------------
    print("Введите переходы в формате: S0,S1,S2")
    table_data: list[dict[str, list]] = []
    for i in range(count_of_vertex):
        row = []
        for j in range(len(alphabet)):
            value = input(f"Введите переход из S{i} по {alphabet[j]}: ").replace(" ", "").split(",")
            if value == ['-']:
                value = None
            row.append(value)
        table_data.append({"from": f'S{i}', "to": row})

    # ----------------- Вывод таблицы -----------------
    table = PrettyTable()
    table.field_names = [" "] + alphabet
    for row in table_data:
        row = [row["from"]] + row["to"]
        for i in range(len(row)):
            if row[i] is None:
                row[i] = "∅"
            if type(row[i]) is list:
                row[i] = ", ".join(row[i])
        table.add_row(row)
    print(table)

    # -------------- Начальные вершины --------------
    start_vertex: list[str] = []
    for row in table_data:
        is_start = True
        for row2 in table_data[1:]:
            for list_of_el in row2["to"]:
                if list_of_el is None:
                    continue
                if row["from"] in list_of_el:
                    is_start = False
                    break
        if is_start:
            start_vertex.append(row["from"])
    print(f"Начальные вершины: {start_vertex}")

    # ----------------- Вывод графа -----------------
    graph = Graph()
    for row in table_data:
        for i in range(len(row["to"])):
            list_of_el = row["to"][i]

            if list_of_el is None:
                continue

            for el in list_of_el:
                graph.add_edge(row["from"], el, alphabet[i])

    for s in start_vertex:
        graph.style_node(s, node_color="aqua", node_size=1000)
    graph.draw()

    # ----------- Детерминированный автомат -----------
    p_start_vertex, p_end_vertex, p_table_data = get_d_data(
        start_vertex=start_vertex,
        end_vertex=end_vertex,
        alphabet=alphabet,
        table_data=table_data
    )

    # ----------------- Вывод таблицы -----------------
    table = PrettyTable()
    table.field_names = [" "] + alphabet
    rows: list[list] = []
    count_of_rows = len(p_table_data) // len(alphabet)
    for i in range(count_of_rows):
        row = [f"P{i}"]
        for el in [_ for _ in p_table_data if _["from"] == f"P{i}"]:
            row.append(el["mask"])
        rows.append(row)
        table.add_row(row)
    print(table)

    # ----------------- Вывод графа -----------------
    graph = Graph()
    for row in rows:
        for i in range(len(row)):
            if i == 0:
                continue
            if row[i] == "∅":
                continue
            graph.add_edge(row[0], row[i], label=alphabet[i - 1])

    for p in p_start_vertex:
        graph.style_node(p, node_color="green", node_size=1000, alpha=0.2)

    for p in p_end_vertex:
        graph.style_node(p, node_color="red", node_size=1000, alpha=0.2)
    graph.draw()

    # -------------- Допускает ли автомат слово -------------------
    print("\n\n Проверка: Допускает ли автомат введенное слово ('end' чтобы закончить)\n\n")

    while True:
        input_value = input("Введите слово: ")
        if input_value.replace(" ", "").lower() == 'end':
            break

        if check_word(input_value, p_start_vertex, p_end_vertex, p_table_data):
            print("ДА")
        else:
            print("НЕТ")


if __name__ == '__main__':
    main()

```

</details>
 <details>
  <summary>Вывод программы</summary>
  
  ![изображение](https://user-images.githubusercontent.com/76239707/229886803-544b4fc9-9660-4a4f-a44f-23d49c450dab.png)  
   
  ![изображение](https://user-images.githubusercontent.com/76239707/229886823-84e29267-59e5-46b8-85c8-a16725e49856.png)


```bash

Введите алфавит. Напишите 'end' для завершения ввода
Введите символ: a
Введите символ: b
Введите символ: end
Введите кол-во вершин: 4
Введите конечные вершины. Напишите 'end' для завершения ввода
Введите конечную вершину: S2
Введите конечную вершину: S3
Введите конечную вершину: end
Введите переходы в формате: S0,S1,S2
Введите переход из S0 по a: S2, S3
Введите переход из S0 по b: S2
Введите переход из S1 по a: S2, S3
Введите переход из S1 по b: S2
Введите переход из S2 по a: S2
Введите переход из S2 по b: -
Введите переход из S3 по a: S2
Введите переход из S3 по b: S2, S3
+----+--------+--------+
|    |   a    |   b    |
+----+--------+--------+
| S0 | S2, S3 |   S2   |
| S1 | S2, S3 |   S2   |
| S2 |   S2   |   ∅    |
| S3 |   S2   | S2, S3 |
+----+--------+--------+

Начальные вершины: ['S0', 'S1']
INFO | > P0 = ['S0', 'S1']
INFO | 	(S0; a)-> ['S2', 'S3']
INFO | 	(S1; a)-> ['S2', 'S3']
INFO | (P0; a)-> ['S2', 'S3'] == P1
INFO | 	(S0; b)-> ['S2']
INFO | 	(S1; b)-> ['S2']
INFO | (P0; b)-> ['S2'] == P2
INFO | > P1 = ['S2', 'S3']
INFO | 	(S2; a)-> ['S2']
INFO | 	(S3; a)-> ['S2']
INFO | (P1; a)-> ['S2'] == P2
INFO | 	(S3; b)-> ['S2', 'S3']
INFO | (P1; b)-> ['S2', 'S3'] == P1
INFO | > P2 = ['S2']
INFO | 	(S2; a)-> ['S2']
INFO | (P2; a)-> ['S2'] == P2
INFO | (P2; b)-> ∅
+----+----+----+
|    | a  | b  |
+----+----+----+
| P0 | P1 | P2 |
| P1 | P2 | P1 |
| P2 | P2 | ∅  |
+----+----+----+


 Проверка: Допускает ли автомат введенное слово ('end' чтобы закончить)


Введите слово: a
ДА
Введите слово: ab
ДА
Введите слово: b
ДА
Введите слово: aba
ДА
Введите слово: abaa
ДА
Введите слово: aa
ДА
Введите слово: bb
НЕТ
Введите слово: ba
ДА
Введите слово: abaaaaa
ДА
Введите слово: abbbb
ДА
Введите слово: abbbbaaaa
ДА
Введите слово: end

Process finished with exit code 0

```

</details>

### Пункты 1-6 (Полная реализация)

<details>
  <summary>utils.py</summary>


</details>
<details>
  <summary>main.py</summary>


</details>


*Авторство: **Бояршинов Н.О***
