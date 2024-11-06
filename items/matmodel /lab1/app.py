import numpy as np
import matplotlib.pyplot as plt

# Определяем новую функцию для начального распределения (парабола)
def q_init(x):
    value = -1 / 100 * (x - 80) ** 2 + 1
    return max(value, 0)

# Разностные схемы (без изменений)
def left(q, i, n, u, t, h):
    q_n_i = q[n][i]
    if u > 0:
        q_n_im1 = q[n][i - 1] if (i - 1 >= 0) else 0
        return q_n_i - u * t * (q_n_i - q_n_im1) / h
    else:
        size = q.shape[1]
        q_n_ip1 = q[n][i + 1] if (i + 1 < size) else 0
        if i == 69 and n == 1:
            print(f"q_n_i: {q_n_i}, q_n_ip1: {q_n_ip1}, delta {(q_n_i - q_n_ip1)}, just {q[n][i + 1]}")
        return q_n_i - (-u) * t * (q_n_i - q_n_ip1) / h

def center(q, i, n, u, t, h):
    size = q.shape[1]
    q_n_i = q[n][i]
    q_n_ip1 = q[n][i + 1] if (i + 1 < size) else 0
    q_n_im1 = q[n][i - 1] if (i - 1 >= 0) else 0
    return q_n_i - u * t * (q_n_ip1 - q_n_im1) / (2 * h)

def kabare(q, i, n, u, t, h):
    q_n_i = q[n][i]
    if u > 0:
        q_n_im1 = q[n][i - 1] if (i - 1 >= 0) else 0
        q_nm1_im1 = q[n - 1][i - 1] if (i - 1 >= 0 and n - 1 >= 0) else 0
        return q_n_i - (q_n_im1 - q_nm1_im1) - (2 * t * u) / h * (q_n_i - q_n_im1)
    else:
        size = q.shape[1]
        q_n_ip1 = q[n][i + 1] if (i + 1 < size) else 0
        q_nm1_ip1 = q[n - 1][i + 1] if (i + 1 < size) else 0
        return q_n_i - (q_n_ip1 - q_nm1_ip1) - (2 * t * -u) / h * (q_n_i - q_n_ip1)

def combined(q, i, n, u, t, h):
    size = q.shape[1]
    q_n_i = q[n][i]
    q_n_ip1 = q[n][i + 1] if (i + 1 < size) else 0
    q_n_im1 = q[n][i - 1] if (i - 1 >= 0) else 0
    if u > 0:
        q_nm1_im1 = q[n - 1][i - 1] if (i - 1 >= 0 and n - 1 >= 0) else 0
        return q_n_i - (q_n_im1 - q_nm1_im1) / 2 - (u * t) * (q_n_ip1 + 4 * q_n_i - 5 * q_n_im1) / (4 * h)
    else:
        q_nm1_ip1 = q[n - 1][i + 1] if (i + 1 < size and n - 1 >= 0) else 0
        return q_n_i - (q_n_ip1 - q_nm1_ip1) / 2 - (-u * t) * (q_n_im1 + 4 * q_n_i - 5 * q_n_ip1) / (4 * h)

# Функция для расчета значений для каждой схемы
def paint(fname, u, t, h, n):
    x = np.arange(0, 100, h, dtype=float)
    q = np.empty([n, x.size])
    q[0] = np.array([q_init(_x) for _x in x])
    for i in range(1, n):
        for j in range(0, x.size):
            q[i][j] = fname(q, j, i - 1, u, t, h)
            print(f"[{i};{j}]{q[i][j]}", end=' ')
        print(f"\n{i}: ", end=' ')
    return x, q[n - 1]


u = -0.5   # скорость
t = 0.1    # временной шаг
h = 1.0    # пространственный шаг
T = 100    # количество шагов

x, q_left = paint(left, u, t, h, T)
_, q_center = paint(center, u, t, h, T)
_, q_kabare = paint(kabare, u, t, h, T)
_, q_combined = paint(combined, u, t, h, T)

print(x)
q_true = np.array([q_init(_x) for _x in x])

plt.figure(figsize=(10, 6))
plt.plot(x, q_true, label='Исходная функция параболы', color='black')
plt.plot(x, q_left, label='Правый уголок', color='blue', linestyle='--')
plt.plot(x, q_center, label='Центральный р/с', color='red', linestyle='-.')
plt.plot(x, q_kabare, label='Кабаре', color='green', linestyle=':')
plt.plot(x, q_combined, label='Трёхслойная разностная схема', color='purple')

plt.title('Сравнение разностных схем')
plt.xlabel('x')
plt.ylabel('q(x)')
plt.legend()
plt.grid(True)
plt.show()