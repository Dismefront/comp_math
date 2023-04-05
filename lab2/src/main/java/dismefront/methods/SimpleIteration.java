package dismefront.methods;

import dismefront.functions.Function;
import dismefront.main.Main;

public class SimpleIteration {

    public double findRoot(Function f, double x0, double eps) {
        double x = f.f(x0);
        double xn = f.f(x);
        int maxIterations = 1000;
        int i = 0;
        double diff = Math.abs(x - xn);
        while (diff > eps && i < maxIterations) {
            x = f.f(xn);
            xn = f.f(x);
            diff = Math.abs(xn - x);
            i++;
        }
        if (i >= maxIterations) {
            throw new RuntimeException("Method failed to converge");
        }
        return xn;
    }

    @Override
    public String toString() {
        return "Simple Iteration method";
    }

}
