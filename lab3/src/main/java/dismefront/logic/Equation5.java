package dismefront.logic;

public class Equation5 implements Equation<Double, Double> {

    public Double apply(Double x) {
        return 3 * Math.pow(x, 5) + Math.pow(x, 4) - 0.4 * Math.pow(x, 3) + 2.8 * x * x + 10.4 * x - 8;
    }

    public String what() {
        return "3x^5 + x^4 - 0.4x^3 + 2.8x^2 + 10.4x - 8";
    }

}
