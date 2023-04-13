import Task1.Car;
import Task2.*;
import Task3.*;
import Task4.Engine;
import Task5.Bus;
import Task5.CargoCar;
import Task5.SpecialCar;
import Task7.MotorDepot;
import Task9.Func;
import Task9.Graph;
import Task9.GraphShower;

import java.awt.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        Engine engine = new Engine(4, 200, 20, 10000, Engine.FuelType.бензин, "В345ВК104RUS");
        System.out.println("Task1");
        System.out.println("=".repeat(100));
        Car car = new Car(Car.Model.легковой, "ТОЙОТА", 4, engine, Color.BLUE, "А432ВВ544RUS");
        car.showInfo();
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        do{
            System.out.println("Заменить желаемую характеристику\n1-Цвет\n2-Двигатель\n3-знак\n-1 - выход:");
            i = scanner.nextInt();
            switch(i){
                case 1:
                    System.out.println("Выберете цвет\n1-синий\n2-красный\n3-белый:");
                    int color = scanner.nextInt();
                    switch (color){
                        case 1:
                            car.setColor(Color.BLUE);
                            break;
                        case 2:
                            car.setColor(Color.RED);
                            break;
                        case 3:
                            car.setColor(Color.WHITE);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Выберете двигатель\n1-бензиновый\n2-дизельный\n3-газовый:");
                    int eng = scanner.nextInt();
                    switch (eng){
                        case 1:
                            car.setEngine(new Engine(4, 200, 20, 10000, Engine.FuelType.бензин, "В345ВК104RUS"));
                            break;
                        case 2:
                            car.setEngine(new Engine(4, 200, 20, 10000, Engine.FuelType.дизель, "В345ВК104RUS"));
                            break;
                        case 3:
                            car.setEngine(new Engine(4, 200, 20, 10000, Engine.FuelType.газ, "В345ВК104RUS"));
                            break;
                    }
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.println("Введите знак:");
                    String sing = scanner.nextLine();
                    car.setRegisterSign(sing);
            }
            car.showInfo();
        }
        while (i!=-1);
        System.out.println("=".repeat(100));

        System.out.println("Task2");
        System.out.println("=".repeat(100));
        Complex num1 = new Complex(2, 5);
        Complex num2 = new Complex(2, 10);
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(Complex.add(num1, num2));
        System.out.println(Complex.sub(num1, num2));
        System.out.println(Complex.mul(num1, num2));
        System.out.println(Complex.div(num1, num2));
        if(Complex.isEquals(num1,num2)){
            System.out.println("Равны");
        }
        else {
            System.out.println("Не равны");
        }
        if(Complex.isConjugated(num1, num2)){
            System.out.println("Сопряжены");
        }
        else {
            System.out.println("Не сопряжены");
        }
        System.out.println(num1.toTrigonometryForm());
        System.out.println("=".repeat(100));

        System.out.println("Task3");
        System.out.println("=".repeat(100));
        System.out.println("sin: "+ComplexFuncs.sin(num1));
        System.out.println("cos: "+ComplexFuncs.cos(num1));
        System.out.println("tan: "+ComplexFuncs.tan(num1));
        System.out.println("atan: "+ComplexFuncs.atan(num1));
        System.out.println("sh: "+ComplexFuncs.sh(num1));
        System.out.println("ch: "+ComplexFuncs.ch(num1));
        System.out.println("th: "+ComplexFuncs.th(num1));
        System.out.println("cth: "+ComplexFuncs.cth(num1));
        System.out.println("=".repeat(100));

        System.out.println("Task5");
        System.out.println("=".repeat(100));
        SpecialCar car1 = new SpecialCar("ТОЙОТА", 4, engine, Color.BLUE, "А432ЖЖ544SPR");
        car1.showInfo();
        System.out.println("=".repeat(100));

        System.out.println("Task7");
        System.out.println("=".repeat(100));
        MotorDepot motorDepot = new MotorDepot(3);
        SpecialCar car2 = new SpecialCar("ЛАДА", 4, engine, Color.BLUE, "А432ЖЖ544SPR");
        CargoCar car3 = new CargoCar("КАМАЗ", 6, engine, Color.BLUE, "К432ЖЖ544SPR");
        motorDepot.add(car1);
        motorDepot.add(car1);
        motorDepot.add(car2);
        motorDepot.add(car3);
        motorDepot.show();
        motorDepot.goToFlight(car2);
        motorDepot.goToRepair(car1);
        motorDepot.showCarsInDepot();
        motorDepot.showCarsInFlight();
        motorDepot.showCarsIsBroken();
        motorDepot.del(car3);
        motorDepot.showCarsInDepot();
        System.out.println("=".repeat(100));

        System.out.println("Task8");
        System.out.println("=".repeat(100));
        Graph graph = new Graph();
        graph.getCoordinateMesh().addAxis(10,"x");
        graph.getCoordinateMesh().addAxis(15,"y");
        graph.getMesh().setInterval(5);
        graph.addFunc(new Func("sin"),Color.BLUE);
        graph.addFunc(new Func("sin"),Color.RED);
        GraphShower.showFuncs(graph);
        GraphShower.showCoordinateMesh(graph);
        GraphShower.showMesh(graph);
        GraphShower.showExplanation(graph);
        System.out.println("=".repeat(100));
    }
}
