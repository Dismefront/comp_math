package dismefront.methods;

import dismefront.logic.Equation;

public class RectangleLeft implements Calculate {

    public Result solve(Equation<Double, Double> equation, Double epsilon, Double a, Double b) throws CouldNotConvergeException {
        double h = b - a;
        int n = 1;
        double prevIntegral = 0;
        double integral = h * equation.apply(a);

        while (Math.abs(integral - prevIntegral) > epsilon) {
            prevIntegral = integral;
            integral = 0;
            h /= 2;
            n *= 2;

            for (int i = 0; i < n; i++) {
                double x = a + i * h;
                integral += equation.apply(x);
            }

            integral *= h;
            if (n >= ConstantBoundary.MAX_SUBINTERVALS) {
                throw new CouldNotConvergeException();
            }
        }

        return new Result(integral, n);
    }

}
