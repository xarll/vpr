package Task4;

public class Engine {
    private final String serialNumber;
    private final double power;
    private final double displacement;
    private final String fuelType;
    private final int numberOfCylinders;

    public Engine(String serialNumber, double power, double displacement, String fuelType, int numberOfCylinders) {
        this.serialNumber = serialNumber;
        this.power = power;
        this.displacement = displacement;
        this.fuelType = fuelType;
        this.numberOfCylinders = numberOfCylinders;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getPower() {
        return power;
    }

    public double getDisplacement() {
        return displacement;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }

    public String toString() {
        return String.format(
                "Engine: %s, %s, %s, %s, %s",
                this.serialNumber,
                this.power,
                this.displacement,
                this.fuelType,
                this.numberOfCylinders
        );
    }
}

