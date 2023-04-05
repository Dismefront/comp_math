package dismefront.functions;

public class Function2 implements Function {

    @Override
    public String what() {
        return "x^3 - 18x - 83";
    }

    @Override
    public double f(double... x) {
            return Math.pow(x[0], 3) - 18 * x[0] - 83;
    }

    @Override
    public int id() {
        return 2;
    }

}
