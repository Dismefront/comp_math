package dismefront.functions;

import java.util.function.DoubleUnaryOperator;

public class System1 implements EquationSystem {

    private class F1 implements Function {

        @Override
        public String what() {
            return "3 * x^2 * y^2 + x^2 - 3 * x * y - 7 = 0";
        }

        @Override
        public double f(double... x) {
            return 3 * x[0] * x[0] * x[1] * x[1] + x[0] * x[0] - 3 * x[0] * x[1] - 7;
        }

        @Override
        public int id() {
            return 0;
        }

    }

    private class F2 implements Function {

        @Override
        public String what() {
            return "10 * x^2 * y^2 + 3 * x^2 - 20 * x * y - 3 = 0";
        }

        @Override
        public double f(double... x) {
            return 10 * x[0] * x[1] * x[0] * x[1] + 3 * x[0] * x[0] - 20 * x[0] * x[1] - 3;
        }

        @Override
        public int id() {
            return 0;
        }

    }

    @Override
    public String what() {
        return functions[0].what() +
                "\n" +
                functions[1].what();
    }

    private Function[] functions;

    public System1() {
        functions = new Function[2];
        functions[0] = new F1();
        functions[1] = new F2();
    }


    @Override
    public Function[] getFunctions() {
        return functions;
    }

    @Override
    public double[] applyMatrix(double... x) {
        double[] res = new double[2];
        res[0] = functions[0].f(x);
        res[1] = functions[1].f(x);
        return res;
    }

    @Override
    public int id() {
        return 1;
    }

    @Override
    public int equationsCount() {
        return 2;
    }

}
