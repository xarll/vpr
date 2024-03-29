# Лабораторная работа #2

## Вступление

Вам предстоит выполнить 10 заданий: написать код под каждое

## Работа

### Задание 1
Напишите программу, выводящую на экран таблицу,
содержащую минимальные и максимальные значения для всех простых
числовых типов

```java
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, List<String>> types = new LinkedHashMap<String, List<String>>() {{
            put("byte", Arrays.asList("8 бит", "-128", "127"));
            put("short", Arrays.asList("16 бит", "-32768", "32767"));
            put("int", Arrays.asList("32 бит", "-2147483648", "2147483647"));
            put("long", Arrays.asList("64 бит", "-9223372036854775808", "9223372036854775807"));
            put("float", Arrays.asList("32 бит", "1.4e-45", "3.4028235e+38"));
            put("double", Arrays.asList("64 бит", "4.9e-324", "1.7976931348623157e+308"));
        }};

        System.out.println("Простые числовые типы Java\n");
        System.out.println("Тип" + " \tВместимость" + "\tДиапазон значений");
        
        for (Map.Entry<String, List<String>> entry : types.entrySet()) {
            String key = entry.getKey();
            String capacity = entry.getValue().get(0);
            String min = entry.getValue().get(1);
            String max = entry.getValue().get(2);

            System.out.println(key + " \t(" + capacity + ") " + "\tот " + min + " до " + max + ".");
        }
    }
}
```

##### Вывод:
```bash
Простые числовые типы Java

Тип 	Вместимость	Диапазон значений
byte 	(8 бит) 	от -128 до 127.
short 	(16 бит) 	от -32768 до 32767.
int 	(32 бит) 	от -2147483648 до 2147483647.
long 	(64 бит) 	от -9223372036854775808 до 9223372036854775807.
float 	(32 бит) 	от 1.4e-45 до 3.4028235e+38.
double 	(64 бит) 	от 4.9e-324 до 1.7976931348623157e+308.
```
##### Несколько слов по коду:

Здесь я создаю переменную `types` типа `Map<String, List<String>>` - т.е она может хранить словарь, где ключ - строка, а значение список строк. 
далее присваиваю переменной значение `new LinkedHashMap<String, List<String>>()` сразу добавляю все значения которые я хочу.

Замечание: почему `LinkedHashMap`? Потому что `HashMap`, как и многие другие реализации интерфейса Map, не гарантирует сохранение порядка элементов, 
а `LinkedHashMap` сохраняет порядок элементов в порядке их добавления в `Map`.

Далее могут возникнуть вопросы за красивую табуляцию(пробелы между строк в выводе), за что отвечает обычный спецсимвол стандарта POSIX: `\t`, 
который и делает такую красивую табуляцию.

### Задание 2
Дан массив из 20 целых переменных. Конкретные значения
для элементов задаются в конструкции инициализации. Найти среднее
геометрическое отрицательных элементов.

```java
public class task2 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, -5, 6, 7, 8, -9, 10, -11, -12, -13, -14, -15, 16, 17, -18, 20, -22};

        int multiply = 1;
        int count = 0;

        for (int element : array) {
            if (element < 0) {
                multiply *= element;
                count++;
            }
        }

        if (count > 0 && multiply != 0) {
            double geometricMean = Math.pow(Math.abs(multiply), 1.0 / count);
            if (count % 2 != 0) {
                geometricMean = -geometricMean;
            }
            System.out.println("Среднее геометрическое элементов: " + geometricMean);
        }
    }
}
```
##### Вывод
```bash
Среднее геометрическое элементов: -10.87453314657656
```

PS: Вообще стоит подумать о законности выражения `Найти среднее
геометрическое отрицательных элементов`, ибо с точки зрения математики - всё спорно.

