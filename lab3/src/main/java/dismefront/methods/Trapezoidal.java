package dismefront.methods;

import dismefront.logic.Equation;

public class Trapezoidal implements Calculate {

    public Result solve(Equation<Double, Double> f, Double epsilon, Double a, Double b) throws CouldNotConvergeException {
        double integral = 0;
        int n = 1;
        double prevIntegral = Double.MAX_VALUE;

        while (Math.abs(integral - prevIntegral) > epsilon) {
            prevIntegral = integral;

            double h = (b - a) / n;
            integral = (f.apply(a) + f.apply(b)) / 2;

            for (int i = 1; i < n; i++) {
                integral += f.apply(a + i * h);
            }

            integral *= h;
            n *= 2;
            if (n >= ConstantBoundary.MAX_SUBINTERVALS) {
                throw new CouldNotConvergeException();
            }
        }

        return new Result(integral, n);
    }

}
