"""
Copyright JKearnsl 2023

"""

import inspect
from typing import Callable
import matplotlib.pyplot as plt
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
            _x: list[float | int],
            function: Callable[[float | int], float | int] = None,
            _y: list[float | int] = None,
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
