package Task5;

import Task1.Car;
import Task4.Engine;

import java.awt.*;

public class CargoCar extends Car {
    public CargoCar(String BRAND, int NUM_OF_WHEELS, Engine engine, Color color) {
        super(Model.грузовой, BRAND, NUM_OF_WHEELS, engine, color);
    }

    public CargoCar(String BRAND, int NUM_OF_WHEELS, Engine engine, Color color, String registerSign) {
        super(Model.грузовой, BRAND, NUM_OF_WHEELS, engine, color, registerSign);
    }
}
