package dismefront.logic;

public class LogarithmicEquation implements Equation<Double, Double> {

    private double a, b;

    public LogarithmicEquation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String what() {
        return "%fln(x) + %f".formatted(a, b);
    }

    @Override
    public Double apply(Double x) {
        return a * Math.log(x) + b;
    }

}
