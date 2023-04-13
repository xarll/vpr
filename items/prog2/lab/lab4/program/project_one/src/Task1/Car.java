package Task1;

import Task4.Engine;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Car {
    // Создание объекта Pattern для проверки номера автомобиля на соответствие шаблону
    private Pattern patternOfSign = Pattern.compile("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}RUS");
    public enum Model{легковой, грузовой, автобус, специальный};
    private final String BRAND;
    private final Model MODEL;
    private Color color;
    private final int NUM_OF_WHEELS;
    private Engine engine;
    private String registerSign = null;

    public final String getBRAND() {
        return BRAND;
    }

    public final Model getMODEL() {
        return MODEL;
    }

    public final Color getColor() {
        return color;
    }

    public final int getNUM_OF_WHEELS() {
        return NUM_OF_WHEELS;
    }

    public final Engine getEngine() {
        return engine;
    }

    public final String getRegisterSign() {
        return registerSign;
    }
    // Конструктор класса Car без параметра registerSign
    public Car(Model MODEL, String BRAND, int NUM_OF_WHEELS, Engine engine, Color color){
        this.BRAND = BRAND;
        this.MODEL = MODEL;
        this.NUM_OF_WHEELS = NUM_OF_WHEELS;
        this.engine = engine;
        this.color = color;
    }
    // Конструктор класса Car с параметром registerSign
    public Car(Model MODEL, String BRAND, int NUM_OF_WHEELS, Engine engine,Color color, String registerSign){
        this.BRAND = BRAND;
        this.MODEL = MODEL;
        this.NUM_OF_WHEELS = NUM_OF_WHEELS;
        this.engine = engine;
        this.color = color;
        setRegisterSign(registerSign);
    }
    // Метод для установки регистрационного знака, который принимает в качестве параметра строку
    public final boolean setRegisterSign(String registerSign){
        return setRegisterSign(registerSign, patternOfSign);
    }
    protected final boolean setRegisterSign(String registerSign, Pattern pattern){
        Matcher matcher = pattern.matcher(registerSign);
        // проверяем, соответствует ли переданный номер регистрации заданному шаблону
        if(matcher.matches()){
            this.registerSign = registerSign;
            return true;
        }
        else {
            return false;
        }
    }

    public final void setColor(Color color){
        this.color = color;
    }
    public final void setEngine(Engine engine){
        this.engine = engine;
    }

    public void showInfo(){
        System.out.println("Марка "+this.BRAND);
        System.out.println("Модель "+this.MODEL);
        System.out.println("Колеса "+this.NUM_OF_WHEELS);
        System.out.println("Цвет "+this.color);
        System.out.println("Двигатель "+this.engine);
        System.out.println("Номер "+this.registerSign);
    }
}
