# Лексикографические преобразования

Из слова в цифру, из цифры в слово

## Алгоритм (ручная запись)

```

Пустому слову присв. номер "0"
А буквам a1, ... an из алфавита E
1, 2, ... n - соответственно.

Если слово X имеет лексикографический номер
lx, то слово Xai имеет лексикографич номер lx^(n + i),
отсюда следует, что лексикографич номер для слова
ai1, ai2, ... aik имеет вид: n^(k-1) * i1 + n^(k-2) * i2 ... ik (формула #1)

Наоборот:
Если дано число N >= 0, то если N ∈ [0, n], то оно служит
номером буквы алфавита или пустого символа при N = 0.
В противном случае (N > n) записываем N = k1 n + r0, r1 ∈ [1, n]

Если k1 <= n, то N является номером слова ak1 ar0.
В противном случае k1 = k2 n + r1, r1 ∈ [1, n]
N = (k2 n + r1)n + r0 - k2 n^2 + r1 n + r0

Если k2 <= n, то N является номером слова ak2 ar1 ar0.
В противном случае k2 = k3 n + r2.
Продолжаем этот процесс до тех пор, пока k1 не станет <= n.
В результате получим ф-лу аналогичнйо ф-ле 1.
```

Ps: ручная запись может быть неточной 

### Пример входных данных:
```
>>> alphabet = ['a', 'b']
>>> N = 321
```
### Пример выходных данных:
```
Формула: 2^7 * 1 + 2^6 * 2 + 2^5 * 1 + 2^4 * 1 + 2^3 * 1 + 2^2 * 1 + 2^1 * 2 + 2^0 * 1
Слово: abaaaaba
```

## Реализация

### Нахождение номера по слову:
```python
def get_number(word: str, alphabet: list[str]) -> int:
    # Кол-во символов в алфавите
    n = len(alphabet)
    # Кол-во символов в слове
    k = len(word)

    logging.debug(f" n = {n}, k = {k}")
    debug_formula = " "

    result = 0
    for i in range(1, k + 1):
        char = word[i - 1]
        result += (n ** (k - i)) * (alphabet.index(char) + 1)
        debug_formula += f"{n}^{k - i} * {alphabet.index(char) + 1} + "

    debug_formula = debug_formula[:-3] + f" = {result}"
    logging.debug(debug_formula)  # Опционально - вывод в логи формулу

    return result
    
```

В данном случае результатом функции является номер слова. В качестве дополнения реализована переменная debug_formula - вывод формулы (1)

### Нахождение слова по номеру
В данном случае код выглядит намного масштабнее, чем функция нахожденя слова, что представлена выше. 
Это было сделано за счет подробного вывода решения, что наверняка Вам пригодится

```python
def get_word(number: int, alphabet: list) -> str:
    # Кол-во символов в алфавите
    n = len(alphabet)
    
    # Если номер <= длине алф, то он либо индекс буквы, либо пустой символ
    if number <= n:
        if number == 0:
            return '*Пустой символ*'
        return alphabet[number]
    
    # Выведем входные данные и определим шаблоны для вывода лога подробного решения
    logging.debug(f" n = {n}, number = {number}")
    debug_formula_template = "({k}) * {n} + {remainder} "
    debug_formula = debug_formula_template
    debug_left_part = ""
    
    # Индексы слов в алфавите, причем [1, n]
    char_index_list = []

    k = number
    while True:

        # Остаток от деления должен быть на [1, n]
        if k % n == 0:
            k = (k // n) - 1
            remainder = n
        else:
            remainder = k % n
            k = k // n

        char_index_list.append(remainder)
        if k > n:
            debug_left_part += debug_formula.format(k=k, n=n, remainder=remainder) + " = "
            debug_formula = debug_formula.format(k=debug_formula_template, n=n, remainder=remainder)
        else:
            char_index_list.append(k)
            debug_formula = debug_formula.format(k=k, n=n, remainder=remainder)
            break

    char_index_list.reverse()
    
    # Вывод промежуточных результатов
    logging.debug(f" Номера букв в алф: {char_index_list}")
    logging.debug(debug_left_part + debug_formula)

    # Формирование конечной формулы и слова
    debug_end_formula = ""
    word = ""
    # Кол-во букв в слове
    k = len(char_index_list)
    for index in range(len(char_index_list)):
        i = char_index_list[index]
        word += alphabet[i - 1]
        k = k - 1
        debug_end_formula += f"{n}^{k} * {i} + "
    
    debug_end_formula = debug_end_formula[:-3]
    # Вывод конечного слова
    logging.debug(debug_end_formula)

    return word

```
Ps: выражения ввида `logging.debug(...)` можно заменить на `print(...)` или вовсе не использовать

Пример вывода:
![image](https://user-images.githubusercontent.com/76239707/219405564-94880c4e-260e-4225-ae5e-49764afbc020.png)


*Авторство: **Бояршинов Н.О***





