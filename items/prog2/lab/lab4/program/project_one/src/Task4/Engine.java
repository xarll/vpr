package Task4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Engine {
    // перечисление типов бензина
    public enum FuelType{дизель, бензин, газ};
    private double power, fuelConsumption, workload;

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getWorkload() {
        return workload;
    }

    public void setWorkload(double workload) {
        this.workload = workload;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public String getRegisterSign() {
        return registerSign;
    }

    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }

    public void setNumberOfCylinders(int numberOfCylinders) {
        this.numberOfCylinders = numberOfCylinders;
    }

    private FuelType fuelType;
    private String registerSign;
    private int numberOfCylinders;
    // Конструктор класса Engine, который принимает в качестве параметров данные о количестве цилиндров, мощности, расходе топлива, объеме работы, типе топлива и регистрационном знаке
    public Engine(int numberOfCylinders, double power, double fuelConsumption, double workload, FuelType fuelType, String registerSign){
        this.numberOfCylinders = numberOfCylinders;
        this.power = power;
        this.fuelConsumption = fuelConsumption;
        this.workload = workload;
        this.fuelType = fuelType;
        setRegisterSign(registerSign);
    }

    // Метод для установки регистрационного знака, который принимает в качестве параметра строку
    public void setRegisterSign(String registerSign){
        Pattern pattern = Pattern.compile("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}RUS");
        Matcher matcher = pattern.matcher(registerSign);
        if(matcher.matches()){
            this.registerSign = registerSign;
        }
        else {
            System.out.println("Ошибка! Неверные данные!");
        }
    }
    // Переопределенный метод toString для получения строкового представления объекта
    @Override
    public String toString(){
        String s =String.format("Номер: %s  Тип топлива: %s Кол-во цилиндров: %d Мощность: %f Расход топлива: %f Объем работы: %f", registerSign, fuelType, numberOfCylinders, power, fuelConsumption, workload);
        return s;
    }
}