### Задание 3
Радар посылает сигнал, ловит его отражение и по времени
распространения сигнала определяет расстояние до предмета. Есть
некоторое расстояние R. Сигнал от предметов, расположенных на
больших расстояниях слишком слабый и радар не может обнаружить их. 
Радар используется для охраны некоторого объекта.
Определено расстояние r меньшее R. Если какой-то предмет
оказывается слишком близко к объекту охраны, на расстоянии меньшем
r, объявляется тревога. Даны (заданы в самой функции main)
координаты (два вещественных значения) некоторого предмета в
окрестности объекта (в Декартовой прямоугольной системе координат).
Определить и вывести на экран сообщение: если предмет далеко - "Не
обнаружен", если на расстоянии большем r, но меньшем или равном R, 
то "Обнаружен", если на расстоянии меньшем или равном r - "Тревога".

```java
public class task3 {
    public static void main(String[] args) {

        float r = 5.2F;
        float R = 10F;

        float obj_x = 2F;
        float obj_y = 3F;

        double distance = Math.sqrt(Math.pow(obj_x, 2) + Math.pow(obj_y, 2));

        if (distance > R) {
            System.out.println("Предмет не обнаружен");
        } else if (distance <= R && distance > r) {
            System.out.println("Предмет обнаружен");
        } else if (distance <= r) {
            System.out.println("Тревога!");
        }
    }
}
```
##### Вывод
```bash
Тревога!
```

##### Несколько слов по коду: 
Тут всё очень просто: находим расстояние до обьекта по теореме Пифагора, 
и проходимся по условию.


### Задание 4
Преобразуйте предыдущую программу так, чтобы можно
было задавать расстояния R и r, координаты предмета через командную
строку. Протестируйте программу для всех возможных вариантов
попадания точки.

```java
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {

        // задать значения через командную строку stdin

         Scanner scanner = new Scanner(System.in);
         System.out.print("Введите значение r: ");
         float r = scanner.nextFloat();
         System.out.print("Введите значение R: ");
         float R = scanner.nextFloat();
         System.out.print("Введите значение x: ");
         float obj_x = scanner.nextFloat();
         System.out.print("Введите значение y: ");
         float obj_y = scanner.nextFloat();
         scanner.close();
         
        double distance = Math.sqrt(Math.pow(obj_x, 2) + Math.pow(obj_y, 2));

        if (distance > R) {
            System.out.println("Предмет не обнаружен");
        } else if (distance <= R && distance > r) {
            System.out.println("Предмет обнаружен");
        } else if (distance <= r) {
            System.out.println("Тревога!");
        }
    }
}
```
##### Вывод
```bash
Введите значение r: 5
Введите значение R: 10
Введите значение x: 2
Введите значение y: 3
Тревога!
```


### Задание 5
Напишите программу, получающую через командную
строку целое десятичное число и отображающую на экране само это
число и его представление в двоичной, восьмеричной и
шестнадцатеричной системах счисления.

```java
import java.util.Scanner;

public class task5 {
    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);

        System.out.println("\nВведенное число: " + num);
        System.out.println("Число в двоичной системе: " + Integer.toBinaryString(num));
        System.out.println("Число в восьмеричной системе: " + Integer.toOctalString(num));
        System.out.println("Число в шестнадцатеричной системе: " + Integer.toHexString(num));
    }
}
```
##### Вывод
```bash
Введите значение: 5

Введенное число: 5
Число в двоичной системе: 101
Число в восьмеричной системе: 5
Число в шестнадцатеричной системе: 5
```

