package Task1;


import Task1.types.Bus;
import Task1.types.FreightCar;
import Task1.types.PassengerCar;
import Task4.Engine;

public class CarFactory {
    public static Car createCar(String type, String brand, String color, Engine engine, int numOfWheels) {
        return switch (type) {
            case "passenger" -> new PassengerCar(
                    brand,
                    color,
                    engine,
                    numOfWheels,
                    "[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}RUS"
            );
            case "freight" -> new FreightCar(
                    brand,
                    color,
                    engine,
                    numOfWheels,
                    "[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}RUS"
            );
            case "bus" -> new Bus(
                    brand,
                    color,
                    engine,
                    numOfWheels,
                    "[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}RUS"
            );
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
