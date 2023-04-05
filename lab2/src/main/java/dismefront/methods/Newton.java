package dismefront.methods;

import dismefront.functions.Function;

public class Newton {

    public static double H = 1e-9;

    public double findRoot(Function f, double a, double b, double epsilon) {
        double x = (a + b) / 2.0;
        double fx = f.f(x);
        double dfx = (f.f(x + H) - f.f(x)) / H;
        double diff = epsilon + 1.0;
        int maxIter = 1000;
        int iter = 0;

        while (Math.abs(diff) > epsilon && iter < maxIter) {
            x = x - fx / dfx;
            fx = f.f(x);
            dfx = (f.f(x + H) - f.f(x)) / H;
            diff = x - (x - fx / dfx);
            iter++;
        }

        if (iter == maxIter) {
            System.err.println("Maximum number of iterations reached.");
        }

        return x;
    }

    @Override
    public String toString() {
        return "Newton's method";
    }
}