### Задание 6
В стандарте Unicode-16 под знаки Кириллицы (включая
символы украинского, сербского, македонского и т.д. языков, а также
старославянского) отведен диапазон кодов 0x0400 - 0x04FF. Создайте
метод для отображения в виде таблицы непрерывного набора Unicode
символов, которому через аргументы передаются стартовый код, число
выводимых строк и столбцов таблицы. Напишите программу, которая
использует метод для вывода символов Кириллицы (стартовый код
0x0400, 16 строк, 16 столбцов, а, затем, для вывода некоторых знаков
денежных единиц (например, бразильского крузейро, индийской рупии,
евро, гривны, турецкой лиры, рубля, биткоина), стартовый код 0x20a0, 2
строки, 16 столбцов. Нужно получить что-то подобное следующему:
```
 0 1 2 3 4 5 6 7 8 9 a b c d e f
400 Ѐ Ё Ђ Ѓ Є Ѕ І Ї Ј Љ Њ Ћ Ќ Ѝ Ў Џ
410 А Б В Г Д Е Ж З И Й К Л М Н О П
420 Р С Т У Ф Х Ц Ч Ш Щ Ъ Ы Ь Э Ю Я
430 а б в г д е ж з и й к л м н о п
440 р с т у ф х ц ч ш щ ъ ы ь э ю я
450 ѐ ё ђ ѓ є ѕ і ї ј љ њ ћ ќ ѝ ў џ
460 Ѡ ѡ Ѣ ѣ Ѥ ѥ Ѧ ѧ Ѩ ѩ Ѫ ѫ Ѭ ѭ Ѯ ѯ
470 Ѱ ѱ Ѳ ѳ Ѵ ѵ Ѷ ѷ Ѹ ѹ Ѻ ѻ Ѽ ѽ Ѿ ѿ
480 Ҁ ҁ ҂ ҃ ҄ ҅ ҆ ҇ ҈ ҉ Ҋ ҋ Ҍ ҍ Ҏ ҏ
490 Ґ ґ Ғ ғ Ҕ ҕ Җ җ Ҙ ҙ Қ қ Ҝ ҝ Ҟ ҟ
4a0 Ҡ ҡ Ң ң Ҥ ҥ Ҧ ҧ Ҩ ҩ Ҫ ҫ Ҭ ҭ Ү ү
4b0 Ұ ұ Ҳ ҳ Ҵ ҵ Ҷ ҷ Ҹ ҹ Һ һ Ҽ ҽ Ҿ ҿ
4c0 Ӏ Ӂ ӂ Ӄ ӄ Ӆ ӆ Ӈ ӈ Ӊ ӊ Ӌ ӌ Ӎ ӎ ӏ 
4d0 Ӑ ӑ Ӓ ӓ Ӕ ӕ Ӗ ӗ Ә ә Ӛ ӛ Ӝ ӝ Ӟ ӟ
4e0 Ӡ ӡ Ӣ ӣ Ӥ ӥ Ӧ ӧ Ө ө Ӫ ӫ Ӭ ӭ Ӯ ӯ
4f0 Ӱ ӱ Ӳ ӳ Ӵ ӵ Ӷ ӷ Ӹ ӹ Ӻ ӻ Ӽ ӽ Ӿ ӿ
 0 1 2 3 4 5 6 7 8 9 a b c d e f
20a0 ₠ ₡ ₢ ₣ ₤ ₥ ₦ ₧ ₨ ₩ ₪ ₫ € ₭ ₮ ₯
20b0 ₰ ₱ ₲ ₳ ₴ ₵ ₶ ₷ ₸ ₹ ₺ ₻ ₼ ₽ ₾ ₿
```
Чтобы символы отображались в консольном окне, нужно, чтобы кодировка
символов и в текстовом редакторе, в котором набирается текст программы и
кодировка символов в терминале (консольном окне) использовали стандарт
Unicode. В MS Visual Studio Code и IntelliJ Idea это настройки по умолчанию.
Чтобы добиться этого в Eclipse нужно выполнить следующее: Window -
Preferences - General - Workspace - Text file encoding (установить UTF-8)

```java
public class task6 {
    private static void viewTable(int start_code, int rows, int columns) {

        for (int i = 0; i < columns; i++) {
            System.out.printf("%X ", i);
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.printf("%4X ", start_code + i * columns);
            for (int j = 0; j < columns; j++) {;
                System.out.printf("%c ", (char)(start_code + i * columns + j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        viewTable(0x0400, 16, 16);
        viewTable(0x20a0, 2, 16);
    }
}
```
##### Вывод
```
0 1 2 3 4 5 6 7 8 9 A B C D E F 
 400 Ѐ Ё Ђ Ѓ Є Ѕ І Ї Ј Љ Њ Ћ Ќ Ѝ Ў Џ 
 410 А Б В Г Д Е Ж З И Й К Л М Н О П 
 420 Р С Т У Ф Х Ц Ч Ш Щ Ъ Ы Ь Э Ю Я 
 430 а б в г д е ж з и й к л м н о п 
 440 р с т у ф х ц ч ш щ ъ ы ь э ю я 
 450 ѐ ё ђ ѓ є ѕ і ї ј љ њ ћ ќ ѝ ў џ 
 460 Ѡ ѡ Ѣ ѣ Ѥ ѥ Ѧ ѧ Ѩ ѩ Ѫ ѫ Ѭ ѭ Ѯ ѯ 
 470 Ѱ ѱ Ѳ ѳ Ѵ ѵ Ѷ ѷ Ѹ ѹ Ѻ ѻ Ѽ ѽ Ѿ ѿ 
 480 Ҁ ҁ ҂ ҃ ҄ ҅ ҆ ҇ ҈ ҉ Ҋ ҋ Ҍ ҍ Ҏ ҏ 
 490 Ґ ґ Ғ ғ Ҕ ҕ Җ җ Ҙ ҙ Қ қ Ҝ ҝ Ҟ ҟ 
 4A0 Ҡ ҡ Ң ң Ҥ ҥ Ҧ ҧ Ҩ ҩ Ҫ ҫ Ҭ ҭ Ү ү 
 4B0 Ұ ұ Ҳ ҳ Ҵ ҵ Ҷ ҷ Ҹ ҹ Һ һ Ҽ ҽ Ҿ ҿ 
 4C0 Ӏ Ӂ ӂ Ӄ ӄ Ӆ ӆ Ӈ ӈ Ӊ ӊ Ӌ ӌ Ӎ ӎ ӏ 
 4D0 Ӑ ӑ Ӓ ӓ Ӕ ӕ Ӗ ӗ Ә ә Ӛ ӛ Ӝ ӝ Ӟ ӟ 
 4E0 Ӡ ӡ Ӣ ӣ Ӥ ӥ Ӧ ӧ Ө ө Ӫ ӫ Ӭ ӭ Ӯ ӯ 
 4F0 Ӱ ӱ Ӳ ӳ Ӵ ӵ Ӷ ӷ Ӹ ӹ Ӻ ӻ Ӽ ӽ Ӿ ӿ 
0 1 2 3 4 5 6 7 8 9 A B C D E F 
20A0 ₠ ₡ ₢ ₣ ₤ ₥ ₦ ₧ ₨ ₩ ₪ ₫ € ₭ ₮ ₯ 
20B0 ₰ ₱ ₲ ₳ ₴ ₵ ₶ ₷ ₸ ₹ ₺ ₻ ₼ ₽ ₾ ₿ 
```
##### Немного о коде:
Выражение `System.out.printf` используется для форматирования, 
например `System.out.printf("%X ", i);` не перенесет строку, а напечатает 
благодаря ключу `%X`: `X` - большое 16-ричное число, 10-ричное представление которого
функция получит следующим аргументом метода `printf`: `i`.

Для символов `%c` - соответственно.


### Задание 7
Написать приложение для реализации следующего
алгоритма: дана строка, посчитать и вывести на экран следующие
значения: количество букв, сколько из них строчных, сколько прописных,
количество цифр, сколько из них арабских, сколько не арабских,
количество других символов и общее количество всех символов.

```java
public class task7 {
    public static void main(String[] args) {

        String str = "Hello W3orld";

        int len = str.length();

        int upCases = 0;
        int lowCases = 0;
        int arabicNums = 0;
        int nonArabicNums = 0;
        int otherCases = 0;

        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {
                    upCases++;
                } else {
                    lowCases++;
                }
            } else if (Character.isDigit(ch)) {
                if (ch >= '0' && ch <= '9') {
                    arabicNums++;
                } else {
                    nonArabicNums++;
                }
            } else {
                otherCases++;
            }
        }

        System.out.println("Общее количество символов: " + len);
        System.out.println("Количество букв: " + (upCases + lowCases));
        System.out.println("Количество прописных букв: " + lowCases);
        System.out.println("Количество заглавных букв: " + upCases);
        System.out.println("Количество цифр: " + (arabicNums + nonArabicNums));
        System.out.println("Количество арабских цифр: " + arabicNums);
        System.out.println("Количество не арабских цифр: " + nonArabicNums);
        System.out.println("Количество других символов: " + otherCases);
    }
}
```
##### Вывод
```bash
Общее количество символов: 12
Количество букв: 10
Количество прописных букв: 8
Количество заглавных букв: 2
Количество цифр: 1
Количество арабских цифр: 1
Количество не арабских цифр: 0
Количество других символов: 1
```

### Задание 8
Используйте справочную систему Java для того, чтобы
разобраться с тем, как использовать методы `indexOf` класса *String*.

Метод `indexOf` класса `String` возвращает индекс первого вхождения указанной подстроки в строку. 
Если подстрока не найдена, метод возвращает -1.

Синтаксис метода `indexOf`:
```java
int indexOf(String str)
```
где `str` - подстрока, которую нужно найти в строке.

Метод `indexOf` также может быть вызван с дополнительным аргументом - индексом, с которого нужно начать поиск:
```java
int indexOf(String str, int fromIndex)
```
где `str` - подстрока, которую нужно найти в строке, а `fromIndex` - индекс, с которого нужно начать поиск.

Пример использования метода `indexOf`:
```java
String str = "Hello, world!";
int index = str.indexOf("world");
if (index != -1) {
    System.out.println("Слово 'world' найдено в строке " + str + " на позиции " + index);
} else {
    System.out.println("Слово 'world' не найдено в строке " + str);
}
```

### Задание 9
Используйте методы `indexOf` класса *String* для решения и
программной реализации следующей задачи. Посчитать, сколько раз
заданная подстрока встречается в указанной строке.

```java
public class task9 {
    public static void main(String[] args) {
        String str = "Hello, world!";
        String word = "world";
        int count = 0;
        int index = 0;

        while (true) {
            index = str.indexOf(word, index);
            if (index != -1) {
                index = index + word.length();
                count++;
            } else {
                break;
            }
        }
        System.out.println("Количество вхождений слова \"" + word + "\" в строку \"" + str + "\": " + count);
    }
}
```
##### Вывод
```bash
Количество вхождений слова "world" в строку "Hello, world!": 1
```


### Задание 10
Напишите программу для решения следующей задачи.
Дана строка. Найти все ее циклические перестановки. Например, для
строки “abcd” это строки “abcd”, “bcda”, “cdab”, “dabc”.

```java
import java.util.ArrayList;
import java.util.List;

public class task10 {
    public static void main(String[] args) {
        String str = "abcd";
        List<String> permutations = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            permutations.add(str.substring(i) + str.substring(0, i));
        }
        System.out.println(permutations);
    }
}
```
##### Вывод
```bash
[abcd, bcda, cdab, dabc]
```
##### Немного о коде:
Мы используем метод `substring` класса `String`, чтобы создать новые строки, в которых символы сдвигаются на новые позиции.
Метод `substring` имеет следующий синтаксис: substring(индекс_от, индекс_до), где если `индекс_до` не указан, то берется максимальное значение
int => до конца строки, как в случае с `str.substring(i)`.

*Авторство: **Бояршинов Н.О***
