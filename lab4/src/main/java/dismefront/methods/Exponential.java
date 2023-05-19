package dismefront.methods;

import java.util.ArrayList;

public class Exponential implements ApproximationFunction {

    public double[] solve(ArrayList<Double> x, ArrayList<Double> y) {
        int n = x.size();
        double sumX = 0, sumY = 0, sumX2 = 0, sumXY = 0;

        for (int i = 0; i < n; i++) {
            sumX += x.get(i);
            sumY += Math.log(y.get(i));
            sumX2 += x.get(i) * x.get(i);
            sumXY += x.get(i) * Math.log(y.get(i));
        }

        double delta = n * sumX2 - sumX * sumX;
        double a = Math.exp((sumY * sumX2 - sumX * sumXY) / delta);
        double b = (n * sumXY - sumX * sumY) / delta;

        return new double[] {a, b};
    }
}