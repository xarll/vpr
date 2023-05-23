# Лабораторная работа #6

## Вступление

Вам предстоит реализовать графические приложения на Java

## Готовые проекты

- [...](...)

## Работа

### Задание 1
Создайте приложение, отображающее в окне 300x300
пикселей график кривой f(x) = sin(x) на интервале x от -π до π.

![image](https://github.com/xarll/vpr/assets/76239707/b05f37bf-ea25-4365-b617-72783b77e52a)

<details>
  <summary>task1/Task1.java</summary>
  
```java
package task1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Task1 {
    public Task1() {
        JFrame frame = new JFrame("Task1");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                int w = getWidth(), h = getHeight();
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, w - 1, h - 1);
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3));
                g.setColor(Color.RED);

                double xScale = w / (2 * Math.PI); // Масштаб x по размеру панели
                double yScale = h / 2.0;           // Масштаб y по размеру панели
                double xOffset = w / 2.0;          // Центр x
                double yOffset = h / 2.0;          // Центр y
                double yPrev = Math.sin(-Math.PI); // Предыдущее значение y

                for (double x = -Math.PI; x <= Math.PI; x += 0.01) {
                    double y = Math.sin(x);
                    
                    int x1 = (int) Math.round((x - 0.01) * xScale + xOffset);
                    int y1 = (int) Math.round(-yPrev * yScale + yOffset);
                    int x2 = (int) Math.round(x * xScale + xOffset);
                    int y2 = (int) Math.round(-y * yScale + yOffset);

                    g.drawLine(x1, y1, x2, y2);
                    yPrev = y;
                }
            }
        };
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Task1();
    }
}


```
  
</details>


### Задание 2
Создайте класс Curve для отображения 2D-графика
гладкой функции f(x) на заданном интервале изменения аргумента x. В
классе должны быть предусмотрены два массива (или один двумерный
массив) для хранения таблицы значений аргумента и функции, и метод
setData, позволяющий пользователю класса задавать данные для
конкретной функции и конкретного интервала по x. Необходимо
предусмотреть методы для задания координат прямоугольной области,
в которой должен отображаться график, и для отображения самого
графика. Создайте приложение, отображающий в окне 300x300
пикселей график кривой f(x) = sin(x) на интервале x от 0 до 2*π с
помощью объекта класса Curve.

<details>
  <summary>task2/Curve.java</summary>
  
```java
 package task2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Curve extends JPanel {
    private double[] xValues;
    private double[] yValues;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    public Curve() {
        this.xMin = 0;
        this.xMax = 1;
        this.yMin = 0;
        this.yMax = 1;
    }

    public void setData(double[] xValues, double[] yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
    }

    public void setBounds(double xMin, double xMax, double yMin, double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        Point lastPoint = null;
        for(int i = 0; i < xValues.length; i++) {
            double x = xValues[i];
            double y = yValues[i];
            int xPixel = (int)(getWidth() * (x - xMin) / (xMax - xMin));
            int yPixel = (int)(getHeight() * (1 - (y - yMin) / (yMax - yMin)));
            Point currentPoint = new Point(xPixel, yPixel);
            if(lastPoint != null) {
                g.drawLine(lastPoint.x, lastPoint.y, currentPoint.x, currentPoint.y);
            }
            lastPoint = currentPoint;
        }
    }

    public static void main(String[] args) {
        Curve curve = new Curve();
        double[] xValues = new double[629];
        double[] yValues = new double[629];
        int i = 0;
        for (double x = 0; x <= 2* Math.PI; x += 0.01) {
            xValues[i] = x;
            yValues[i] = Math.sin(x);
            i++;
        }
        curve.setData(xValues, yValues);
        curve.setBounds(0, 2 * Math.PI, -1, 1);

        JFrame frame = new JFrame("Curve");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(curve);
        frame.setVisible(true);
    }
}

```
  
</details>


### Задание 3
Модифицируйте систему классов из задания 9
лабораторной работы "Классы в Java": Graph, Axis, Curve так, чтобы
происходило реальное отображение графика в окне приложения.

<details>
  <summary>Task/Pair.java</summary>
  
```java

```
  
</details>


### Задание 4
Создайте приложение, в окне которого при щелчке мышью
на месте курсора отображаются его координаты. Цвет отображения
задается пользователем с помощью клавиатуры. Клавиши b, w, r, g, o
задают цвет символов (b - black, w - white, r - red, g - green, o - orange).

<details>
  <summary>task4/Task4.java</summary>
  
```java
package task4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Task4 {
    private final JLabel coordinatesLabel;
    private Color color = Color.BLACK;

    public Task4() {
        JFrame frame = new JFrame("Координаты мыши");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            public boolean isFocusable() {
                return true;
            }
        };

        coordinatesLabel = new JLabel("Нажмите на по области окна");
        coordinatesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        coordinatesLabel.setForeground(color);
        panel.add(coordinatesLabel);

        frame.add(panel);
        frame.pack();
        frame.setSize(300, 300);
        frame.setVisible(true);
        panel.requestFocus();

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int x = event.getX();
                int y = event.getY();
                String coordinates = String.format("Координаты: (%d, %d)", x, y);
                coordinatesLabel.setText(coordinates);
                coordinatesLabel.setForeground(color);
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'b' -> color = Color.BLACK;
                    case 'w' -> color = Color.WHITE;
                    case 'r' -> color = Color.RED;
                    case 'g' -> color = Color.GREEN;
                    case 'o' -> color = Color.ORANGE;
                }
                coordinatesLabel.setForeground(color);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Task4::new);
    }
}

```
  
</details>


### Задание 5
Создайте приложение, загружающее и отображающее
некоторое заданное в программе изображение из файла (JPEG или
GIF). Изображение должно отображаться, занимая максимально
возможную часть окна, подстраиваясь под изменение размера окна
пользователем, но при этом отношение ширины изображения к его
высоте должно оставаться неизменным (не должны меняться
пропорции изображения). По щелчку мышью в области окна
изображение в нем должно переворачиваться (поворачиваться на 180
градусов).

<details>
  <summary>Task/Pair.java</summary>
  
```java
```
  
</details>


### Задание 6
Эффект размытия (Blur) - каждая цветовая компонента
пикселя (канал): красная, зеленая, синяя, заменяется на среднее
значение соответствующих каналов соседних пикселей (и самого
текущего пикселя). Например, можно взять область из 3x3 пикселей и
рассмотреть центральный пиксель. Все остальные будут его
ближайшими соседями. Находим среднее значение для каждого из их
цветовых каналов и заменяем на эти значения цветовые каналы
центрального пикселя. То же самое можно сделать для области 5x5
пикселей и т.д. Эффект преобразования в серые цвета - цветное
изображение преобразуется в оттенки серого цвета. Для каждого
пикселя находится среднее его значений по разным каналам: красному,
зеленому и синему. Затем каждый канал получает это среднее
значение. Измените программу из задания 5 так, чтобы с помощью
клавиатуры можно было менять фильтры: вращение на 180 градусов -
клавиша R или r, преобразование в оттенки серого (можно выполнить
только один раз) - клавиша G или g, размытие - клавиша B или b (с
учетом только ближайших соседей и самого пикселя). Собственно
эффект возникает только после щелчка мышью по области окна.

<details>
  <summary>Task/Pair.java</summary>
  
```java
```
  
</details>


### Задание 7
Создайте приложение с анимацией. По щелчку мышью
создается в произвольной позиции окна круг заданного цвета со
случайным значением модуля скорости из некоторого диапазона.
Можно задать для всех шариков направление скорости 45 градусов
против часовой стрелки относительно горизонтальной оси. Шарики
двигаются и отражаются от стенок. Столкновения не обрабатываются.

<details>
  <summary>Task/Pair.java</summary>
  
```java
```
  
</details>


### Задание 8
Создайте приложение "Бегущая строка". В программе
задается массив символьных строк (сообщений) и в окне программы
прокручивается одно из этих сообщение, за счет вывода этой строки
так, как будто она движется по экрану слева-направо. Щелчок мышью 
должен приводить к смене сообщения, то есть к выбору (случайным
образом) другой строки из массива.

<details>
  <summary>Task/Pair.java</summary>
  
```java
```
  
</details>



Авторство: Бояршинов Н.О
