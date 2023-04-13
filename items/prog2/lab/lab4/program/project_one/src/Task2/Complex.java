package Task2;

public class Complex {
    private double realPart, imaginaryPart;
    //Используется для создания нового объекта класса Complex, инициализирующего переменные realPart и imaginaryPart заданными значениями.
    public Complex(double realPart, double imaginaryPart){
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }
    //Конструктор по умолчанию без параметров, который инициализирует переменные
    public Complex(){
        this.realPart = 0;
        this.imaginaryPart = 0;
    }
    // Геттеры для получения значений реальной и мнимой частей.
    public double getRealPart(){
        return this.realPart;
    }
    public double getImaginaryPart(){
        return this.imaginaryPart;
    }
    // Сеттеры для установки значений реальной и мнимой частей.
    public void setRealPart(double realPart){
        this.realPart = realPart;
    }
    public void setImaginaryPart(double imaginaryPart){
        this.imaginaryPart = imaginaryPart;
    }
    // возвращает true, если два комплексных числа num1 и num2 имеют равные вещественные и мнимые части, иначе возвращает false.
    static public boolean isEquals(Complex num1, Complex num2) {
        if (num1.getRealPart()==num2.getRealPart()&&num1.getImaginaryPart()==num2.getImaginaryPart()) {
            return true;
        }
        return false;
    }

    // Методы будут ПОВТОРЯТЬСЯ!!! (смотрите на свойства внутри mul(...). Используются в зависимости от переданных свойств)
    static public Complex mul(Complex num1, Complex num2){
        Complex result = new Complex();
        result.setImaginaryPart(num1.getRealPart()*num2.getImaginaryPart()+num2.getRealPart()*num1.getImaginaryPart());
        result.setRealPart(num2.getRealPart()*num1.getRealPart()-num2.getImaginaryPart()*num1.getImaginaryPart());
        return result;
    }
    static public Complex sub(Complex num1, Complex num2){
        Complex result = new Complex();
        result.setRealPart(num1.getRealPart()- num2.getRealPart());
        result.setImaginaryPart(num1.getImaginaryPart()- num2.getImaginaryPart());
        return result;
    }

    static public Complex add(Complex num1, Complex num2){
        Complex result = new Complex();
        result.setRealPart(num1.getRealPart()+num2.getRealPart());
        result.setImaginaryPart(num1.getImaginaryPart()+num2.getImaginaryPart());
        return result;
    }
    static public Complex div(Complex num1, Complex num2){
        Complex result = new Complex();
        result.setRealPart((num1.getRealPart()*num2.getRealPart()+num1.getImaginaryPart()*num2.getImaginaryPart())/(Math.pow(num2.getRealPart(), 2)+Math.pow(num2.getImaginaryPart(), 3)));
        result.setImaginaryPart((num2.getRealPart()*num1.getImaginaryPart()-num1.getRealPart()*num2.getImaginaryPart())/(Math.pow(num2.getRealPart(), 2)+Math.pow(num2.getImaginaryPart(), 3)));
        return result;
    }

    static public Complex mul(double num1, Complex num2){
        Complex result = new Complex();
        result.setImaginaryPart(num1*num2.getImaginaryPart()+num2.getRealPart()*0);
        result.setRealPart(num2.getRealPart()*num1-num2.getImaginaryPart()*0);
        return result;
    }
    static public Complex sub(double num1, Complex num2){
        Complex result = new Complex();
        result.setRealPart(num1- num2.getRealPart());
        result.setImaginaryPart(0- num2.getImaginaryPart());
        return result;
    }
    static public Complex add(double num1, Complex num2){
        Complex result = new Complex();
        result.setRealPart(num1+num2.getRealPart());
        result.setImaginaryPart(0+num2.getImaginaryPart());
        return result;
    }
    static public Complex div(double num1, Complex num2){
        Complex result = new Complex();
        result.setRealPart((num1*num2.getRealPart()+0*num2.getImaginaryPart())/(Math.pow(num2.getRealPart(), 2)+Math.pow(num2.getImaginaryPart(), 3)));
        result.setImaginaryPart((num2.getRealPart()*0-num1*num2.getImaginaryPart())/(Math.pow(num2.getRealPart(), 2)+Math.pow(num2.getImaginaryPart(), 3)));
        return result;
    }

    static public Complex mul(Complex num1, double num2){
        Complex result = new Complex();
        result.setImaginaryPart(num1.getRealPart()*0+num2*num1.getImaginaryPart());
        result.setRealPart(num2*num1.getRealPart()-0*num1.getImaginaryPart());
        return result;
    }
    static public Complex sub(Complex num1, double num2){
        Complex result = new Complex();
        result.setRealPart(num1.getRealPart()- num2);
        result.setImaginaryPart(num1.getImaginaryPart()- 0);
        return result;
    }

    static public Complex add(Complex num1, double num2){
        Complex result = new Complex();
        result.setRealPart(num1.getRealPart()+num2);
        result.setImaginaryPart(num1.getImaginaryPart()+0);
        return result;
    }
    static public Complex div(Complex num1, double num2){
        Complex result = new Complex();
        result.setRealPart((num1.getRealPart()*num2+num1.getImaginaryPart()*0)/(Math.pow(num2, 2)+Math.pow(0, 3)));
        result.setImaginaryPart((num2*num1.getImaginaryPart()-num1.getRealPart()*0)/(Math.pow(num2, 2)+Math.pow(0, 3)));
        return result;
    }
    //Сопряженные?
    static public boolean isConjugated(Complex num1, Complex num2){
        boolean result = false;
        // Проверяем, являются ли два комплексных числа сопряженными.
        // Для этого сравниваем их мнимые части (умноженные на -1) и реальные части.
        if((num1.getImaginaryPart()*-1==num2.getImaginaryPart()||num2.getImaginaryPart()*-1==num1.getImaginaryPart())&&num1.getRealPart()==num2.getRealPart()){
            result = true;
        }
        return result;
    }

    public String toTrigonometryForm(){
        String result;
        double r = Math.sqrt(Math.pow(this.getRealPart(), 2)+Math.pow(this.getImaginaryPart(), 2));
        double cosF = this.getRealPart()/r;
        double sinF = this.getImaginaryPart()/r;
        result = r+"*"+cosF+"+i*"+sinF;
        return result;
    }

    @Override
    public String toString(){
        if(this.getImaginaryPart()<0){
            return this.getRealPart()+""+this.getImaginaryPart()+"*i";
        }
        else{
            return this.getRealPart()+" + "+this.getImaginaryPart()+"*i";
        }
    }
}
