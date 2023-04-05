package dismefront.methods;

import dismefront.functions.Function;

public class Secant {

    public double findRoot(Function f, double a, double b, double eps) {
        double x0 = a;
        double x1 = b;
        double x2 = x1 - f.f(x1) * (x1 - x0) / (f.f(x1) - f.f(x0));
        while (Math.abs(x2 - x1) > eps) {
            x0 = x1;
            x1 = x2;
            x2 = x1 - f.f(x1) * (x1 - x0) / (f.f(x1) - f.f(x0));
        }
        return x2;
    }

    @Override
    public String toString() {
        return "Secant method";
    }

}
