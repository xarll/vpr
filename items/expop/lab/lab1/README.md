# Лабораторная работа №1. Решение транспортной задачи.

Необходимо произвести расчёт транспортной задачи согласно своему варианту методом северо-западного угла и оптимизировать полученный план методом потенциалов.

После этого необходимо разработать программное средство для реализации решения транспортной задачи согласно этому плану.

## Варианты заданий
<details>

  <summary>Таблицы</summary>
  
Вариант 1. 
bj
ai	b1 = 120	b2 = 120	b3 = 200	b4 = 180	b5 = 110
а1 = 200	1	2	3	5	2
а2 = 150	4	6	7	3	1
а3 = 350	2	2	3	4	5

Вариант 2. 
bj
ai	b1 = 140	b2 = 110	b3 = 170	b4 = 90	b5 = 140
а1 = 250	4	3	4	5	3
а2 = 200	2	4	5	7	8
а3 = 220	4	3	7	2	1

Вариант 3. 
bj
ai	b1 = 110	b2 = 140	b3 = 220	b4 = 190	b5 = 120
а1 = 180	2	4	5	8	6
а2 = 300	7	3	6	4	5
а3 = 230	8	5	6	5	3

Вариант 4. 
bj
ai	b1 = 160	b2 = 120	b3 = 140	b4 = 200	b5 = 170
а1 = 300	1	4	2	1	3
а2 = 250	6	2	3	5	1
а3 = 200	2	3	4	1	4

Вариант 5. 
bj
ai	b1 = 110	b2 = 200	b3 = 90	b4 = 100	b5 = 120
а1 = 100	5	2	3	6	1
а2 = 300	1	1	4	4	2
а3 = 150	4	1	2	3	5

Вариант 6. 
bj
ai	b1 = 100	b2 = 120	b3 = 100	b4 = 200	b5 = 300
а1 = 150	5	1	2	3	4
а2 = 320	7	8	1	1	2
а3 = 400	4	1	3	1	2

Вариант 7. 
bj	b1 = 100	b2 = 100	b3 = 80	b4 = 90	b5 = 70
ai					
а1 = 200	1	4	5	3	1
а2 = 350	2	3	1	4	2
а3 = 150	2	1	3	1	1

Вариант 8. 
bj
ai	b1 = 100	b2 = 90	b3 = 200	b4 = 30	b5 = 80
а1 = 200	1	2	4	1	5
а2 = 120	1	2	1	3	1
а3 = 150	2	1	3	3	1

Вариант 9. 
bj
ai	b1 =100 	b2 = 120	b3 = 130	b4 = 100	b5 = 90
а1 = 300	1	4	5	3	1
а2 = 120	2	1	2	1	2
а3 = 300	3	1	4	2	1

Вариант 10. 
bj
ai	b1 = 100	b2 = 20	b3 = 70	b4 = 90	b5 = 180
а1 = 300	1	4	2	1	2
а2 = 90	2	2	3	1	3
а3 = 70	3	4	5	6	7

Вариант 11. 
bj
ai	b1 =100 	b2 = 200	b3 = 130	b4 = 180	b5 = 110
а1 = 150	1	4	7	2	1
а2 = 200	2	5	1	4	3
а3 = 170	46	27	36	40	45

Вариант 12. 
bj
ai	b1 = 120	b2 = 130	b3 = 200	b4 = 180	b5 = 110
а1 = 200	1	4	7	8	1
а2 = 150	2	3	1	4	1
а3 = 35	5	1	3	2	3

Вариант 13. 
bj
ai	b1 = 140	b2 = 110	b3 = 170	b4 = 90	b5 = 140
а1 = 250	1	2	3	5	2
а2 = 200	4	6	7	3	1
а3 = 220	3	2	3	4	5

Вариант 14. 
bj
ai	b1 = 110	b2 = 130	b3 = 200	b4 = 180	b5 = 110
а1 = 200	2	4	5	8	6
а2 = 150	7	3	6	4	5
а3 = 350	8	2	3	4	5

