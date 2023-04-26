package Task9;

public class Grid {
    private int xSpacing;
    private int ySpacing;

    public Grid() {
        this.xSpacing = 50;
        this.ySpacing = 50;
    }

    public void setSpacing(int xSpacing, int ySpacing) {
        this.xSpacing = xSpacing;
        this.ySpacing = ySpacing;
    }

    public void draw() {
        System.out.println("Рисую сетку с промежутками " + this.xSpacing + "x" + this.ySpacing + "...");
    }
}