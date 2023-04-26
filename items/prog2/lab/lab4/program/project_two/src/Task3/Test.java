package Task3;

import Task2.Complex;

public class Test {
    public static void main(String[] args) {
        Complex z1 = new Complex(3, 4);
        Complex z2 = new Complex(2, 5);

        System.out.println("z1 = " + z1);
        System.out.println("z2 = " + z2);


        System.out.println("\nz1 + z2 = " + z1.add(z2));
        System.out.println("z1 - z2 = " + z1.subtract(z2));
        System.out.println("z1 * z2 = " + z1.multiply(z2));
        System.out.println("z1 / z2 = " + z1.divide(z2));

        System.out.println("\nconjugate(z1) = " + z1.conjugate());
        System.out.println("abs(z1) = " + ComplexMath.abs(z1));
        System.out.println("phase(z1) = " + z1.phase());
        System.out.println("exp(z1) = " + ComplexMath.exp(z1));
        System.out.println("sin(z1) = " + ComplexMath.sin(z1));
        System.out.println("cos(z1) = " + ComplexMath.cos(z1));
        System.out.println("tan(z1) = " + ComplexMath.tan(z1));
        System.out.println("arctan(z1) = " + ComplexMath.arctan(z1));
        System.out.println("sinh(z1) = " + ComplexMath.sinh(z1));
        System.out.println("cosh(z1) = " + ComplexMath.cosh(z1));
        System.out.println("tanh(z1) = " + ComplexMath.tanh(z1));
        System.out.println("coth(z1) = " + ComplexMath.coth(z1));
        System.out.println("exp(z1) = " + ComplexMath.exp(z1));

        System.out.println("\nAsTrigString(z1) = " + z1.toTrigString());
        System.out.println("AsString(z1) = " + z1.toString());
    }
}
