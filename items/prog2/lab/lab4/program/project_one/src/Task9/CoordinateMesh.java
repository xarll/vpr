package Task9;

import java.util.ArrayList;

public class CoordinateMesh {
    private ArrayList<Axis> axes = new ArrayList<>();
    public enum Positions{середина, лево, право};
    private Positions position = Positions.середина;

    public Positions getPosition() {
        return position;
    }
    public void setPosition(Positions position){
        this.position = position;
    }
    public void setAxisInterval(double interval, int numOfAxis){
        axes.get(numOfAxis).interval = interval;
    }
    public double getAxisInterval(int numOfAxis){
        return axes.get(numOfAxis).interval;
    }
    public void setAxesDesignation(String designation, int numOfAxis){
        axes.get(numOfAxis).designation = designation;
    }
    public String getAxisDesignation(int numOfAxis){
        return axes.get(numOfAxis).designation;
    }
    public void addAxis(int interval, String designation){
        axes.add(new Axis(interval, designation));
    }
    public void delAxis(){
        axes.remove(axes.size()-1);
    }
    public int getAxesNum(){
        return axes.size();
    }
}
