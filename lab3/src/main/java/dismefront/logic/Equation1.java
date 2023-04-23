package dismefront.logic;

public class Equation1 implements Equation<Double, Double> {

    public Double apply(Double x) {
        return x * x * x - 5 * x * x + 3 * x - 16;
    }

    public String what() {
        return "x^3 - 5x^2 + 3x - 16";
    }

}
