package Task9;

import java.awt.*;
import java.util.ArrayList;

public class Graph {
    private ArrayList<Func> funcs = new ArrayList<>();
    private Explanation explanation = new Explanation();
    private CoordinateMesh coordinateMesh = new CoordinateMesh();
    private Mesh mesh = new Mesh();

    public void addFunc(Func func, Color color){
        funcs.add(func);
        explanation.addPar(color,func.getFuncView());
    }
    public void delFunc(Func func){
        if(funcs.contains(func)){
            explanation.delPar(funcs.indexOf(func));
            funcs.remove(func);
        }
    }
    public CoordinateMesh getCoordinateMesh(){
        return coordinateMesh;
    }
    public Explanation getExplanation(){
        return explanation;
    }
    public Mesh getMesh(){
        return mesh;
    }
    public ArrayList<Func> getFuncs(){
        return funcs;
    }
}
