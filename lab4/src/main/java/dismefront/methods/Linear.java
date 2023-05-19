package dismefront.methods;

import java.util.ArrayList;

public class Linear implements ApproximationFunction {

    public double[] solve(ArrayList<Double> x, ArrayList<Double> y) {
        int n = x.size();

        double sumX = 0.0;
        double sumY = 0.0;
        double sumXY = 0.0;
        double sumX2 = 0.0;

        for (int i = 0; i < n; i++) {
            sumX += x.get(i);
            sumY += y.get(i);
            sumXY += x.get(i) * y.get(i);
            sumX2 += x.get(i) * x.get(i);
        }

        double slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double intercept = (sumY - slope * sumX) / n;

        double[] result = new double[2];
        result[0] = intercept;
        result[1] = slope;

        return result;
    }

}
