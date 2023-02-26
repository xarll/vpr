# Лабораторная работа #1

## Вступление

Вам предстоит познакомиться с Java

## Работа

### Задание 1

Найдите на локальном диске каталоги, содержащие Java SDK и
стандартную документацию. Из подкаталога с документацией docs загрузите в
браузер файл index.html. Посмотрите, как организована документация. В
дальнейшем ее можно использовать в качестве справочного материала по всем
пакетам, классам, интерфейсам и функциям стандартных библиотек Java SE.
Наберите в строке поиска SEARCH (в правой верхней части окна) строку String.
Посмотрите описания конструкторов и методов этого класса. Чем метод isEmpty
отличается от метода isBlank? 

* `isEmpty` - проверяет, является ли строка пустой ("") или значение null.
* `isBlank` - проверяет, есть-ли в строке пробел, пустая строка ("") или значение null.

### Задание 2

1) Посмотрите, какие значения заданы переменным окружения PATH и
СLASSPATH.
2) Определите версии компилятора Java и интерпретатора команд JVM,
установленных в Вашей системе.

3) Используйте любой текстовый редактор для создания файла Hello.java со
следующим содержимым:
```Java
public class Hello {
    public static void main(String[] s) {
        System.out.println("Hello World!");
    }
}
```
Обратите внимание на то, что имя файла с исходным кодом программы должно
в точности (с учетом регистра символов) совпадать с именем класса, а
расширение должно быть java.

4) Откомпилируйте файл и запустите на выполнение программу. Вы должны
увидеть в консольном окне (или окне терминала)
`Hello World!`
В Windows создайте файлы compile.bat для компиляции и run.bat для запуска
произвольной Java-программы. Поместите в эти файлы команду `set
CLASSPATH=.;%CLASSPATH%` если переменная `CLASSPATH` определена, но
текущий каталог (.) в ней не указан.

Второй вариант:
Создайте пустую директорию, посместите туда файл `Hello.java` из п.3

Для linux:
Создайте файл `compile.sh` с содержимым:
```bash
#!/bin/bash
javac ./*.java -d ./out
```
Дириктория `/out` будет содержать `.class` файлы после запуска

И файл `run.sh` с содержимым:
```bash
#!/bin/bash
java  ./out/ $1  # Арг 1 - имя главного класса
```
Где `$1` - первый позиционный аргумент скрипта

Разрешите выполнение скриптов:
```bash
chmod +x compile.sh
chmod +x run.sh
```

Для windows:
Создайте файл `compile.bat` с содержимым:
```
javac ./*.java -d ./out
pause
```
И файл `run.bat` с содержимым:
```
java ./out/%1
```

5) Если у Вас версия Java 11 или больше, такую простую программу можно
выполнить без явной трансляции. Удалите откомпилированный файл `Hello.class`
и выполните
`java Hello.java`

### Задание 3

Создайте файл `HelloFrame.java` в текстовом редакторе или в
доступной IDE, откомпилируйте и запустите на выполнение программу.
Разберитесь в общих чертах с тем, как она работает.

```Java
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
/** Simple Frame with Label
 @version 1.0
 @author Mister X
 */
public class HelloFrame extends Frame {

    /** Program entry point */
    public static void main(String[] args) throws Exception {
        HelloFrame hello = new HelloFrame();
        hello.setTitle("Hello!");
        hello.setBackground(Color.black);
        hello.setLayout(new FlowLayout());
        hello.setLabelAttributes(firstLabel);
        hello.setLabelAttributes(secondLabel);
        hello.add(firstLabel);
        hello.add(secondLabel);
        hello.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Random rnd = new Random();
                int idx = rnd.nextInt(4);
                secondLabel.setText(text[idx]);
            }
        });
        hello.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        hello.setBounds(100,100,260, 80);
        hello.setVisible(true);
    }
    /** method that changes Label attributes
     @param label - concrete Label
     */
    private void setLabelAttributes(Label label) {
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 22);
        label.setFont(font);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.YELLOW);
    }
    /** field - reference array of strings */
    private static String[] text = {"Word", "Lord", "Nord", "Sword"};

    /** field - Label for preamble */
    private static final Label firstLabel = new Label("Hello my");
    /** field - Label for variants of answers */
    private static final Label secondLabel = new Label(text[0]);
}
```

### Задание 4

1) Заархивируйте файлы с расширением class, полученные после
компиляции программы `HelloFrame.java` из предыдущего задания.

Создайте отдельный пустой каталог, переместите туда файл из
`out/.../HelloFrame.class` и создайте файл `manif.txt`
с содержимым:

```
Main-Class: HelloFrame
```
Внимание! Файл чувствителен к регистру

Откройте терминал в этом каталоге, выполните:
```bash
jar cmfv manif.txt HelloFrame.jar *.class
```
Тем самым создастся файл `HelloFrame.jar` в этом каталоге

2) Запустите на выполнение программу `HelloFrame` из архива.

```bash
java -jar HelloFrame.jar
```
3) Создайте выполняемый архив для программы `HelloFrame`. Проверьте
работоспособность программы.

***Внимание:** Пункт 3 излишен, так как его замещают пункты 1 и 2*

### Задание 5

Создайте документацию для `HelloFrame.java`. Для этого скопируйте
сам этот файл в отдельный каталог и запустите javadoc:
```bash
javadoc HelloFrame.java
```

Посмотрите на html-файлы, созданные этой программой. Можно запустить,
например, `index.html`. Отметьте, насколько форма подачи результата похожа на
то, что содержится в стандартной документации.
Скачать руководство пользователя по javadoc (SDK 15) можно по [ссылке](https://docs.oracle.com/en/java/javase/15/javadoc/javadoc-guide.pdf)

*Авторство: **Бояршинов Н.О***
