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
