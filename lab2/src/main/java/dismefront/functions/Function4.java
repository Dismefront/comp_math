package dismefront.functions;

public class Function4 implements Function {

    @Override
    public String what() {
        return "x^3 - 3x - 3";
    }

    @Override
    public double f(double... x) {
        return Math.pow(x[0], 3) - 3 * x[0] - 3;
    }

    @Override
    public int id() {
        return 4;
    }

}