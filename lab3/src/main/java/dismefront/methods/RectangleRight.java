package dismefront.methods;

import dismefront.logic.Equation;

public class RectangleRight implements Calculate {

    public Result solve(Equation<Double, Double> equation, Double epsilon, Double a, Double b) throws CouldNotConvergeException {
        double integral = 0.0;
        int numSubIntervals = 1;
        double delta = (b - a) / numSubIntervals;

        while (true) {
            double sum = 0.0;
            for (int i = 1; i <= numSubIntervals; i++) {
                double x = a + i * delta;
                sum += equation.apply(x);
            }
            double newIntegral = sum * delta;
            if (Math.abs(newIntegral - integral) < epsilon) {
                return new Result(newIntegral, numSubIntervals);
            }
            integral = newIntegral;
            numSubIntervals *= 2;
            delta /= 2.0;
            if (numSubIntervals >= ConstantBoundary.MAX_SUBINTERVALS) {
                throw new CouldNotConvergeException();
            }
        }
    }

}
