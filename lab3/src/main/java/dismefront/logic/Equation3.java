package dismefront.logic;

public class Equation3 implements Equation<Double, Double> {

    public Double apply(Double x) {
        return 3 * Math.atan(5 * x) + 1;
    }

    public String what() {
        return "3 * atan(x) + 1";
    }

}