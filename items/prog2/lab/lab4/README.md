# Лабораторная работа #4

## Вступление

Все классы, созданные по нижеприведенным заданиям, должны
размещаться в пакетах (например, `lab4.car`, `lab4.complex`).

Так как много больших математических формул в описании заданий, то я их сократил до минимального вида. Полное задание в методичке

## Готовые проекты

- [Шведенко/Тузов](https://github.com/xarll/vpr/tree/main/items/prog2/lab/lab4/program/project_one)
- [Лунь Архипов]()

## Работа

### Задание 1
Создайте класс Car, представляющий понятие
"автомобиль". Каждый автомобиль должен иметь, как минимум,
следующие характеристики: регистрационный знак, марка, вид, цвет,
мощность двигателя, количество колес. Для вновь созданного,
конкретного автомобиля такие характеристики как марка, вид, цвет,
мощность двигателя, количество колес должны быть заданы
непременно, но регистрационного номера у него до поры до времени
может и не быть (а может и быть). Все характеристики автомобиля,
кроме марки, вида и количества колес можно изменять в процессе его
эксплуатации. Вид автомобиля - легковой, грузовой, автобус. Создайте
и используйте для задания вида автомобиля перечислимый тип. Для
легковых, грузовых автомобилей и автобусов с нормальным
креплением знака (тип 1) согласно ГОСТ Р 50577-2018 [6] знак имеет
следующий формат: X 000 XX 00 RUS или X 000 XX 000 RUS. Здесь 0 в
реальном знаке заменяется какой-то арабской цифрой, а X - одной из
12 букв кириллицы (в верхнем регистре), написание которой совпадает
с написанием латинской буквы: А, В, Е, К, М, Н, О, Р, С, Т, У, Х. Попытка
задания неправильного знака должна пресекаться соответствующим
методом класса. Для проверки используйте регулярное выражение.
Создайте код для тестирования класса Car с заданием начальных
характеристик, запросом новых значений для тех характеристик,
которые можно изменять и выводом на экран текущих значений всех
характеристик.

<details>
  <summary>Task1/Car.java</summary>
  
  ```java
 package Task1;

public abstract class Car {
    private String regPlatePattern;

    protected String regPlate;
    private final String brand;
    protected String color;
    protected int enginePower;
    private final int numOfWheels;

    public Car(String brand, String color, int enginePower, int numOfWheels) {
        this.brand = brand;
        this.color = color;
        this.enginePower = enginePower;
        this.numOfWheels = numOfWheels;

    }

    public Car(String brand, String color, int enginePower, int numOfWheels, String regPlatePattern) {
        this.brand = brand;
        this.color = color;
        this.enginePower = enginePower;
        this.numOfWheels = numOfWheels;
        this.regPlatePattern = regPlatePattern;
    }


    public String getRegPlate() { return  this.regPlate;}
    public void setRegPlate(String value) {
        if ((this.regPlatePattern != null) && (!value.matches(this.regPlatePattern))) {
            throw new IllegalArgumentException("Invalid registration plate");
        }
        this.regPlate = value;
    }
    public String getBrand() { return  this.brand;}
    public String getColor() { return  this.color;}
    public void setColor(String value) { this.color = value;}
    public int getEnginePower() { return  this.enginePower;}
    public void setEnginePower(int value) { this.enginePower = value;}
    public int getNumOfWheels() {return this.numOfWheels;}

    
}

  ```
 
</details>

<details>
  <summary>Task1/CarFactory.java</summary>
  
  ```java
 package Task1;


public class CarFactory {
    public static Car createCar(CarTypes type, String brand, String color, int enginePower, int numOfWheels) {
        return switch (type) {
            case passenger -> new PassengerCar(
                    brand,
                    color,
                    enginePower,
                    numOfWheels,
                    "[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}RUS"
            );
            case freight -> new FreightCar(
                    brand,
                    color,
                    enginePower,
                    numOfWheels,
                    "[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}RUS"
            );
            case bus -> new Bus(
                    brand,
                    color,
                    enginePower,
                    numOfWheels,
                    "[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}RUS"
            );
        };
    }
}

  ```
 
</details>


<details>
  <summary>Task1/CarTypes.java</summary>
  
  ```java
 package Task1;

public enum CarTypes {
    passenger,
    freight,
    bus,
}

  ```
 
</details>


<details>
  <summary>Task1/Bus.java</summary>
  
  ```java
 package Task1;

public class Bus extends Car {
    public Bus(String brand, String color, int enginePower, int numOfWheels) {
        super(brand, color, enginePower, numOfWheels);
    }

    public Bus(String brand, String color, int enginePower, int numOfWheels, String regPlatePattern) {
        super(brand, color, enginePower, numOfWheels, regPlatePattern);
    }
}

  ```
 
</details>


<details>
  <summary>Task1/FreightCar.java</summary>
  
  ```java
 package Task1;

public class FreightCar extends Car {
    public FreightCar(String brand, String color, int enginePower, int numOfWheels) {
        super(brand, color, enginePower, numOfWheels);
    }

    public FreightCar(String brand, String color, int enginePower, int numOfWheels, String regPlatePattern) {
        super(brand, color, enginePower, numOfWheels, regPlatePattern);
    }
}

  ```
 
</details>


<details>
  <summary>Task1/PassengerCar.java</summary>
  
  ```java
 package Task1;

public class PassengerCar extends Car {
    public PassengerCar(String brand, String color, int enginePower, int numOfWheels) {
        super(brand, color, enginePower, numOfWheels);
    }

    public PassengerCar(String brand, String color, int enginePower, int numOfWheels, String regPlatePattern) {
        super(brand, color, enginePower, numOfWheels, regPlatePattern);
    }
}

 
  ```
 
</details>


<details>
  <summary>Task1/Test.java</summary>
  
  ```java
 package Task1;

public class Test {
    public static void main(String[] args) {
        Car car = CarFactory.createCar(CarTypes.bus, "Volvo", "Red", 100, 4);
        System.out.println(car.getBrand());
        System.out.println(car.getColor());
        System.out.println(car.getEnginePower());
        System.out.println(car.getNumOfWheels());

        System.out.println(car.getRegPlate()); // null
        car.setRegPlate("А123ВС77RUS");
        System.out.println(car.getRegPlate()); // А123ВС77RUS

        car.setColor("Blue");
        System.out.println(car.getColor()); // Blue

        car.setRegPlate("123ВС77RUS");
        System.out.println(car.getRegPlate()); // exception
    }
}

  ```
 
</details>


PS: Вообще enum в моем случае излишен, но я не хотел спорить с преподом


### Задание 2
В Java отсутствует стандартный класс для представления
комплексных чисел. Создайте такой класс (Complex), поддерживающий
операции получения действительной, мнимой частей числа, сложения,
вычитания, умножения, деления, комплексного сопряжения, проверки
двух чисел на равенство, вывода значения комплексного числа в
алгебраической и тригонометрической формах. Так как действительное
число, это комплексное число с нулевой мнимой частью, должны
поддерживаться также арифметические операции, в которых один из
аргументов действительное число (тип double).


<details>
  <summary>Task2/Complex.java</summary>
  
  ```java
package Task2;

/**
 * @param real действительная часть
 * @param imag мнимая часть
 */
public record Complex(double real, double imag) {

    public Complex add(Complex other) {
        double r = real + other.real;
        double i = imag + other.imag;
        return new Complex(r, i);
    }

    public Complex subtract(Complex other) {
        double r = real - other.real;
        double i = imag - other.imag;
        return new Complex(r, i);
    }

    public Complex multiply(Complex other) {
        double r = real * other.real - imag * other.imag;
        double i = real * other.imag + imag * other.real;
        return new Complex(r, i);
    }

    public Complex divide(Complex other) {
        double denom = other.real * other.real + other.imag * other.imag;
        double r = (real * other.real + imag * other.imag) / denom;
        double i = (imag * other.real - real * other.imag) / denom;
        return new Complex(r, i);
    }

    public Complex conjugate() {
        return new Complex(real, -imag);
    }

    public boolean equals(Complex other) {
        return real == other.real && imag == other.imag;
    }

    public String toString() {
        if (imag == 0) {
            return String.format("%.2f", real);
        } else if (imag > 0) {
            return String.format("%.2f + %.2fi", real, imag);
        } else {
            return String.format("%.2f - %.2fi", real, -imag);
        }
    }

    public String toTrigString() {
        double r = Math.sqrt(real * real + imag * imag);
        double theta = Math.atan2(imag, real);
        return String.format("%.2f(cos(%.2f) + i sin(%.2f))", r, theta, theta);
    }

    /**
     * @return аргумент комплексного числа (phi)
     */
    public double phase() {
        return Math.atan2(imag, real);
    }

    public Complex fromPolar(double r, double theta) {
        double x = r * Math.cos(theta);
        double y = r * Math.sin(theta);
        return new Complex(x, y);
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
        Complex a = new Complex(1, 2);
        Complex b = new Complex(3, 4);

        System.out.println(a.add(b));
        System.out.println(a.subtract(b));
        System.out.println(a.multiply(b));
        System.out.println(a.divide(b));

    }
}

  ```
 
</details>




### Задание 3
Реализуйте методы для вычисления элементарных
функций комплексного переменного z = ..., 
sin(z) = ..., cos(z) = ..., tan(z) = ..., arctan(z) = ...
sh(z) = ..., ch(z) = ..., , th(z) = ..., cth(z) = ...
Формула Эйлера: `e^(i*x) = cos(x)+i*sin(x)`, `x` - действительное число.
Организуйте методы так, чтобы их можно было вызывать, не создавая
объекты класса, которому принадлежат эти методы. Прежде чем писать
код, ответьте себе на вопрос, должны ли эти методы быть методами
класса Complex, созданного в предыдущем задании?

<details>
  <summary>Task3/ComplexMath.java</summary>
  
  ```java
 package Task3;

import Task2.Complex;

public class ComplexMath {

    public static Complex sin(Complex z) {
        double real = Math.sin(z.real()) * Math.cosh(z.imag());
        double imag = Math.cos(z.real()) * Math.sinh(z.imag());
        return new Complex(real, imag);
    }

    public static Complex cos(Complex z) {
        double real = Math.cos(z.real()) * Math.cosh(z.imag());
        double imag = -Math.sin(z.real()) * Math.sinh(z.imag());
        return new Complex(real, imag);
    }

    public static Complex tan(Complex z) {
        Complex numerator = sin(z);
        Complex denominator = cos(z);
        return numerator.divide(denominator);
    }

    public static Complex arctan(Complex z) {
        Complex numerator = new Complex(1, 0).subtract(new Complex(0, 1).multiply(z));
        Complex denominator = new Complex(1, 0).add(new Complex(0, 1).multiply(z));
        Complex quotient = numerator.divide(denominator);
        return new Complex(0, -0.5).multiply(
                log(new Complex(1, 0).subtract(quotient)).subtract(
                        log(new Complex(1, 0).add(quotient))
                )
        );
    }

    public static Complex sinh(Complex z) {
        double real = Math.sinh(z.real()) * Math.cos(z.imag());
        double imag = Math.cosh(z.real()) * Math.sin(z.imag());
        return new Complex(real, imag);
    }

    public static Complex cosh(Complex z) {
        double real = Math.cosh(z.real()) * Math.cos(z.imag());
        double imag = Math.sinh(z.real()) * Math.sin(z.imag());
        return new Complex(real, imag);
    }

    public static Complex tanh(Complex z) {
        Complex numerator = sinh(z);
        Complex denominator = cosh(z);
        return numerator.divide(denominator);
    }

    public static Complex coth(Complex z) {
        Complex numerator = new Complex(1, 0).add(new Complex(0, 1).multiply(z));
        Complex denominator = new Complex(1, 0).subtract(new Complex(0, 1).multiply(z));
        return numerator.divide(denominator);
    }

    public static Complex exp(Complex z) {
        double real = Math.exp(z.real()) * Math.cos(z.imag());
        double imag = Math.exp(z.real()) * Math.sin(z.imag());
        return new Complex(real, imag);
    }


    public static double abs(Complex z) {
        return Math.sqrt(z.real() * z.real() + z.imag() * z.imag());
    }


    public static Complex log(Complex z) {
        double real = Math.log(abs(z));
        double imag = z.phase();
        return new Complex(real, imag);
    }
}
  ```
 
</details>


<details>
  <summary>Task3/Test.java</summary>
  
  ```java
 package Task3;

import Task2.Complex;

public class Test {
    public static void main(String[] args) {
        Complex z1 = new Complex(3, 4);
        Complex z2 = new Complex(2, 5);

        System.out.println("z1 = " + z1);
        System.out.println("z2 = " + z2);


        System.out.println("\nz1 + z2 = " + z1.add(z2));
        System.out.println("z1 - z2 = " + z1.subtract(z2));
        System.out.println("z1 * z2 = " + z1.multiply(z2));
        System.out.println("z1 / z2 = " + z1.divide(z2));

        System.out.println("\nconjugate(z1) = " + z1.conjugate());
        System.out.println("abs(z1) = " + ComplexMath.abs(z1));
        System.out.println("phase(z1) = " + z1.phase());
        System.out.println("exp(z1) = " + ComplexMath.exp(z1));
        System.out.println("sin(z1) = " + ComplexMath.sin(z1));
        System.out.println("cos(z1) = " + ComplexMath.cos(z1));
        System.out.println("tan(z1) = " + ComplexMath.tan(z1));
        System.out.println("arctan(z1) = " + ComplexMath.arctan(z1));
        System.out.println("sinh(z1) = " + ComplexMath.sinh(z1));
        System.out.println("cosh(z1) = " + ComplexMath.cosh(z1));
        System.out.println("tanh(z1) = " + ComplexMath.tanh(z1));
        System.out.println("coth(z1) = " + ComplexMath.coth(z1));
        System.out.println("exp(z1) = " + ComplexMath.exp(z1));

        System.out.println("\nAsTrigString(z1) = " + z1.toTrigString());
        System.out.println("AsString(z1) = " + z1.toString());
    }
}

  ```
 
</details>

### Задание 4
В Задании 1 двигатель автомобиля представлен только
одной своей характеристикой - мощностью. А самом деле каждый
двигатель имеет серийный (заводской) номер, и, помимо мощности,
рабочий объем, расход топлива, вид топлива, число цилиндров и т.д.
Поэтому для такого важного понятия удобно ввести отдельный класс.
Создайте класс Engine, включив в него несколько важных характеристик
двигателя и методы доступа к этим характеристикам. Определите и
реализуйте нужный конструктор (или конструкторы) класса Engine.
Замените в классе Car поле "мощность" на поле "двигатель" (engine).
Измените код тестирования для проверки работоспособности новой
версии класса Car и класса Engine.


<details>
  <summary>Task4/Engine.java</summary>
  
```java
package Task4;

public class Engine {
    private final String serialNumber;
    private final double power;
    private final double displacement;
    private final String fuelType;
    private final int numberOfCylinders;

    public Engine(String serialNumber, double power, double displacement, String fuelType, int numberOfCylinders) {
        this.serialNumber = serialNumber;
        this.power = power;
        this.displacement = displacement;
        this.fuelType = fuelType;
        this.numberOfCylinders = numberOfCylinders;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getPower() {
        return power;
    }

    public double getDisplacement() {
        return displacement;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }

    public String toString() {
        return String.format(
                "Engine: %s, %s, %s, %s, %s",
                this.serialNumber,
                this.power,
                this.displacement,
                this.fuelType,
                this.numberOfCylinders
        );
    }
}


```
  
</details>


PS: В этом и последующих заданиях нас просят менять таск1, => я не буду публиковать измененный таск1 далее, а опубликую весь готовый проект в отдельной директории.



### Задание 5
В Заданиях 1, 4 вид автомобиля задается как
предопределенное значение. Появление новых видов будет приводить
к необходимости модифицировать класс Car. Кроме того, для новых
видов автомобилей правила формирования регистрационного знака
могут быть другими. Более гибкое решение - каждый вид автомобиля
представлять собственным классом. С другой стороны, все автомобили 
будут обладать одинаковым набором некоторых базовых
характеристик. Не разумно при возникновении нового вида автомобиля
всякий раз повторять в нем объявления этих базовых характеристик и
определять методы доступа к ним. Нужно использовать семейство
родственных классов, в котором один (Car) будет содержать все общие
характеристики, а другие - классы для конкретных видов автомобилей
дополнительные характеристики и/или конкретные значения для
базовых характеристик. Измените нужным образом класс Car, создайте
классы для следующих видов автомобилей: легковой, грузовой,
автобус, специальный (например, пожарная машина, или автомобиль
для дипломатических миссий). Для специальных автомобилей
придумайте свою схему формирования регистрационного знака (или
возьмите из ГОСТ Р 50577-2018 [6]).


Удаляем Enum и заменяем его на строковый тип, а всё остальное из этой таски реализовано в самом начале


### Задание 6
. В Задании 5 класс Car содержит общую функциональность
семейства автомобилей разных видов, но не представляет какие-то
конкретные автомобили. Не логично разрешать пользователям
создавать в программе экземпляры класса Car. Кроме того, некоторые
методы доступа к базовым характеристикам автомобиля, заданные в
классе Car не должны переопределяться в классах-наследниках. Если
это будет сделано случайно или преднамеренно, изменится базовое
поведение некоторых автомобилей, а этого не должно происходить по
логике организации семейства классов автомобилей. Нужно запретить
наследникам переопределять такие методы базового класса. Наконец,
нужно задаться вопросом, насколько расширяемой должна быть наша
система родственных классов. Например, есть ли какие-то особые
формы автобусов, для которых нужно построить класс-наследник
имеющегося класса? А для других классов, представляющих
автомобили? Сделайте так, чтобы, по крайней мере, класс
представляющий автобусы нельзя было расширять.


Добавим ключевое слово `final` к классу наследнику, чтобы нельзя было расширять его:

```java
public final class Bus extends Car {...}
```


### Задание 7
Создайте класс "Автобаза". На базе может размещаться
некоторое фиксированное количество автомобилей разных видов
(классы производные от Car). Для хранения объектов, представляющих
автомобили нужно использовать массив фиксированного размера.
Максимально возможный размер для конкретной автобазы (конкретного
объекта класса) задается при создании объекта. Каждый автомобиль
может находиться в одном из трех допустимых состояний: на базе, в
рейсе, в ремонте. Нужно обеспечить добавление нового автомобиля
(если еще есть место для размещения автомобиля). Удаление 
30
(списание) автомобиля. Отправку исправного автомобиля в рейс.
Отправку неисправного автомобиля в ремонт. Возврат автомобиля из
рейса или из ремонта. Отображение на экране списка находящихся на
базе исправных автомобилей, списка автомобилей, находящихся в
рейсе, списка неисправных автомобилей (отдельные методы). 

<details>
  <summary>Task7/AutoBase.java</summary>
  
```java
package task7;

import Task1.Car;

public class AutoBase {
    private final Car[] parkedCars;
    private final Car[] inTransitCars;
    private final Car[] inRepairCars;

    public AutoBase(int maxCars) {
        parkedCars = new Car[maxCars];
        inTransitCars = new Car[maxCars];
        inRepairCars = new Car[maxCars];
    }

    public void addCarToBase(Car car) {
        addCarToArray(car, parkedCars);
    }

    public void removeCarFromBase(Car car) {
        removeCarFromArray(car, parkedCars);
    }

    public void sendCarToTransit(Car car) {
        moveCarToArray(car, parkedCars, inTransitCars);
    }

    public void returnCarFromTransit(Car car) {
        moveCarToArray(car, inTransitCars, parkedCars);
    }

    public void sendCarToRepair(Car car) {
        moveCarToArray(car, parkedCars, inRepairCars);
    }

    public void returnCarFromRepair(Car car) {
        moveCarToArray(car, inRepairCars, parkedCars);
    }

    public Car[] getParkedCars() {
        return parkedCars;
    }

    public Car[] getInTransitCars() {
        return inTransitCars;
    }

    public Car[] getInRepairCars() {
        return inRepairCars;
    }

    private void addCarToArray(Car car, Car[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = car;
                return;
            }
        }
        throw new IllegalStateException("The array is full");
    }

    private void removeCarFromArray(Car car, Car[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == car) {
                array[i] = null;
                return;
            }
        }
        throw new IllegalArgumentException("The car is not in the array");
    }

    private void moveCarToArray(Car car, Car[] fromArray, Car[] toArray) {
        removeCarFromArray(car, fromArray);
        addCarToArray(car, toArray);
    }

    private int getArrayCarsCount(Car[] parkedCars) {
        int count = 0;
        for (Car car : parkedCars) {
            if (car != null) {
                count++;
            }
        }
        return count;
    }

    public String toString() {
        return "Parked cars: " + getArrayCarsCount(parkedCars) + "\n" +
                "In transit cars: " + getArrayCarsCount(inTransitCars) + "\n" +
                "In repair cars: " + getArrayCarsCount(inRepairCars);
    }
}

```
  
</details>

<details>
  <summary>Task7/Test.java</summary>
  
```java
package task7;

import Task1.Car;
import Task1.CarFactory;
import Task4.Engine;

public class Test {
    public static void main(String[] args) {
        AutoBase autobase = new AutoBase(5);

        Engine engine = new Engine(
                "1234567890",
                100,
                1.6,
                "gasoline",
                4
        );

        Car bus1 = CarFactory.createCar("bus", "red", "A123AA", engine, 6);
        Car bus2 = CarFactory.createCar("bus", "red", "A111AA", engine, 6);

        autobase.addCarToBase(bus1);
        autobase.addCarToBase(bus2);

        System.out.println(autobase.toString());

        autobase.removeCarFromBase(bus1);
        System.out.println();
        System.out.println(autobase.toString());

        autobase.sendCarToTransit(bus2);
        System.out.println();
        System.out.println(autobase.toString());

        autobase.returnCarFromTransit(bus2);
        System.out.println();
        System.out.println(autobase.toString());


        autobase.sendCarToRepair(bus2);
        System.out.println();
        System.out.println(autobase.toString());

        autobase.returnCarFromRepair(bus2);
        System.out.println();
        System.out.println(autobase.toString());

    }
}

```
  
</details>

### Задание 8


 Средство построения графиков функций может
генерировать рисунки подобные следующему. Отвлекаясь пока от
графического изображения спроектируйте
систему классов для решения такой задачи
в объектно-ориентированном стиле (в виде
UML-диаграммы классов). Каждое важное
понятие задачи нужно представить
собственным классом. Например, на
рисунке есть собственно весь график, на
нем отдельные кривые, оси координат,
координатная сетка, пояснения. Это все
важные понятия в решаемой задаче.
Каждый класс отвечает за свой функционал. В частности, каждый класс
отвечает за прорисовку своих объектов. Определите, какие параметры
должны хранить соответствующие классы, какие у них должны быть
методы и изобразите на UML-диаграмме классов сами классы, их поля
и методы, отношения между классами.


### Задание 9
Создайте классы для построенной в Задании 7 UMLдиаграммы классов. Вместо реального рисования нужно обеспечить
вывод на экран текстовой информации со значениями параметров
объектов соответствующих классов.

*Библиотека графического пользовательского интерфейса AWT предлагает
использовать в качестве главного окна программы объект класса Frame. Frame
представляет окно с полосой заголовка на которой, помимо заголовка,
размещаются также элементы управления для сворачивания окна,
разворачивания его на весь экран и закрытия окна, рамкой, позволяющей
нужным образом изменять размеры окна и рабочей областью, которая является
контейнером, то есть может содержать другие элементы пользовательского
интерфейса. Для рисования в AWT есть класс Canvas ("полотно"). Этот класс
представляет подчиненное окно без заголовка и рамки. Можно разместить
объект Canvas в рабочей области Frame так, чтобы "полотно" занимало всю
рабочую область. Чтобы Canvas всегда занимал всю рабочую область Frame
нужно при изменении размера Frame соответствующим образом изменять и
размер Canvas. AWT предлагает стандартные менеджеры размещения
(компоновки) которые берут на себя задачу изменения размеров элементов
управления, размещенных в окне Frame. При этом возможны разные варианты
изменений размеров, только по горизонтали, только по вертикали и более
сложные. Например, менеджер компоновки BorderLayout позволяет так
разместить элемент управления в окне, чтобы "подстраивались" одновременно
и ширина, и высота Canvas.*

В 8-9 заданиях нас просят просто описать класс для всяких плотов и графов и работы с ним. Я не стал этим заниматься так как нет интереса. Возьмите эту таску из раздела "Готовые проекты" [выше](https://github.com/xarll/vpr/blob/main/items/prog2/lab/lab4/README.md#%D0%B3%D0%BE%D1%82%D0%BE%D0%B2%D1%8B%D0%B5-%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82%D1%8B)



*Авторство: **Бояршинов Н.О***