Вариант 15. 
bj
ai	b1 = 100	b2 = 120	b3 = 140	b4 = 200	b5 = 170
а1 = 300	1	4	2	1	3
а2 = 250	6	2	3	5	1
а3 = 200	2	3	4	1	4

Вариант 16. 
bj
ai	b1 = 100	b2 = 120	b3 = 100	b4 = 200	b5 = 300
а1 = 150	2	5	3	6	1
а2 = 320	1	1	4	4	2
а3 = 400	4	1	2	3	5

Вариант 17. 
bj
ai	b1 = 100	b2 = 100	b3 = 80	b4 = 90	b5 = 70
а1 = 200	1	4	7	2	1
а2 = 350	2	5	1	4	3
а3 = 150	2	3	1	2	1

Вариант 18. 
bj
ai	b1 = 100	b2 = 120	b3 = 100	b4 = 200	b5 = 300
а1 = 150	1	4	2	1	3
а2 = 320	6	2	3	5	1
а3 = 400	2	3	4	1	4

Вариант 19. 
bj
ai	b1 = 180	b2 = 110	b3 = 70	b4 = 90	b5 = 170
а1 = 250	1	2	4	1	5
а2 = 200	1	2	1	3	1
а3 = 220	2	1	3	3	1

Вариант 20. 
bj
ai	b1 = 110	b2 = 170	b3 = 200	b4 = 180	b5 = 110
а1 = 200	5	2	3	6	1
а2 = 150	1	1	4	4	2
а3 = 350	4	3	1	2	1

Вариант 21. 
bj
ai	b1 = 100	b2 = 20	b3 = 70	b4 = 100	b5 = 180
а1 = 300	1	4	7	2	3
а2 = 90	1	5	3	1	6
а3 = 70	2	1	3	1	4

Вариант 22. 
bj
ai	b1 = 100	b2 = 120	b3 = 90	b4 = 70	b5 = 80
а1 = 300	1	4	1	5	6
а2 = 250	1	3	1	1	2
а3 = 200	4	1	2	2	3

Вариант 23. 
bj
ai	b1 = 110	b2 = 80	b3 = 100	b4 = 90	b5 = 70
а1 = 350	1	5	1	7	1
а2 = 200	2	3	1	8	3
а3 = 150	6	7	9	10	8

Вариант 24. 
bj
ai	b1 = 110	b2 = 90	b3 = 100	b4 = 80	b5 = 200
а1 = 270	1	4	7	9	1
а2 = 300	2	3	1	2	4
а3 = 100	5	6	7	1	2

Вариант 25. 
bj
ai	b1 = 40	b2 = 80	b3 = 100	b4 = 150	b5 = 200
а1 = 250	1	5	1	3	1
а2 = 300	2	4	7	1	3
а3 = 150	2	4	5	6	1

Вариант 26. 
bj
ai	b1 = 100	b2 = 100	b3 = 80	b4 = 70	b5 = 90
а1 = 200	1	4	2	3	1
а2 = 150	2	1	7	8	1
а3 = 200	2	1	3	1	4

Вариант 27. 
bj
ai	b1 = 110	b2 = 80	b3 = 100	b4 = 90	b5 = 70
а1 = 250	1	4	7	9	1
а2 = 300	2	3	1	2	4
а3 = 150	2	1	3	1	4

Вариант 28. 
bj
ai	b1 = 100	b2 = 120	b3 =90 	b4 = 70	b5 = 80
а1 = 200	1	4	7	9	1
а2 = 100	1	3	1	1	2
а3 = 160	4	1	2	3	1

Вариант 29. 
bj
ai	b1 = 100	b2 = 120	b3 = 90	b4 = 70	b5 = 80
а1 = 350	1	5	1	7	1
а2 = 200	3	2	1	8	3
а3 = 150	6	7	9	1	3

