package Task5;

import Task1.Car;
import Task4.Engine;

import java.awt.*;

public final class Bus extends Car {
    public Bus(String BRAND, int NUM_OF_WHEELS, Engine engine, Color color) {
        super(Model.автобус, BRAND, NUM_OF_WHEELS, engine, color);
    }

    public Bus(String BRAND, int NUM_OF_WHEELS, Engine engine, Color color, String registerSign) {
        super(Model.автобус, BRAND, NUM_OF_WHEELS, engine, color, registerSign);
    }
}
