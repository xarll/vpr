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
                    _ = [el for el in exclude_rels if el["dest_vertex"] == dest_vertex]
                    el_in_exclude_rels = None if not _ else _[0]

                    if el_in_exclude_rels:
                        el_in_exclude_rels["vertex"].append(vertex)
                    else:
                        exclude_rels.append(dict(vertex=[vertex], _class=dest_class, dest_vertex=dest_vertex))

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
