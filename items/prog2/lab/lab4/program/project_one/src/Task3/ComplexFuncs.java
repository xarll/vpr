package Task3;

import Task2.Complex;

public abstract class ComplexFuncs {

    public abstract Complex func();

    public static Complex powE(Complex num){
        Complex buf1= new Complex();
        buf1.setRealPart(Math.pow(Math.E, num.getRealPart())*Math.cos(num.getImaginaryPart()));
        buf1.setImaginaryPart(Math.pow(Math.E, num.getRealPart())*Math.sin(num.getImaginaryPart()));
        return buf1;
    }

    public static Complex sin(Complex num){
        Complex buf1 = Complex.mul(new Complex(num.getRealPart(), num.getImaginaryPart()),new Complex(0,1) );
        buf1 = ComplexFuncs.powE(buf1);
        Complex buf2 = Complex.mul(new Complex(num.getRealPart(), num.getImaginaryPart()),new Complex(0,-1) );
        buf2 = ComplexFuncs.powE(buf2);
        buf1 = Complex.sub(buf1, buf2);
        return Complex.div(buf1, new Complex(0, 2));
    }
    public static Complex cos(Complex num){
        Complex buf1 = Complex.mul(new Complex(num.getRealPart(), num.getImaginaryPart()),new Complex(0,1) );
        buf1 = ComplexFuncs.powE(buf1);
        Complex buf2 = Complex.mul(new Complex(num.getRealPart(), num.getImaginaryPart()),new Complex(0,-1) );
        buf2 = ComplexFuncs.powE(buf2);
        buf1 = Complex.add(buf1, buf2);
        return Complex.div(buf1, 2);
    }
    public static Complex tan(Complex num){
        Complex buf1 = ComplexFuncs.sin(num);
        Complex buf2 = ComplexFuncs.cos(num);
        buf1 = Complex.div(buf1, buf2);
        return buf1;
    }
    public static Complex atan(Complex num){
        Complex buf1 = ComplexFuncs.sin(num);
        Complex buf2 = ComplexFuncs.cos(num);
        buf1 = Complex.div(buf2, buf1);
        return buf1;
    }
    public static Complex sh(Complex num){
        Complex buf1 = ComplexFuncs.powE(num);
        Complex buf2 = Complex.mul(new Complex(num.getRealPart(), num.getImaginaryPart()),new Complex(-1,0) );
        buf1 = Complex.sub(buf1, buf2);
        buf1 = Complex.div(buf1, 2);
        return buf1;
    }
    public static Complex ch(Complex num){
        Complex buf1 = ComplexFuncs.powE(num);
        Complex buf2 = Complex.mul(new Complex(num.getRealPart(), num.getImaginaryPart()),new Complex(-1,0) );
        buf1 = Complex.add(buf1, buf2);
        buf1 = Complex.div(buf1, 2);
        return buf1;
    }
    public static Complex th(Complex num){
        return Complex.div(ComplexFuncs.sh(num), ComplexFuncs.ch(num));
    }
    public static Complex cth(Complex num){
        return Complex.div(ComplexFuncs.ch(num),ComplexFuncs.sh(num));
    }
}
