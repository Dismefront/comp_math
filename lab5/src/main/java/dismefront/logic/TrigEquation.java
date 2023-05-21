package dismefront.logic;

public class TrigEquation implements Equation<Double, Double> {

    private double a, b;

    public TrigEquation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String what() {
        return "%f*sin(%f)".formatted(a, b);
    }

    @Override
    public Double apply(Double x) {
        return a * Math.sin(x * b);
    }

}