Вариант 30. 
bj
ai	b1 = 100	b2 = 90	b3 = 80	b4 = 70	b5 = 200
а1 = 200	1	4	7	9	1
а2 = 200	2	3	1	2	4
а3 = 140	2	4	5	6	1


</details>  



## Реализация

### Программный код


<details>
  <summary>main.py</summary>
  
  ```Python
import utils
import linear_transport as lt


def main() -> None:
    # Вставьте сюда размерность своей таблицы:
    # (m - количество строк, n - количество столбцов)
    m, n = 3, 5

    # Вставьте сюда значения предложений производителей:
    A = [200, 350, 150]

    # Вставьте сюда значения спроса потребителей:
    B = [100, 100, 80, 90, 70]

    # Вставьте сюда значения тарифов перевозок:
    C = [[1, 4, 7, 2, 1], [2, 5, 1, 4, 3], [2, 3, 1, 2, 1]]

    print(f'Размер таблицы: {m} x {n}')
    print('Предложения производителей:', A)
    print('Спрос потребителей:', B)
    print('Тарифы перевозок:')
    utils.print_matrix(C)
    print()

    transport = lt.TransportationTable(m, n)
    transport.producers = A
    transport.consumers = B
    for i in range(m):
        for j in range(n):
            transport.get_element(i, j).cost = C[i][j]

    if not transport.check_balance():
        print('Исходная задача не закрыта')
        transport.balance()
        print('Закрытая задача:')
        print(f'Размер таблицы: {transport.dimension_rows} x {transport.dimension_columns}')
        print('Предложения производителей:', transport.producers)
        print('Спрос потребителей:', transport.consumers)
        print('Тарифы перевозок:')
        utils.print_transportations_cost(transport)
    else:
        print('Исходная задача закрыта')
    print()

    transport.north_west_corner()
    print('Опорный план, полученный методом северо-западного угла:')
    utils.print_transportations_quantity(transport)
    print(f'Стоимость перевозок согласно плану: {transport.calculate_transportations_cost()}')
    print()

    if transport.check_degeneracy():
        print('Полученный план - вырожденный')
        transport.remove_degeneracy()
        print('Новый опорный план:')
        utils.print_transportations_quantity(transport)
        print(f'Стоимость перевозок согласно плану: {transport.calculate_transportations_cost()}')
    else:
        print('Полученный план не является вырожденным')
    print()

    count = 0
    while not transport.potential_method():
        print(f'Оптимизация плана методом потенциалов, итерация №{count + 1}:')
        utils.print_transportations_quantity(transport)
        print(f'Стоимость перевозок согласно плану: {transport.calculate_transportations_cost()}')
        count += 1
        print()

    print('Оптимальный план, полученный методом потенциалов: ')
    utils.print_transportations_quantity(transport)
    print(f'Стоимость перевозок согласно плану: {transport.calculate_transportations_cost()}')


if __name__ == '__main__':
    main()

  ```

</details>


