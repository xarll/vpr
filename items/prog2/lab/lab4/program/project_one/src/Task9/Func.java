package Task9;

public class Func {
    private String funcView;
    public Func(String funcView){
        this.funcView = funcView;
    }
    public String getFuncView(){
        return funcView;
    }
    public double calculationFunc(int arg){
        return Math.cos(arg);
    }
}
