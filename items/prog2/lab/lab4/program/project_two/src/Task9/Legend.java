package Task9;

import java.util.ArrayList;

public class Legend {
    private ArrayList<String> labels;

    public Legend() {
        this.labels = new ArrayList<String>();
    }

    public void addLabel(String label) {
        this.labels.add(label);
    }

    public void draw() {
        System.out.println("Рисую легенду: " + String.join(", ", this.labels) + "...");
    }
}
