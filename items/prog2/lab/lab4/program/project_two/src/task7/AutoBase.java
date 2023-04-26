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
