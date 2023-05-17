# Грамматика

Лаб работа выполняется на листочке + программно

## Варианты заданий
<details>

  <summary>Фото</summary>
  
  ![image](https://user-images.githubusercontent.com/76239707/235680831-405cb43a-b8dd-4d53-a7c0-54ab904a9afc.png)
  ![image](https://user-images.githubusercontent.com/76239707/235680864-624a26d7-d565-49e6-b76e-0f429625956a.png)

</details>  

## Алгоритм (ручная запись)
1. 



## Реализация варианта #2

<details>

  <summary>Спойлер</summary>
  
  

</details>  


## Реализация варианта #5

<details>

  <summary>Спойлер</summary>
  
  

</details>  


## Общая программная реализация

<details>

  <summary>main.py</summary>
  
  ```python
  import utils


def main():
    print(
        "Введите терминальные символы"
        " ('end' для окончания): "
    )
    terms = []
    while True:
        term = input("> ").strip()
        if term == "end":
            break

        if len(term) == 1:
            terms.append(term)

    print(
        "Введите нетерминальные символы"
        " ('end' для окончания): "
    )
    non_terms = []
    while True:
        non_term = input("> ").strip()
        if non_term == "end":
            break

        if len(non_term) == 1:
            non_terms.append(non_term)

    start_symbol = input("Введите стартовый символ: ").strip()

    print(
        "Введите правила грамматики в формате: "
        "'A->a|b|c' или 'S -> a'"
        " ('end' для окончания): "
    )
    rules = []
    while True:
        rule = input("> ").strip()
        if rule != 'end':
            left, right = rule.split('->')
            for el in right.split('|'):
                rules.append((left.strip(), el.strip()))
        else:
            break

    if utils.is_rrg(rules, terms, non_terms):
        print("Класс 3: Праволинейная грамматика")
        return
    elif utils.is_lrg(rules, terms, non_terms):
        print("Класс 3: Леволинейная грамматика")
        return
    elif utils.is_cfg(rules, terms, non_terms):
        print("Класс 2: Контекстно-свободная грамматика")
        return
    elif utils.is_ng(rules, terms, non_terms, start_symbol):
        if utils.is_csg(rules, terms, non_terms, start_symbol):
            print("Класс 1: Контекстно-зависимая грамматика")
            return
        else:
            print("Класс 1: Неукорачивающая грамматика")
            return
    else:
        print("Класс 0:  Неограниченная грамматика")


if __name__ == '__main__':
    main()

  ```

</details>  



<details>

  <summary>utils.py</summary>
  
  ```python
  def is_rrg(rules: list[tuple[str, str]], terms: list[str], non_terms: list[str]) -> bool:
    """
    Класс 3: Праволинейная грамматика

    :param non_terms:
    :param terms:
    :param rules:
    :return:
    """
    for rule in rules:
        left, right = rule

        if not (len(left) == 1 and left in non_terms):
            return False

        if right == "eps":
            continue

        for el in right[:-1]:
            if el not in terms:
                return False

    return True


def is_lrg(rules: list[tuple[str, str]], terms: list[str], non_terms: list[str]) -> bool:
    """
    Класс 3: Леволинейная грамматика

    :param non_terms:
    :param terms:
    :param rules:
    :return:
    """
    for rule in rules:
        left, right = rule

        if not (len(left) == 1 and left in non_terms):
            return False

        if right == "eps":
            continue

        for el in right[1:]:
            if el not in terms:
                return False

    return True


def is_cfg(rules: list[tuple[str, str]], terms: list[str], non_terms: list[str]) -> bool:
    """
    Класс 2: Контекстно-свободная грамматика

    :param non_terms:
    :param terms:
    :param rules:
    :return:
    """

    for rule in rules:
        left, right = rule

        if not (len(left) == 1 and left in non_terms):
            return False

        if right == "eps":
            continue

    return True


def is_csg(rules: list[tuple[str, str]], terms: list[str], non_terms: list[str], start_symbol: str) -> bool:
    """
    Класс 1: Контекстно-зависимая грамматика

    :param start_symbol:
    :param non_terms:
    :param terms:
    :param rules:
    :return:
    """

    flag = False

    for rule in rules:
        left, right = rule

        if len(left) == 1:
            if right == "eps":
                if left != start_symbol and flag:
                    return False
                else:
                    flag = True

        if start_symbol in right:
            if flag:
                return False
            else:
                flag = True

        # левая часть
        is_left_ok = False
        for el in non_terms:
            if el in left:
                is_left_ok = True
                break

        if not is_left_ok:
            return False

        # Правая часть
        is_right_ok = False
        if right != "eps":
            is_right_ok = True

        if not is_right_ok:
            return False

        gamma = None
        for index, el in enumerate(left):
            if el not in non_terms:
                continue
            xi_1 = left[:index]
            xi_2 = left[index + 1:]

            if right.startswith(xi_1) and right.endswith(xi_2):
                gamma = right.replace(xi_1, "").replace(xi_2, "")
                if not gamma:
                    continue

        if not gamma:
            return False

    return True


def is_ng(rules: list[tuple[str, str]], terms: list[str], non_terms: list[str], start_symbol: str) -> bool:
    """
    Класс 1: Неукорачивающая грамматика

    :param start_symbol:
    :param non_terms:
    :param terms:
    :param rules:
    :return:
    """
    flag = False

    for rule in rules:
        left, right = rule

        if len(left) == 1:
            if right == "eps":
                if left != start_symbol and flag:
                    return False
                else:
                    flag = True

        if start_symbol in right:
            if flag:
                return False
            else:
                flag = True

        if len(left) > len(right):
            return False

    return True

  ```

</details>  
