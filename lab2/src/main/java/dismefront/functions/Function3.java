package dismefront.functions;

public class Function3 implements Function {

    @Override
    public String what() {
        return "(cos(x) + 2) / 3";
    }

    @Override
    public double f(double... x) {
        return (Math.cos(x[0]) + 2) / 3;
    }

    @Override
    public int id() {
        return 3;
    }

}