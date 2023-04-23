package dismefront.logic;

public class Equation2 implements Equation<Double, Double> {

    public Double apply(Double x) {
        return 2 * x * x - 10 * x - 3;
    }

    public String what() {
        return "2x^2 - 10x - 3";
    }

}
