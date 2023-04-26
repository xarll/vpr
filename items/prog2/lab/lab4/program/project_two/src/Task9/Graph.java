package Task9;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private ArrayList<Curve> curves;
    Axis xAxis;
    Axis yAxis;
    Grid grid;
    Legend legend;

    public Graph() {
        this.curves = new ArrayList<Curve>();
        this.xAxis = new Axis();
        this.yAxis = new Axis();
        this.grid = new Grid();
        this.legend = new Legend();
    }

    public void addCurve(Curve curve) {
        this.curves.add(curve);
    }

    public void draw() {
        System.out.println("Рисую график...");
        for (Curve curve : this.curves) {
            curve.draw();
        }
        this.xAxis.draw();
        this.yAxis.draw();
        this.grid.draw();
        this.legend.draw();
        System.out.println("Нарисовал");
    }
}

