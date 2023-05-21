package dismefront.logic;

public class PowerEquation implements Equation<Double, Double> {

    private double a, b;

    public PowerEquation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String what() {
        return "%.2fx^%.2f".formatted(a, b);
    }

    @Override
    public Double apply(Double x) {
        return a * Math.pow(x, b);
    }

}
