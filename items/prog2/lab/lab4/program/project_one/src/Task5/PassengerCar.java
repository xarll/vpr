package Task5;

import Task1.Car;
import Task4.Engine;

import java.awt.*;

public class PassengerCar extends Car {
    public PassengerCar(String BRAND, Engine engine, Color color) {
        super(Model.легковой, BRAND, 4, engine, color);
    }
    public PassengerCar(String BRAND, Engine engine, Color color, String registerSign) {
        super(Model.легковой, BRAND, 4, engine, color, registerSign);
    }

}
