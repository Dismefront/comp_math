package dismefront.logic;

public class Polynomial3DEquation implements Equation<Double, Double> {

    private double a, b, c, d;

    public Polynomial3DEquation(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public String what() {
        return "%.2fx^3 + %.2fx^2 + %.2fx + %.2f".formatted(a, b, c, d);
    }

    @Override
    public Double apply(Double x) {
        return a * x * x * x + b * x * x + c * x + d;
    }

}
