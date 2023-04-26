package Task1.types;

import Task1.Car;
import Task4.Engine;

public final class Bus extends Car {
    public Bus(String brand, String color, Engine engine, int numOfWheels) {
        super(brand, color, engine, numOfWheels);
    }

    public Bus(String brand, String color, Engine engine, int numOfWheels, String regPlatePattern) {
        super(brand, color, engine, numOfWheels, regPlatePattern);
    }
}
