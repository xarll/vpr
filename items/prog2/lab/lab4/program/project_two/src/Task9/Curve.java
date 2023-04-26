package Task9;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Curve {
    private ArrayList<Point> points;
    private String name;

    public Curve(String name) {
        this.points = new ArrayList<Point>();
        this.name = name;
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public void draw() {
        System.out.println("Рисую кривые " + this.name + "...");
        for (Point point : this.points) {
            point.draw();
        }
        System.out.println("Нарисовал");
    }
}
