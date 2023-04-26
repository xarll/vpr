package Task1;

import Task4.Engine;

public class Test {
    public static void main(String[] args) {
        Engine engine = new Engine(
                "1234567890",
                100,
                1.6,
                "gasoline",
                4
        );



        Car car = CarFactory.createCar("bus", "Volvo", "Red", engine, 4);
        System.out.println(car.getBrand());
        System.out.println(car.getColor());
        System.out.println(car.getEngine());
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
