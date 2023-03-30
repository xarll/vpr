# Лабораторная работа #2

## Список функций и задания
<details>
  <summary>Фото</summary>
  
  ![image](https://user-images.githubusercontent.com/76239707/228766615-63a33d47-2620-4221-949e-89b2fa5e0a01.png)
  
</details>
 
 Скачать [.docx](https://github.com/xarll/vpr/raw/main/items/vychmat/lab/lab2/Laboratornaya_rabota_2%20(1).docx)


### Примеры готовой работы:

Проект на питоне: [тык](./example_num2)
Проект на MathCAD: [.xmcd]()


### Метод половинного деления (МПД)

<details>
  <summary>Модуль: task1.py</summary>
  
  
```Python
"""
Классная работа

Метод половинного деления

"""
import dataclasses
import math
from typing import Callable

import numpy as np
import sympy
from prettytable import PrettyTable

from utils import Plot, ctg


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

    if math.isnan(f(a)) or math.isnan(f(b)):
        return None  # => Одна из границ не определена

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
    # func = lambda x: ctg(x) - (x/5) - 1
    a = 0  # 0.1
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
    # step = 0.001
    # x = np.arange(-5 * np.pi, 5 * np.pi + step, step)

    pl = Plot(_x_lim=[-10, 10.], _y_lim=[-20, 5.])
    pl.add(function=func, _x=x)
    pl.add(_x=[a for _ in range(len(x))], _y=x, label="Граница a")
    pl.add(_x=[b for _ in range(len(x))], _y=x, label="Граница b")
    pl.add_point(result[0], 0)
    pl.show()

    # ---------- График роста x ----------
    x = [i for i in range(1, len(result[1]) + 1)]
    y = [el.x for el in result[1]]
    pl2 = Plot(_x_lim=[x[0], x[-1]], _y_lim=[0, 1], major_loc_y=0.1, minor_loc_y=0.01)
    pl2.add(_x=x, _y=y, label="График зависимости x от числа итераций")
    pl2.show()


if __name__ == "__main__":
    main()

```
</details>

<details>
  <summary>Модуль: utils.py </summary>
 
  
  ```Python
  """
Copyright JKearnsl 2023

"""

import inspect
import math
from typing import Callable, Sequence
import matplotlib.pyplot as plt
import numpy as np
import sympy
from matplotlib import ticker


class Plot:

    def __init__(
            self,
            _x_lim: list[float | int] = None,
            _y_lim: list[float | int] = None,
            major_loc_x: int | float = 1,
            minor_loc_x: int | float = 0.5,
            major_loc_y: int | float = 1,
            minor_loc_y: int | float = 0.5

    ):
        self.fig, self.ax = plt.subplots()

        if _x_lim is None:
            _x_lim = [-5., 5.]

        if _y_lim is None:
            _y_lim = [-5., 5.]

        self.fig.set_figwidth(10)
        self.fig.set_figheight(10)

        # установка положения осей координат
        self.ax.spines['left'].set_position('zero')
        self.ax.spines['bottom'].set_position('zero')
        self.ax.spines['right'].set_color('none')
        self.ax.spines['top'].set_color('none')

        self.ax.set_xlabel(r'x', fontsize=15, loc='right')
        self.ax.set_ylabel(r'y', fontsize=15, loc='top')

        self.ax.set_xlim(_x_lim)
        self.ax.set_ylim(_y_lim)

        # Настройка сетки
        self.ax.minorticks_on()

        self.ax.grid(which='major')
        self.ax.grid(which='minor', linestyle=':')

        self.ax.xaxis.set_major_locator(ticker.MultipleLocator(major_loc_x))
        self.ax.xaxis.set_minor_locator(ticker.MultipleLocator(minor_loc_x))
        self.ax.yaxis.set_major_locator(ticker.MultipleLocator(major_loc_y))
        self.ax.yaxis.set_minor_locator(ticker.MultipleLocator(minor_loc_y))

    def add(
            self,
            _x: Sequence[float | int],
            function: Callable[[float | int], float | int] = None,
            _y: Sequence[float | int] = None,
            label: str = None

    ) -> None:

        if _y is None:
            assert function, ValueError("Или funс или y обязаны присутствовать в аргументах функции make_plot")
            _y = [function(x) for x in _x]

        if not label:
            if function:
                source_code = inspect.getsource(function)
                label = f'График функции ${source_code[source_code.find(":") + 1:].strip()}$'
            else:
                label = f"func: [{_x[0]}, {_x[-1]}], [{_y[0]}, {_y[-1]}]"

        self.ax.plot(_x, _y, label=label)  # график ф-ции

    def add_point(self, x: float | int, y: float | int, label: str = None, color: str = None, size: int = None) -> None:
        if color is None:
            color = 'blue'
        if size is None:
            size = 20
        self.ax.scatter(x, y, c=color, s=size, label=label)

    def show(self) -> None:
        self.ax.legend(fontsize=16)
        plt.show()


def is_pi_like(x: float) -> bool:
    tolerance = 1e-3
    return np.abs(x / np.pi - np.round(x / np.pi)) < tolerance


def ctg(x: int | float) -> float:
    if is_pi_like(x):
        return math.nan

    tan_x = math.tan(x)
    if tan_x == 0:
        return math.nan
    else:
        return 1 / tan_x

  ```
</details>
  
### Метод простых итераций (МПИ)

<details>
  <summary>Модуль: task2.py</summary>
  
  
```Python
```
</details>
  
<details>
  <summary>Модуль: utils.py</summary>
  
  
```Python
```
</details>
  
### Метод секущих

<details>
  <summary>Модуль: task3.py</summary>
  
  
```Python
```
</details>
  
<details>
  <summary>Модуль: utils.py</summary>
  
  
```Python
```
</details>

##### Python зависимости:
- `numpy` для работы с массивами
- `prettytable` для работы с красивыми таблицами :)
- `matplotlib` для работы с графиками
- `sympy` для математики



*Авторство: **Бояршинов Н.О***
