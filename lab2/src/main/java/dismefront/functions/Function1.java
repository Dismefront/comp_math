package dismefront.functions;

public class Function1 implements Function {

    @Override
    public String what() {
        return "x^3 + 2.64x^2 - 5.41x - 11.76";
    }

    @Override
    public double f(double... x) {
        return x[0] * x[0] * x[0] + 2.64 * x[0] * x[0] - 5.41 * x[0] - 11.76;
    }

    @Override
    public int id() {
        return 1;
    }

}
