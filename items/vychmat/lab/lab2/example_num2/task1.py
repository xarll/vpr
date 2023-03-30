"""
Классная работа

Метод половинного деления

"""
import dataclasses
from typing import Callable

import numpy as np
from prettytable import PrettyTable

from utils import Plot


@dataclasses.dataclass
class AnalyticalModel:
    a: float = None
    b: float = None
    x: float = None
    b_neg_x: float = None
    fx: float = None
    fa: float = None
    fb: float = None


def half_division_method(f: Callable[[float], float], a, b, eps: float) -> tuple[float, list[AnalyticalModel]] | None:
    """
    Метод половинного деления
    (обеспечивает гарантированную сходимость метода независимо от сложности функции)

    :param f: функция
    :param a: Начало интервала
    :param b: Конец интервала
    :param eps: эпсилон
    :return:
    """
    analysis: list[AnalyticalModel] = []

    if f(a) * f(b) > 0:
        return None  # => Не имеет на концах разных знаков на интервале [a, b]
    while abs(b - a) > eps:
        row = AnalyticalModel()
        row.a = a
        row.b = b
        row.fa = f(a)
        row.fb = f(b)

        x = (a + b) / 2

        if f(x) == 0:
            break

        row.x = x
        row.fx = f(x)
        row.b_neg_x = b - x

        if f(a) * f(x) < 0:
            b = x
        else:
            a = x
        analysis.append(row)
    return a, analysis


def main():
    func = lambda x: 0.5**x + 1 - (x-2)**2
    a = 0
    b = 1
    eps = 10 ** (-5) - 10 ** (-6)

    print(f"Граница: [{a}, {b}]")
    print(f"Точность: eps = {eps}")

    result = half_division_method(func, a, b, eps)

    if not result:
        print("Знак левой и правой границы совпадает, метод половинного деления невозможен")
        return 1
    print(f"Искомый корень x ≈ {round(result[0], 8)}")
    print(f"Было проведено {len(result[1])} итераций")

    table = PrettyTable()
    table.align = 'l'
    table.field_names = ["n", "a", "b", "x", "b - x", "f(x)", "f(a)", "f(b)"]

    for i in range(1, len(result[1]) + 1):
        data = result[1][i - 1]
        table.add_row([
            i,
            round(data.a, 8),
            round(data.b, 8),
            round(data.x, 8),
            format(data.b_neg_x, ".10f"),
            format(data.fx, ".8f"),
            format(data.fa, ".8f"),
            format(data.fb, ".8f"),
        ])
    print(table)

    # ---------- График функции ----------
    x = np.round(np.arange(-20, 20, 0.1), 1)
    pl = Plot(_x_lim=[-10, 10.], _y_lim=[-20, 5.])
    pl.add(function=func, _x=x)
    pl.add(_x=[a for _ in range(len(x))], _y=x, label="Граница a")
    pl.add(_x=[b for _ in range(len(x))], _y=x, label="Граница b")
    pl.add_point(result[0], 0)
    pl.show()

    # ---------- График роста x ----------
    x = [i for i in range(1, len(result[1]) + 1)]
    y = [el.x for el in result[1]]
    pl2 = Plot(_x_lim=[x[0], x[-1]], _y_lim=[0, 1], major_loc_y=0.5, minor_loc_y=0.1)
    pl2.add(_x=x, _y=y, label="График зависимости x от числа итераций")
    pl2.show()


if __name__ == "__main__":
    main()
