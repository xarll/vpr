package Task5;

import Task1.Car;
import Task4.Engine;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCar extends Car {

    private Pattern patternOfSign  = Pattern.compile("[ÀÂÅÊÌÍÎĞÑÒÓÕ]\\d{3}[ÁÃÅÆÈËÍÏÑÓÕ×]{2}\\d{2,3}SPR");

    public SpecialCar(String BRAND, int NUM_OF_WHEELS, Engine engine, Color color) {
        super(Model.ñïåöèàëüíûé, BRAND, NUM_OF_WHEELS, engine, color);
    }

    public SpecialCar(String BRAND, int NUM_OF_WHEELS, Engine engine, Color color, String registerSign) {
        super(Model.ñïåöèàëüíûé, BRAND, NUM_OF_WHEELS, engine, color);
        setRegisterSign(registerSign, patternOfSign);
    }

}
