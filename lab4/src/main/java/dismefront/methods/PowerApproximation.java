package dismefront.methods;

import java.util.ArrayList;

public class PowerApproximation implements ApproximationFunction {

    @Override
    public double[] solve(ArrayList<Double> x, ArrayList<Double> y) {
        int n = x.size();
        double sumX = 0.0;
        double sumY = 0.0;
        double sumXY = 0.0;
        double sumX2 = 0.0;
        for (int i = 0; i < n; i++) {
            double xi = x.get(i);
            double yi = y.get(i);
            sumX += Math.log(xi);
            sumY += Math.log(yi);
            sumXY += Math.log(xi) * Math.log(yi);
            sumX2 += Math.log(xi) * Math.log(xi);
        }
        double b = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double a = Math.exp((sumY - b * sumX) / n);
        return new double[]{a, b};
    }
}
