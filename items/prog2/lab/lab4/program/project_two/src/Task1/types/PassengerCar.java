package Task1.types;

import Task1.Car;
import Task4.Engine;

public class PassengerCar extends Car {
    public PassengerCar(String brand, String color, Engine engine, int numOfWheels) {
        super(brand, color, engine, numOfWheels);
    }

    public PassengerCar(String brand, String color, Engine engine, int numOfWheels, String regPlatePattern) {
        super(brand, color, engine, numOfWheels, regPlatePattern);
    }
}
