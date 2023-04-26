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
