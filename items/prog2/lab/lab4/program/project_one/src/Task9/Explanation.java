package Task9;

import java.awt.*;
import java.util.ArrayList;

public class Explanation {
    private ArrayList<Color> graphColors = new ArrayList<>();
    private ArrayList<String> funcNames = new ArrayList<>();
    public void addPar(Color color, String name){
        graphColors.add(color);
        funcNames.add(name);
    }
    public Color getColor(int index){
        return graphColors.get(index);
    }
    public String getName(int index){
        return funcNames.get(index);
    }
    public void delPar(int index){
        graphColors.remove(index);
        funcNames.remove(index);
    }
}
