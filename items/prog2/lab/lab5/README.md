# Лабораторная работа #5

## Вступление

Generic Types (Общие типы) - и есть тема данной лаб работы

## Готовые проекты

- [Проект с заданиями 1-7 и 9 (8 и 10 в процессе)](https://drive.google.com/file/d/1-dO3tbzVe8i6fKoZkOkpJUJkOop400zf/view?usp=share_link)
- [...](...)

## Работа

### Задание 1
Создайте обобщенный класс Pair, представляющий
понятие "пара значений". Такой класс оказывается полезным в
ситуациях, когда нужно использовать два связанных друг с другом
значения. Например, алгоритм поиска наибольшего элемента
заданного массива может вернуть само это наибольшее значение и
значение позиции (индекса) первого элемента массива с таким
значением. Типы элементов пары могут быть произвольными (но
ссылочными) и в общем случае не совпадающими. Необходимо
предусмотреть конструктор (или конструкторы) для инициализации
вновь созданных пар, возможность получение/изменения каждого из 
значений пары (можно, как это сделано, например, в стандартном
шаблоне pair в С++ просто использовать открытые поля first и second) и
метод make_pair для создания пары значений, который можно вызывать
даже если у нас пока нет ни одного объекта класса Pair.


<details>
  <summary>Task1/Pair.java</summary>
  
```java
package Task1;

public class Pair<T1, T2> {
    private T1 first;
    private T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    public static <T1, T2> Pair<T1, T2> makePair(T1 first, T2 second) {
        return new Pair<>(first, second);
    }
}

```
  
</details>

<details>
  <summary>Task1/Test.java</summary>
  
```java
package Task1;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Pair<String, Integer> pair = Pair.makePair("foo", 42);
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());


        String[] strings = {"hello", "world"};
        Pair<String, String[]> pair2 = Pair.makePair("foo", strings);
        System.out.println(pair2.getFirst());
        System.out.println(Arrays.toString(pair2.getSecond()));
    }
}

```
  
</details>



### Задание 2
Мешок (Bag) - это емкость фиксированного размера, в
которую можно складывать различные предметы. Поднимая мешок мы
его встряхиваем и предметы перераспределяются в мешке
произвольным образом. Из мешка достается тот предмет, который
подвернулся под руку первым, то есть какой-то из имеющихся, но
неизвестно какой. Другими словами, Bag - это контейнер, в который
новые элементы добавляются в произвольную (будем считать
случайную) позицию и удаляются из случайно выбранной позиции.
Создайте (НЕ ОБОБЩЕННЫЙ!) класс Bag в котором элементы хранятся
в массиве (какого типа должны быть элементы массива если по
требованиям задачи Bag может хранить предметы любых типов?).
Размер массива (предельный размер конкретного "мешка") указывается
в конструкторе при создании объекта и дальше меняться не может.
Нужно предусмотреть методы для добавления, удаления (этот метод
должен возвращать удаленный элемент) элементов, метод
возвращающий (какой-то) элемент и метод, возвращающий значение
текущего размера Bag. Какие методы (или один метод) Bag не должны
переопределяться в классах-потомках? Запретите их (или его)
переопределение.
  
Метод `random()` класса `Math` возвращает псевдослучайное действительное значение в диапазоне от 0.0 до 1.0, включая 0.0 но не включая 1.0.
Чтобы получить псевдослучайное целое значение из диапазона `[0, size-1]` можно использовать выражение `(int)Math.round(Math.random()*(size-1))`.
Протестируйте работоспособность класса Bag для элементов разного типа `(Integer, String, …)`.


<details>
  <summary>Task2/Bag.java</summary>
  
```java
package Task2;

public class Bag {
    private Object[] items;

    public Bag(int weight) {
        this.items = new Object[weight];
    }

    public int getCurrentSize() {
        int counter = 0;
        for (Object item : items) {
            if (item != null) {
                counter++;
            }
        }
        return counter;
    }

    public Object getItem() {
        while (true) {
            int random_index = (int)Math.round(Math.random()*(items.length - 1));
            if (items[random_index] != null) {
                Object item = items[random_index];
                items[random_index] = null;
                return item;
            }
        }
    }

    public void putItem(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Object is null");
        }

        if (getCurrentSize() == items.length) {
            throw new IllegalStateException("Bag is full");
        }

        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = obj;
                break;
            }
        }
    }
}

```
  
</details>
  
<details>
  <summary>Task2/Test.java</summary>
  
```java
package Task2;

public class Test {
    public static void main(String[] args) {
        Bag bag = new Bag(10);
        bag.putItem("item1");
        bag.putItem("item2");
        bag.putItem("item3");
        bag.putItem("item4");
        bag.putItem("item5");
        bag.putItem("item6");
        bag.putItem("item7");
        bag.putItem("item8");
        bag.putItem("item9");
        bag.putItem("item10");
        System.out.println(bag.getCurrentSize());
        System.out.println(bag.getItem());
        System.out.println(bag.getItem());
        System.out.println(bag.getCurrentSize());
        bag.putItem("item11");
        System.out.println(bag.getCurrentSize());
        System.out.println(bag.getItem());
    }
}

```
  
</details>



### Задание 3
Создайте НЕОБОБЩЕННЫЙ класс `PairBag`, представляющий "мешок" для хранения пар значений. Используйте созданные
раньше обобщенный класс `Pair` и необобщенный класс Bag. Прежде
всего, определите, какое отношение между классами `Bag` и `PairBag`
следует использовать. Как и следует из названия, объекты `PairBag`
должны хранить только пары значений (не могут хранить отдельные)
значения. Методы должны работать с парами (получать/возвращать). 
При этом, в одном и том же объекте `PairBag` одновременно могут
храниться пары c разными типами значений, например,
`Pair<Integer,Integer>` и `Pair<Integer,String>`.

<details>
  <summary>Task3/PairBag.java</summary>
  
```java
package Task3;

import Task1.Pair;
import Task2.Bag;

public class PairBag extends Bag {

    public PairBag(int weight) {
        super(weight);
    }

    public Pair<Object, Object> getPair() {
        return (Pair<Object, Object>) super.getItem();
    }
    
    public void putPair(Pair<Object, Object> pair) {
        super.putItem(pair);
    }
}

```
  
</details>

<details>
  <summary>Task3/Test.java</summary>
  
```java
package Task3;

import Task1.Pair;

public class Test {
    public static void main(String[] args) {
        PairBag pairBag = new PairBag(10);

        pairBag.putPair(new Pair<>(1, "Cool String"));

        Pair<Object, Object> some = pairBag.getPair();

        System.out.println(some.getFirst());
        System.out.println(some.getSecond());
    }
}

```
  
</details>


### Задание 4
Хранение в контейнере пар разного типа обычно приводит
к неопределенности при использовании пар. Такой проблемы не будет,
если типы пар одинаковые. Если тип первого значения пары `T1`, а тип
второго значения пары `T2`, то все пары в контейнере должны иметь тип
`Pair<T1,T2>`. Создайте обобщенный класс `GPairBag` для хранения пар
одинакового типа. В реализации не используйте стандартные
контейнеры Java. Используйте те классы, которые Вы реализовали при
решении предыдущих заданий этой лабораторной работы. Начните с
определения того, какое отношение между `GPairBag` и `PairBag` удобно
использовать.

<details>
  <summary>Task4/GPairBag.java</summary>
  
```java
package Task4;

import Task1.Pair;
import Task2.Bag;

public class GPairBag<T> extends Bag {
    public GPairBag(int weight) {
        super(weight);
    }

    public void putPair(Pair<T, T> pair) {
        super.putItem(pair);
    }

    public Pair<T, T> getPair() {
        return (Pair<T, T>) super.getItem();
    }
}

```
  
</details>
  
<details>
  <summary>Task4/Test.java</summary>
  
```java
package Task4;

import Task1.Pair;

public class Test {
    public static void main(String[] args) {
        GPairBag<String> bag = new GPairBag<String>(10);

        bag.putPair(new Pair<String, String>("Hello", "World"));

        Pair<String, String> some = bag.getPair();

        System.out.println(some.getFirst());
        System.out.println(some.getSecond());
    }
}

```
  
</details>

### Задание 5
Создайте обобщенный класс `GenericPairBag` для хранения
пар одинакового типа, используя подходящий стандартный
обобщенный контейнерный класс Java, например, `ArrayList`.


<details>
  <summary>Task5/GenericPairBag.java</summary>
  
```java
package Task5;

import Task1.Pair;

import java.util.ArrayList;

public class GenericPairBag<T> {

    private final ArrayList<Pair<T, T>> pairs;

    public GenericPairBag() {
        pairs = new ArrayList<>();
    }

    public void putPair(Pair<T, T> pair) {
        pairs.add(pair);
    }

    public Pair<T, T> getPair() {
        if (pairs.isEmpty()) {
            throw new IllegalStateException("Pair bag is empty");
        }

        int randomIndex = (int)Math.round(Math.random()*(pairs.size() - 1));
        Pair<T, T> pair = pairs.get(randomIndex);
        pairs.remove(randomIndex);
        return pair;
    }

    public int getCurrentSize() {
        return pairs.size();
    }
}

```
  
</details>
  
<details>
  <summary>Task5/Test.java</summary>
  
```java
package Task5;

import Task1.Pair;

public class Test {
    public static void main(String[] args) {

        GenericPairBag<String> bag = new GenericPairBag<>();

        Pair<String, String> pair1 = new Pair<>("1", "2");
        Pair<String, String> pair2 = new Pair<>("3", "4");

        bag.putPair(pair1);
        bag.putPair(pair2);

        Pair<String, String> pair = bag.getPair();
        System.out.println(pair.getFirst() + " " + pair.getSecond());
    }
}

```
  
</details>



### Задание 6
Проводится спортивное соревнование, турнир, среди N
команд. Нужно провести жеребьевку, то есть определить пары команд,
которые будут проводить игры друг с другом. Для этого записки с
названиями команд складываются в "мешок", перемешиваются, затем
последовательно извлекаются из мешка и две подряд выбранные
записки определяют пару играющих друг с другом команд.
Получившиеся пары помещаются в другой "мешок". Затем, пока в этом
"мешке" еще что-то есть, из него выбирается какая-то пара и
пользователю задается вопрос о том, какая команда выиграла, первая
или вторая (игра проводится до победы одной из команд, ничьей быть
не может). Названия выигравших команд складываются в первый
"мешок". Теперь их будет N/2 штук. Процесс продолжается до тех пор,
пока не останутся две команды. Победившая команда является
победителем всего турнира. Напишите программу, реализующую
описанный выше процесс проведения турнира. Число командучастников N задается пользователем. Очевидно, что N не может быть
произвольным. Если пользователь дает недопустимое значение N
можно задать значение по умолчанию, например, 8. "Мешки" должны
быть представлены каким-нибудь подходящим стандартным классомконтейнером (или классом `Bag`) и классом `GenericPairBag`. Имена 
команд можно генерировать автоматически `("Команда1", "Команда2", …)`.

  
<details>
  <summary>Task6/Tournament.java</summary>
  
```java
package Task6;

import Task1.Pair;
import Task2.Bag;
import Task5.GenericPairBag;

import java.util.Scanner;

public class Tournament {
    private final Bag teamsBag;
    private final GenericPairBag<String> pairsBag;

    public Tournament(int numTeams) {
        if (numTeams <= 1 || numTeams % 2 != 0) {
            throw new IllegalArgumentException("Некорректное количество команд");
        }

        // Создаем мешок команд
        teamsBag = new Bag(numTeams);
        for (int i = 1; i <= numTeams; i++) {
            teamsBag.putItem("Команда " + i);
        }
        pairsBag = new GenericPairBag<>();
    }

    public String playTournament() {
        // Создаем мешок пар команд
        while (teamsBag.getCurrentSize() > 1) {
            String team1 = (String) teamsBag.getItem();
            String team2 = (String) teamsBag.getItem();
            pairsBag.putPair(new Pair<>(team1, team2));
        }

        while (pairsBag.getCurrentSize() > 0) {
            Pair<String, String> pair = pairsBag.getPair();
            String winner = playMatch(pair.getFirst(), pair.getSecond());
            teamsBag.putItem(winner);
        }

        if (teamsBag.getCurrentSize() != 1) {
            return playTournament();
        } else
            return (String) teamsBag.getItem();
    }

    private String playMatch(String team1, String team2) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n >> " + team1 + " против " + team2);
            System.out.print("\nКакая команда выиграла? \n\n\t1 - " + team1 + "\n\t2 - " + team2 + "\n\nВвод: ");
            int winner = scanner.nextInt();
            if (winner == 1) {
                return team1;
            } else if (winner == 2) {
                return team2;
            } else {
                System.out.println("Некорректный выбор, попробуйте еще раз.");
            }
        }
    }
}

```
  
</details>

<details>
  <summary>Task6/Test.java</summary>
  
```java
package Task6;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество команд: ");
        int numTeams = scanner.nextInt();

        if (numTeams <= 1 || numTeams % 2 != 0) {
            System.out.println("Некорректное количество команд. Используется значение по умолчанию (8).");
            numTeams = 8;
        }

        Tournament tournament = new Tournament(numTeams);
        System.out.println("Победитель турнира: " + tournament.playTournament());
    }
}

```
  
</details>


### Задание 7
Создайте класс DList, содержащий два поля - список
значений произвольного типа T1 и список списков значений
произвольного типа T2. Работа с полями должна быть согласованной,
то есть, например, при добавлении новых значений должны
указываться одновременно целое значение и список целых значений,
соответственно целое значение добавляется в первое поле класса,
список во второе, Таким образом, каждому элементу в позиции i первого
поля (списка) соответствует список в позиции i второго поля (списка
списков). Удалять и получать информацию можно указав либо позицию
i, либо значение первого поля. Например, первое поле может содержать
список `{1, 2, 3, 4, 5}`, с каждым элементом этого списка могут быть
связаны, например, такие списки: `{1}, {1,1}, {1,2}, { {1,3}, {2,2} }, {2,3}`. То
есть со всеми элементами, кроме 4, связано по одному списку, а со
значением 4 связаны два списка.


<details>
  <summary>Task7/DList.java</summary>
  
```java
package Task7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DList<T1, T2> {
    private final List<T1> list1;
    private final List<List<T2>> list2;

    public DList() {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
    }

    public void add(T1 value, List<T2> sublist) {
        list1.add(value);
        list2.add(sublist);
    }

    public Map<T1, List<T2>> getByIndex(int index) {
        Map<T1, List<T2>> resp = new HashMap<>();
        resp.put(list1.get(index), list2.get(index));
        return resp;
    }

    public Map<T1, List<T2>> getByKey(T1 key) {
        Map<T1, List<T2>> resp = new HashMap<>();
        int index = list1.indexOf(key);
        if (index >= 0) {
            resp.put(list1.get(index), list2.get(index));
        } else
            throw new IllegalArgumentException("Key not found");
        return resp;
    }


    public void removeByIndex(int index) {
        list1.remove(index);
        list2.remove(index);
    }

    public void removeByKey(T1 key) {
        int index = list1.indexOf(key);
        if (index >= 0) {
            list1.remove(index);
            list2.remove(index);
        } else
            throw new IllegalArgumentException("Key not found");
    }
}

```
  
</details>
  
<details>
  <summary>Task7/Test.java</summary>
  
```java
package Task7;

import Task3.PairBag;
import Task1.Pair;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        DList<String, PairBag> dlist = new DList<>();

        PairBag pairBag = new PairBag(10);
        pairBag.putPair(new Pair<>("Hello", "World"));

        dlist.add("Hello", List.of(pairBag));

        System.out.println(dlist.getByIndex(0));
        System.out.println(dlist.getByKey("Hello"));

        dlist.removeByKey("Hello");

        dlist.add("Hello2", List.of(pairBag));

        System.out.println(dlist.getByIndex(0));
        System.out.println(dlist.getByKey("Hello2"));
    }
}

```
  
</details>

### Задание 8
Реализуйте алгоритм выдачи указанной суммы денег
(целое значение) минимальным количеством монет из заданного
набора методом динамического программирования. Алгоритм должен
возвращать не только количество монет, но и все возможные
комбинации монет, обеспечивающие оптимальную выдачу суммы.
Используйте в реализации класс DList. Протестируйте работу
алгоритма для разных наборов монет (например, 1, 2, 5, 10 и 1, 4, 7, 9).
Здесь первое поле DList будет содержать набор промежуточных
результатов, а второе поле - списки номиналов монет, которыми должна
быть выдана сумма, соответствующая промежуточному результату.
Пример в задании 7 соответствует решению задачи выдачи суммы 5
монетами номиналов 1, 2, 3.

*Хэш-таблицы. Есть набор быть может сложно организованных данных. Нужно
многократно искать информацию в этом наборе. В каком случае поиск будет
максимально быстрым? Мы знаем, что очень быстро можно найти элемент в
массиве, если известен индекс этого элемента. В этом случае сложность
операции поиска не зависит от размера массива. Пусть, например, хранимые
данные - это данные о конкретных людях: фамилия, возраст, телефоны и т.д.
Поиск проводим по фамилии. Проблема в том, что существует очень большое
количество разных фамилий, несоизмеримо больше потенциально возможных
фамилий. Поставить в соответствие любой возможной фамилии элемент 
массива невозможно, его размер окажется огромным. Но если мы можем
отобразить каждый элемент некоторого большого набора (все возможные
фамилии) в значение из какого-то ограниченного набора (набор индексов
элементов массива), то есть у нас есть некоторая функция, выполняющая такое
отображение (хэш-функция), то задачу быстрого поиска можно решить,
построив такой массив. Очевидно, что отобразить элементы большого набора
в меньший однозначно в общем случае невозможно. Неизбежно несколько
элементов большого набора будут отображаться в одно и то же значение из
малого набора. Значит для таких случаев для одного индекса массива нужно
будет хранить несколько значений. Другими словами, можно организовать
данные в виде хэш-таблицы, например, как массив списков. Информация
хранится в элементах списков, а массив нужен для быстрого доступа к
конкретному списку. Если хэш-функция отображает некоторую фамилию на
индекс массива однозначно, соответствующий список состоит из одного
элемента и весь поиск сводится к нахождению этого элемента. В противном
случае в списке будут храниться все записи, отображенные на данный индекс,
и чтобы найти нужные данные придется искать нужную запись в списке.
Например, есть фамилия "ИВАНОВ". Хэш-функция (крайне неоптимальная)
может просто сложить коды всех символов соответствующей строки и выдать
целое число 6279 - индекс элемента массива. Для другой фамилии, например,
"ЛОБАНОВ" та же хэш-функция вернет число 7335. Индексы разные - поиск
быстрый. Но для фамилии "БАЛОНОВ", очевидно, значение хэш-функции
такое же как для фамилии "ЛОБАНОВ" - 7335. Таким образом элемент
таблицы с индексом 7335 будет содержать список из двух элементов и, чтобы
найти информацию о человеке с фамилией "БАЛОНОВ" нужно будет еще и
сравнивать фамилии для всех записей в этом списке.*


<details>
  <summary>Task8/Test.java</summary>
  
```java

```
  
</details>
  
<details>
  <summary>Task8/Test.java</summary>
  
```java

```
  
</details>


### Задание 9
Создайте обобщенный класс `HashFunction<K>` с одним
объявленным абстрактным методом `int hash(K s)`. При создании объекта
класса `HashFunction` ему должна передаваться информация о размере
таблицы – это определяет, какие целые значения (из какого диапазона)
должна выдавать функция. Создайте обобщенный класс `HashTable` для
представления хэш-таблицы в виде массива списков. Учтите, что в
таблице хранятся данные одного типа `T`, а поиск в таблице может
осуществляться по другому типу `K` (например, поиск проводится не по
всей записи, а только по одному ее полю). Это, в частности накладывает
ограничение на тип элементов таблицы. Нужно уметь проверять, что
конкретный элемент содержит значение (например, отдельное поле) по
которому хэш-функция генерирует свое значение. То есть тип `T` должен
содержать реализацию некоторого метода (например, `boolean
contains(K value))`, объявленного в определенном базовом классе (или в
реализуемом интерфейсе). Размер массива (таблицы) и конкретная
хэш-функция (как ссылка на объект класса-наследника `HashFunction`)
передаются конструктору класса при создании объекта `HashTable`.


<details>
  <summary>Task9/HashFunction.java</summary>
  
```java
package Task9;


public abstract class HashFunction<K> {
    protected int tableSize;

    public HashFunction(int tableSize) {
        this.tableSize = tableSize;
    }

    public abstract int hash(K s);
}

```
  
</details>

<details>
  <summary>Task9/HashTable.java</summary>
  
```java
package Task9;

import java.util.ArrayList;
import java.util.List;

public abstract class HashTable<T, K> {
    private final List<T>[] table;
    private final HashFunction<K> hashFunction;

    public HashTable(int tableSize, HashFunction<K> hashFunction) {
        this.table = new ArrayList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = new ArrayList<T>();
        }
        this.hashFunction = hashFunction;
    }

    public void add(T element) {
        K key = getKey(element);
        int index = hashFunction.hash(key);
        table[index].add(element);
    }

    public List<T> get(K key) {
        int index = hashFunction.hash(key);
        return table[index];
    }

    public abstract K getKey(T element);
}

```
  
</details>

<details>
  <summary>Task9/test/Person.java</summary>
  
```java
package Task9.test;

public record Person(String name, int age) {

}

```
  
</details>

<details>
  <summary>Task9/test/PersonHashTable.java</summary>
  
```java
package Task9.test;

import Task9.HashFunction;
import Task9.HashTable;

public class PersonHashTable extends HashTable<Person, String> {

    public PersonHashTable(int tableSize, HashFunction<String> hashFunction) {
        super(tableSize, hashFunction);
    }

    @Override
    public String getKey(Person element) {
        return element.name() + element.age();
    }
}

```
  
</details>

  
<details>
  <summary>Task9/test/PersonNameHashFunction.java</summary>
  
```java
package Task9.test;

import Task9.HashFunction;

public class PersonNameHashFunction extends HashFunction<String> {
    public PersonNameHashFunction(int tableSize) {
        super(tableSize);
    }

    public int hash(String s) {
        if (s == null) {
            return 0;
        }
        return Math.abs(s.hashCode() % tableSize);
    }
}

```
  
</details>

<details>
  <summary>Task9/test/Test.java</summary>
  
```java
package Task9.test;

import Task9.HashFunction;
import Task9.HashTable;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        HashFunction<String> hashFunction = new PersonNameHashFunction(10);
        HashTable<Person, String> hashTable = new PersonHashTable(10, hashFunction);
        Person p1 = new Person("John", 30);
        Person p2 = new Person("Alice", 25);
        Person p3 = new Person("Bob", 40);
        hashTable.add(p1);
        hashTable.add(p2);
        hashTable.add(p3);
        List<Person> persons = hashTable.get("Alice25");
        for (Person person : persons) {
            System.out.println(person.name() + " " + person.age());
        }
    }
}

```
  
</details>

### Задание 10
Создайте класс Person, для хранения информации о
человеке: фамилия, возраст и другие поля (какие и сколько решайте
сами). Создайте класс-наследник HashFunction с какой-нибудь
реализацией хэш-функции, получающей фамилию человека и
возвращающей целое значение (можно использовать алгоритм,
описанный выше немного модифицировав его так, чтобы учесть
максимально возможный размер массива, задаваемый извне).
Напишите программу для тестирования работы хэш-таблицы,
содержащей объекты класса Person.


###### Хз почему, но я машинально реализовал в качестве примера такой класс в таск9, однако я использовал в качестве рассчета хэша строку имя + возраст


*Авторство: **Бояршинов Н.О***
