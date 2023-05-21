package dismefront.logic;

public class Polynomial2DEquation implements Equation<Double, Double> {

    private double a, b, c;

    public Polynomial2DEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String what() {
        return "%.2fx^2 + %.2fx + %.2f".formatted(a, b, c);
    }

    @Override
    public Double apply(Double x) {
        return a * x * x + b * x + c;
    }

}
