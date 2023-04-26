package Task9;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        System.out.println("Рисую точки: (" + this.x + ", " + this.y + ")...");
    }
}