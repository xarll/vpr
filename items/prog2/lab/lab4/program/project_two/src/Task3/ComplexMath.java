package Task3;

import Task2.Complex;

public class ComplexMath {

    public static Complex sin(Complex z) {
        double real = Math.sin(z.real()) * Math.cosh(z.imag());
        double imag = Math.cos(z.real()) * Math.sinh(z.imag());
        return new Complex(real, imag);
    }

    public static Complex cos(Complex z) {
        double real = Math.cos(z.real()) * Math.cosh(z.imag());
        double imag = -Math.sin(z.real()) * Math.sinh(z.imag());
        return new Complex(real, imag);
    }

    public static Complex tan(Complex z) {
        Complex numerator = sin(z);
        Complex denominator = cos(z);
        return numerator.divide(denominator);
    }

    public static Complex arctan(Complex z) {
        Complex numerator = new Complex(1, 0).subtract(new Complex(0, 1).multiply(z));
        Complex denominator = new Complex(1, 0).add(new Complex(0, 1).multiply(z));
        Complex quotient = numerator.divide(denominator);
        return new Complex(0, -0.5).multiply(
                log(new Complex(1, 0).subtract(quotient)).subtract(
                        log(new Complex(1, 0).add(quotient))
                )
        );
    }

    public static Complex sinh(Complex z) {
        double real = Math.sinh(z.real()) * Math.cos(z.imag());
        double imag = Math.cosh(z.real()) * Math.sin(z.imag());
        return new Complex(real, imag);
    }

    public static Complex cosh(Complex z) {
        double real = Math.cosh(z.real()) * Math.cos(z.imag());
        double imag = Math.sinh(z.real()) * Math.sin(z.imag());
        return new Complex(real, imag);
    }

    public static Complex tanh(Complex z) {
        Complex numerator = sinh(z);
        Complex denominator = cosh(z);
        return numerator.divide(denominator);
    }

    public static Complex coth(Complex z) {
        Complex numerator = new Complex(1, 0).add(new Complex(0, 1).multiply(z));
        Complex denominator = new Complex(1, 0).subtract(new Complex(0, 1).multiply(z));
        return numerator.divide(denominator);
    }

    public static Complex exp(Complex z) {
        double real = Math.exp(z.real()) * Math.cos(z.imag());
        double imag = Math.exp(z.real()) * Math.sin(z.imag());
        return new Complex(real, imag);
    }


    public static double abs(Complex z) {
        return Math.sqrt(z.real() * z.real() + z.imag() * z.imag());
    }


    public static Complex log(Complex z) {
        double real = Math.log(abs(z));
        double imag = z.phase();
        return new Complex(real, imag);
    }
}
