package dismefront.methods;

import dismefront.logic.Equation;

public class RectangleMiddle implements Calculate {

    public Result solve(Equation<Double, Double> f, Double epsilon, Double a, Double b) throws CouldNotConvergeException {
        int n = 1;
        double oldIntegral = Double.MAX_VALUE;
        double integral = 0.0;

        while (Math.abs(integral - oldIntegral) > epsilon) {
            n *= 2;
            double h = (b - a) / n;
            oldIntegral = integral;
            integral = 0.0;

            for (int i = 0; i < n; i++) {
                double x = a + h * (i + 0.5);
                integral += f.apply(x);
            }

            integral *= h;
            if (n >= ConstantBoundary.MAX_SUBINTERVALS) {
                throw new CouldNotConvergeException();
            }
        }

        return new Result(integral, n);
    }

}
