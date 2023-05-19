package dismefront.logic;

public class LinearEquation implements Equation<Double, Double> {

    private double a, b;

    public LinearEquation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String what() {
        return "%fx + %f".formatted(a, b);
    }

    @Override
    public Double apply(Double x) {
        return a * x + b;
    }

}
