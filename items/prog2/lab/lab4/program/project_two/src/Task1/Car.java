package Task1;

import Task4.Engine;

public abstract class Car {
    private String regPlatePattern;

    protected String regPlate;
    private final String brand;
    protected String color;
    protected Engine engine;
    private final int numOfWheels;

    public Car(String brand, String color, Engine engine, int numOfWheels) {
        this.brand = brand;
        this.color = color;
        this.engine = engine;
        this.numOfWheels = numOfWheels;
    }

    public Car(String brand, String color, Engine engine, int numOfWheels, String regPlatePattern) {
        this.brand = brand;
        this.color = color;
        this.engine = engine;
        this.numOfWheels = numOfWheels;
        this.regPlatePattern = regPlatePattern;
    }


    public String getRegPlate() { return  this.regPlate;}
    public void setRegPlate(String value) {
        if ((this.regPlatePattern != null) && (!value.matches(this.regPlatePattern))) {
            throw new IllegalArgumentException("Invalid registration plate");
        }
        this.regPlate = value;
    }
    public String getBrand() { return  this.brand;}
    public String getColor() { return  this.color;}
    public void setColor(String value) { this.color = value;}
    public Engine getEngine() { return  this.engine;}
    public void setEngine(Engine value) { this.engine = value;}
    public int getNumOfWheels() {return this.numOfWheels;}

    
}
