package dismefront.methods;

import dismefront.logic.Equation;

public class Simpson implements Calculate {

    public Result solve(Equation<Double, Double> f, Double epsilon, Double a, Double b) throws CouldNotConvergeException {
        int n = 1;
        double oldIntegral = Double.MAX_VALUE;
        double integral = 0.0;

        while (Math.abs(integral - oldIntegral) / (Math.pow(2, 4) - 1) > epsilon) {
            n *= 2;
            double h = (b - a) / n;
            oldIntegral = integral;
            integral = 0.0;

            for (int i = 0; i < n; i += 2) {
                double x0 = a + h * i;
                double x1 = a + h * (i + 1);
                double x2 = a + h * (i + 2);

                integral += (f.apply(x0) + 4 * f.apply(x1) + f.apply(x2));
            }

            integral *= h / 3;
            if (n >= ConstantBoundary.MAX_SUBINTERVALS) {
                throw new CouldNotConvergeException();
            }
        }

        return new Result(integral, n);
    }

}
