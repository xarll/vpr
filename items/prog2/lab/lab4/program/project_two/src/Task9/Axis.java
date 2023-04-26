package Task9;

public class Axis {
    private double minValue;
    private double maxValue;
    private String label;

    public Axis() {
        this.minValue = 0.0;
        this.maxValue = 1.0;
        this.label = "Axis";
    }

    public void setRange(double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void draw() {
        System.out.println("Рисую " + this.label + " оси из " + this.minValue + " в " + this.maxValue + "...");
    }
}