<details>
  <summary>utils.py</summary>
  
  ```Python
from linear_transport import TransportationTable


def print_matrix(matrix: list[list[int]]) -> None:
    for row in matrix:
        for element in row:
            print(element, end=' ')
        print()


def print_transportations_cost(table: TransportationTable) -> None:
    for row in table.transportations:
        for transportation in row:
            print(transportation.cost, end=' ')
        print()


def print_transportations_quantity(table: TransportationTable) -> None:
    for row in table.transportations:
        for transportation in row:
            if transportation.quantity != '*':
                print(int(transportation.quantity), end=' ')
            else:
                print(transportation.quantity, end=' ')
        print()

  ```
  
  </details>
  
  
  <details>
  <summary>linear_transport.py</summary>
  
  ```Python
import sys


class Transportation:
    def __init__(self, row: int, column: int, quantity: int or str = '*', cost: int = -1):
        self._row = row
        self._column = column
        self._quantity = quantity
        self._cost = cost

    @property
    def position_row(self) -> int:
        return self._row

    @property
    def position_column(self) -> int:
        return self._column

    @property
    def quantity(self) -> int:
        return self._quantity

    @quantity.setter
    def quantity(self, quantity: int) -> None:
        self._quantity = quantity

    @property
    def cost(self) -> int:
        return self._cost

    @cost.setter
    def cost(self, cost: int) -> None:
        self._cost = cost

    def __eq__(self, other) -> bool:
        return self._row == other.position_row and self._column == other.position_column and self._quantity == other.quantity and self._cost == other.cost

    def __ne__(self, other) -> bool:
        return not self == other


class TransportationTable:
    def __init__(self, rows_amount: int, columns_amount: int):
        self._rows_amount = rows_amount
        self._columns_amount = columns_amount
        self._producers: list[int] = []
        self._producers_sum: int = 0
        self._consumers: list[int] = []
        self._consumers_sum: int = 0
        self._transportations: list[list[Transportation]] = []

        for i in range(self._rows_amount):
            transportations_row = []
            for j in range(self._columns_amount):
                transportations_row.append(Transportation(i, j))
            self._transportations.append(transportations_row)

    def get_element(self, index1: int, index2: int) -> Transportation:
        return self._transportations[index1][index2]

    @property
    def dimension_rows(self) -> int:
        return self._rows_amount

    @property
    def dimension_columns(self) -> int:
        return self._columns_amount

    @property
    def producers(self) -> list[int]:
        return self._producers.copy()

    @producers.setter
    def producers(self, producers: list[list[int]]) -> None:
        if len(producers) == self._rows_amount:
            self._producers = producers.copy()
            self._producers_sum = sum(self._producers)
        else:
            raise Exception('Длина входного массива не совпадает с измерением таблицы')

    @property
    def consumers(self) -> list[int]:
        return self._consumers.copy()

    @consumers.setter
    def consumers(self, consumers: list[list[int]]) -> None:
        if len(consumers) == self._columns_amount:
            self._consumers = consumers.copy()
            self._consumers_sum = sum(self._consumers)
        else:
            raise Exception('Длина входного массива не совпадает с измерением таблицы')

    @property
    def transportations(self) -> list[list[Transportation]]:
        return self._transportations.copy()

    def check_balance(self) -> bool:
        return self._consumers_sum == self._producers_sum

    def balance(self) -> None:
        if not self.check_balance():

            if self._consumers_sum > self._producers_sum:
                self._rows_amount += 1

                self._producers.append(self._consumers_sum - self._producers_sum)

                transportation_row = []
                for j in range(self._columns_amount):
                    transportation_row.append(Transportation(self._rows_amount - 1, j, '*', 0))
                self._transportations.append(transportation_row)
            else:
                self._columns_amount += 1

                self._consumers.append(self._producers_sum - self._consumers_sum)

                for i in range(self._rows_amount):
                    self._transportations[i].append(Transportation(i, self._columns_amount - 1, '*', 0))

    def north_west_corner(self) -> None:
        producers_copy = self._producers.copy()
        consumers_copy = self._consumers.copy()

        for i in range(self._rows_amount):
            j = 0
            while producers_copy[i] != 0:
                difference = producers_copy[i] - consumers_copy[j]

                if difference >= 0:
                    if consumers_copy[j] != 0:
                        self._transportations[i][j].quantity = consumers_copy[j]
                    producers_copy[i] = difference
                    consumers_copy[j] = 0
                else:
                    self._transportations[i][j].quantity = producers_copy[i]
                    consumers_copy[j] = abs(difference)
                    producers_copy[i] = 0

                j += 1

    def _transportations_to_list(self) -> list[Transportation]:
        transportations_list = []
        for i in range(self._rows_amount):
            for j in range(self._columns_amount):
                if self._transportations[i][j].quantity != '*':
                    transportations_list.append(self._transportations[i][j])

        return transportations_list.copy()

    def _get_neighbors(self, current_transportation: Transportation,
                       transportations_list: list[Transportation]) -> list[Transportation]:
        neighbors = [Transportation(-1, -1, '*'), Transportation(-1, -1, '*')]
        for transportation in transportations_list:
            if transportation != current_transportation:
                if neighbors[0].quantity != '*' and neighbors[1].quantity != '*':
                    break

                if transportation.position_row == current_transportation.position_row \
                        and neighbors[0].quantity == '*':
                    neighbors[0] = transportation
                elif transportation.position_column == current_transportation.position_column \
                        and neighbors[1].quantity == '*':
                    neighbors[1] = transportation

        return neighbors.copy()

    def get_cycle(self, start_transportation: Transportation) -> list[Transportation]:
        path = self._transportations_to_list()
        path.insert(0, start_transportation)

        previous_length: int
        while True:
            previous_length = len(path)

            i = 0
            while True:
                if i >= len(path):
                    break
                neighbors = self._get_neighbors(path[i], path)
                if neighbors[0].quantity == '*' or neighbors[1].quantity == '*':
                    path.pop(i)
                    break
                i += 1

            if previous_length == len(path) or len(path) == 0:
                break

        cycle = []
        for i in range(len(path)):
            cycle.append(Transportation(-1, -1, '*'))
        previous_transportation = start_transportation
        for i in range(len(cycle)):
            cycle[i] = previous_transportation
            previous_transportation = self._get_neighbors(previous_transportation, path)[i % 2]

        return cycle.copy()

    def count_basis(self) -> int:
        basis_amount = 0
        for row in self._transportations:
            for transportation in row:
                if transportation.quantity != '*':
                    basis_amount += 1

        return basis_amount

    def check_degeneracy(self) -> bool:
        return self._rows_amount + self._columns_amount - 1 > self.count_basis()

    def remove_degeneracy(self):
        while self.check_degeneracy():
            zero_added = False
            for i in range(self._rows_amount):
                for j in range(self._columns_amount):
                    if self._transportations[i][j].quantity == '*':
                        zero = Transportation(i, j, sys.float_info.epsilon, self._transportations[i][j].cost)
                        if len(self.get_cycle(zero)) == 0:
                            self._transportations[i][j] = zero
                            zero_added = True
                            break

                if zero_added:
                    break

    def potential_method(self) -> bool:
        max_reduction = 0
        move = []
        leaving: Transportation
        is_null = True

        self.remove_degeneracy()

        for i in range(self._rows_amount):
            for j in range(self._columns_amount):
                if self._transportations[i][j].quantity != '*':
                    continue

                trial = Transportation(i, j, 0, self._transportations[i][j].cost)
                cycle = self.get_cycle(trial)

                reduction = 0
                lowest_quantity = sys.maxsize
                leaving_candidate: Transportation

                plus = True
                for transportation in cycle:
                    if plus:
                        reduction += transportation.cost
                    else:
                        reduction -= transportation.cost
                        if transportation.quantity < lowest_quantity:
                            leaving_candidate = transportation
                            lowest_quantity = transportation.quantity
                    plus = not plus

                if reduction < max_reduction:
                    is_null = False
                    move = cycle
                    leaving = leaving_candidate
                    max_reduction = reduction

        if not is_null:
            quantity = leaving.quantity
            plus = True
            for transportation in move:
                transportation.quantity += quantity if plus else -quantity
                if transportation.quantity == 0:
                    self._transportations[transportation.position_row][transportation.position_column] = \
                        Transportation(transportation.position_row, transportation.position_column, '*',
                                       self._transportations[transportation.position_row]
                                       [transportation.position_column].cost)
                else:
                    self._transportations[transportation.position_row][transportation.position_column] = transportation
                plus = not plus

            return False

        return True

    def calculate_transportations_cost(self) -> int:
        transportations_cost = 0
        for row in self._transportations:
            for transportation in row:
                if transportation.quantity != '*':
                    transportations_cost += transportation.cost * transportation.quantity

        return int(transportations_cost)
  
  ```
  
  </details>

*Авторство: **Прокопенко Д.О.***
