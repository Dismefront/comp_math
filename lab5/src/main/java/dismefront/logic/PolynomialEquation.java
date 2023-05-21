package dismefront.logic;

public class PolynomialEquation implements Equation<Double, Double> {

    private double[] coefficients;

    public PolynomialEquation(double[] coefficients) {
        this.coefficients = coefficients;
    }

    @Override
    public String what() {
        String[] xs = new String[coefficients.length];
        for (int i = 0; i < xs.length; i++) {
            xs[i] = "%fx^%d".formatted(coefficients[i], xs.length - i - 1);
        }
        return String.join("+", xs);
    }

    @Override
    public Double apply(Double aDouble) {
        return null;
    }

}
