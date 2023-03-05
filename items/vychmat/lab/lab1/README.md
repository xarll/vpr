# Лабораторная работа #1

## Вступление

Вам предстоит исследовать данную Вам функцию, используя MathCAD 
или ЯП. В данной работе я буду использовать Python, но также 
прикреплю чужой .xmcd файл другого варианта

## Список функций и задания
<details>
  <summary>Фото</summary>

  ![image](https://user-images.githubusercontent.com/76239707/222967707-5886504e-2609-455b-81b9-6b34dec97a95.png)
  ![image](https://user-images.githubusercontent.com/76239707/222967719-e9a3cf8c-9e58-4a20-a876-d25ae4014d43.png)
  ![image](https://user-images.githubusercontent.com/76239707/222967724-7a508a39-2871-45a2-8691-a2f54166599f.png)
  ![image](https://user-images.githubusercontent.com/76239707/222967732-50c6553c-13e8-40f8-b457-70b690bcb961.png)
</details>

### Пример работы на MathCAD

[.xmcd](./example_mathcad.xmcd)


### Реализация варианта 2 на Python
<details>
  <summary>Task 1</summary>
  
```Python
import numpy as np
import matplotlib.pyplot as plt
import matplotlib
from matplotlib import ticker

matplotlib.rcParams.update({'font.size': 15})


def setup_plot_settings(fig, ax):
    fig.set_figwidth(10)
    fig.set_figheight(10)

    ax.set_xlabel(r'x', fontsize=15)
    ax.set_ylabel(r'f(x)', fontsize=15)

    ax.set_xlim([-5., 5.])
    ax.set_ylim([-5., 5.])

    ax.legend(fontsize=16)
    ax.minorticks_on()

    ax.grid(which='major')
    ax.grid(which='minor', linestyle=':')

    ax.xaxis.set_major_locator(ticker.MultipleLocator(1))
    ax.xaxis.set_minor_locator(ticker.MultipleLocator(0.5))
    ax.yaxis.set_major_locator(ticker.MultipleLocator(1))
    ax.yaxis.set_minor_locator(ticker.MultipleLocator(0.5))


# ------------- 1 -------------

x = np.round(np.arange(-10, 10, 0.1), 1)
# Функция
y_1 = 2 * x / (1 - x ** 2)

# Производная функции y_1
dx = x[1] - x[0]
dydx = np.gradient(y_1, dx)

fig, ax = plt.subplots()

# График
ax.plot(x, y_1, label=r'$f_1(x)=2x/(1 - x^2)$')
ax.plot(x, dydx, label=r"$f_1'(x)=(2 + 2x^2)/(1 - x^2)^2$")

# Установки
setup_plot_settings(fig, ax)
fig.suptitle('График функции и ее первой производной')

# Асимптоты
ax.plot(x, [0 for a in range(len(x))], ':r')  # y = 0 - горизонтальная асимптота
ax.plot([-1 for b in range(len(x))], x, ':b')  # x = 1 - вертикальная асимптота
ax.plot([1 for c in range(len(x))], x, ':b')  # x = -1 - вертикальная асимптота

ax.plot(x, [0 for g in range(len(x))], color='black')  # y = 0 - ось x

plt.show()

# ------------- 2 -------------

# Вторая Производная функции y_1
d2ydx2 = np.gradient(dydx, dx)
fig, ax = plt.subplots()

# График
ax.plot(x, y_1, label=r'$f_1(x)=2x/(1 - x^2)$')
ax.plot(x, d2ydx2, label=r"$f_1''(x)=(12x + 4x^3)/(1 - x^2)^3$")

# Установки
setup_plot_settings(fig, ax)
fig.suptitle('График функции и ее второй производной')

# Асимптоты
ax.plot(x, [0 for d in range(len(x))], ':r')  # y = 0 - горизонтальная асимптота
ax.plot([-1 for e in range(len(x))], x, ':b')  # x = 1 - вертикальная асимптота
ax.plot([1 for f in range(len(x))], x, ':b')  # x = -1 - вертикальная асимптота

plt.show()

y_1_break_points = x[np.where(np.abs(y_1) == np.inf)]

print(
    "1) Область определения функции f(x) = 2x/(1 - x^2): ",
    f"    [ х | {''.join(f'x != {point}, ' for point in y_1_break_points)}х ∈ R]",
    sep="\n",
    end="\n\n"
)

print(
    "2) Исследование функции на четность/нечетность:",
    "    f(x) = 2x/(1 - x^2)",
    "    f(-x) = 2(-x)/(1 - (-x)^2) = - 2x/(1 - x^2)",
    "    f(-x) = - f(x)",
    "    Функция f(x) является нечетной",
    sep="\n",
    end="\n\n"
)

print(
    "3) Точки пересечения графика функции с осями координат:",
    "     f(x) = 2x/(1 - x^2)",
    "     f(x) = 0: x = 0",
    "     f(0) = 0: 2 * 0 / (1 - 0^2) = 0",
    "     Точка пересечения: (0, 0)",
    sep="\n",
    end="\n\n"
)

print(
    "4) Исследование функции на непрерывность:",
    "    f(x) = 2x/(1 - x^2)",
    f"    Точки разрыва: {', '.join(f'{point}' for point in y_1_break_points)}",
    "    f(-1) = 2 * (-1) / (1 - (-1)^2) = -2/0 = -inf",
    "    f(1) = 2 * 1 / (1 - 1^2) = 2/0 = inf",
    "    Функция f(x) не является непрерывной т.к есть точки разрыва\n",
    "    Определим род точек разрыва:",
    "    - Для точки разрыва x= -1:",
    "        При x -> -1-0, f(x) -> +inf",
    "        При x -> -1+0, f(x) -> -inf",
    "    - Для точки разрыва x= 1:",
    "        При x -> 1-0, f(x) -> +inf",
    "        При x -> 1+0, f(x) -> -inf",
    "    Таким образом, обе точки разрыва являются точками разрыва второго рода.\n",
    "    Функция имеет 2 вертикальные асимптоты x = -1 и x = 1 (голубые штриховые линии)",
    sep="\n",
    end="\n\n"
)

print(
    "5) Исследование поведения функции в бесконечности:",
    "     f(x) = 2x/(1 - x^2)",
    "     при x → ±inf, f(x) -> 0",
    "     => у функции есть горизонтальная асимптота y = 0 при x → ±inf (красная штриховая линия).\n",

    sep="\n",
    end="\n\n"
)

print(
    "6) Наклонные асимптоты:",
    "     f(x) = 2x/(1 - x^2)",
    "     при x → ±inf, f(x)/x -> 0"
    "     f(x) не имеет наклонных асимптот, "
    "так как ее график не стремится к какой-либо наклонной прямой "
    "при приближении аргумента x к бесконечности",
    "     => функция не имеет наклонных асимптот",
    sep="\n",
    end="\n\n"
)

print(
    "7) Первая производная, интервалы возрастания/убывания, экстремумы:",
    "     f(x) = 2x/(1 - x^2)",
    "     f'(x) = (2 + 2x^2)/(1 - x^2)^2",
    "",
    "     Интервалы убывания возрастания f(x):",
    "     Так как f'(x) положительна на всем интервале определения, то",
    "     -inf < x < -1: f(x) возрастает",
    "     -1 < x < 1: f(x) возрастает",
    "     1 < x < +inf: f(x) возрастает",
    "",
    "     Так как f'(x) != 0 на всем интервале определения, то",
    "     Нет глобальных экстремумов функции f(x)",
    sep="\n",
    end="\n\n"
)

print(
    "8) Вторая производная, интервалы выпуклости/вогнутости, точки перегиба:",
    "     f(x) = 2x/(1 - x^2)",
    "     f''(x) = (12x + 4x^3)/(1 - x^2)^3",
    "",
    "     Интервалы выпуклости/вогнутости f(x):",
    "     -inf < x < -1: f''(x)+ => вогнута ВНИЗ",
    "     -1 < x < 0: f''(x)- => выпукла ВВЕРХ",
    "     0 < x < 1: f''(x)+ => вогнута ВНИЗ",
    "     1 < x < +inf: f''(x)+ => выпукла ВВЕРХ",
    "",
    "     Точки перегиба функции f(x):",
    "     f''(x) = 0",
    "     (12x + 4x^3)/(1 - x^2)^3 = 0",
    "     x = 0",
    "     Точка перегиба: (0, 0)",
    sep="\n",
    end="\n\n"
)  
```
</details>

<details>
  <summary>Task 2</summary>
  
  ```Python
  import numpy as np
  import matplotlib.pyplot as plt

  a = 15
  t = np.linspace(0, 2 * np.pi, 1000)

  x = 2 * a * np.cos(t) - a * np.cos(2 * t)
  y = 2 * a * np.sin(t) - a * np.sin(2 * t)

  plt.plot(x, y)
  plt.title("Кардиоида")
  plt.grid()
  plt.show()
  ```
</details>

<details>
  <summary>Task 3</summary>
  
```Python
import numpy as np
import matplotlib.pyplot as plt

a = 1
fi = np.linspace(0, 4 * np.pi, 100)

r = a * np.cos(4 * fi)

plt.polar(fi, r)
plt.title("Четырехлистная роза")
plt.grid()
plt.show()
```
</details>

#### Несколько слов про сдачу лаб

Вам предстоит полностью осознать что вы делаете, ибо вы будете обьяснять всё что происходит по пунктно
