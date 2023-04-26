package Task2;

/**
 * @param real действительная часть
 * @param imag мнимая часть
 */
public record Complex(double real, double imag) {

    public Complex add(Complex other) {
        double r = real + other.real;
        double i = imag + other.imag;
        return new Complex(r, i);
    }

    public Complex subtract(Complex other) {
        double r = real - other.real;
        double i = imag - other.imag;
        return new Complex(r, i);
    }

    public Complex multiply(Complex other) {
        double r = real * other.real - imag * other.imag;
        double i = real * other.imag + imag * other.real;
        return new Complex(r, i);
    }

    public Complex divide(Complex other) {
        double denom = other.real * other.real + other.imag * other.imag;
        double r = (real * other.real + imag * other.imag) / denom;
        double i = (imag * other.real - real * other.imag) / denom;
        return new Complex(r, i);
    }

    public Complex conjugate() {
        return new Complex(real, -imag);
    }

    public boolean equals(Complex other) {
        return real == other.real && imag == other.imag;
    }

    public String toString() {
        if (imag == 0) {
            return String.format("%.2f", real);
        } else if (imag > 0) {
            return String.format("%.2f + %.2fi", real, imag);
        } else {
            return String.format("%.2f - %.2fi", real, -imag);
        }
    }

    public String toTrigString() {
        double r = Math.sqrt(real * real + imag * imag);
        double theta = Math.atan2(imag, real);
        return String.format("%.2f(cos(%.2f) + i sin(%.2f))", r, theta, theta);
    }

    /**
     * @return аргумент комплексного числа (phi)
     */
    public double phase() {
        return Math.atan2(imag, real);
    }

    public Complex fromPolar(double r, double theta) {
        double x = r * Math.cos(theta);
        double y = r * Math.sin(theta);
        return new Complex(x, y);
    }
}