package dismefront.logic;

public class ExponentialEquation implements Equation<Double, Double> {

    private double a, b;

    public ExponentialEquation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String what() {
        return "%fe^(%fx)".formatted(a, b);
    }

    @Override
    public Double apply(Double x) {
        return a * Math.exp(b * x);
    }

}
