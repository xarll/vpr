package Task9;

public class Test {
    public static void main(String[] args) {

        Point p1 = new Point(0.0, 0.0);
        Point p2 = new Point(1.0, 1.0);
        Point p3 = new Point(2.0, 4.0);
        Point p4 = new Point(3.0, 9.0);
        Point p5 = new Point(4.0, 16.0);

        Curve curve = new Curve("y = x^2");
        curve.addPoint(p1);
        curve.addPoint(p2);
        curve.addPoint(p3);
        curve.addPoint(p4);
        curve.addPoint(p5);


        Axis xAxis = new Axis();
        xAxis.setRange(0.0, 4.0);
        xAxis.setLabel("x");

        Axis yAxis = new Axis();
        yAxis.setRange(0.0, 16.0);
        yAxis.setLabel("y");


        Grid grid = new Grid();
        grid.setSpacing(50, 50);


        Legend legend = new Legend();
        legend.addLabel("y = x^2");


        Graph graph = new Graph();
        graph.addCurve(curve);
        graph.xAxis = xAxis;
        graph.yAxis = yAxis;
        graph.grid = grid;
        graph.legend = legend;

        graph.draw();
    }
}
