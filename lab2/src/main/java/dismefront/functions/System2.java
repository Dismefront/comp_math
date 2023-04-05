package dismefront.functions;

import java.util.function.DoubleUnaryOperator;

public class System2 implements EquationSystem {

    private class F1 implements Function {

        @Override
        public String what() {
            return "sin(x - 0.6) - y - 1.6";
        }

        @Override
        public double f(double... x) {
            return Math.sin(x[0] - 0.6) - x[1] - 1.6;
        }

        @Override
        public int id() {
            return 0;
        }

    }

    private class F2 implements Function {

        @Override
        public String what() {
            return "3x - cos(y) - 0.9";
        }

        @Override
        public double f(double... x) {
            return 3 * x[0] - Math.cos(x[1]) - 0.9;
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

    public System2() {
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
        return 2;
    }

    @Override
    public int equationsCount() {
        return 2;
    }

}
