package dismefront.methods;

import java.util.ArrayList;

public class Polynomial2D implements ApproximationFunction {

    @Override
    public double[] solve(ArrayList<Double> x, ArrayList<Double> y) {
        int n = x.size();
        double[] result = new double[3];

        double sumX = 0.0, sumY = 0.0, sumX2 = 0.0, sumX3 = 0.0, sumX4 = 0.0, sumXY = 0.0, sumX2Y = 0.0;
        for (int i = 0; i < n; i++) {
            double xi = x.get(i);
            double yi = y.get(i);
            sumX += xi;
            sumY += yi;
            sumX2 += xi * xi;
            sumX3 += xi * xi * xi;
            sumX4 += xi * xi * xi * xi;
            sumXY += xi * yi;
            sumX2Y += xi * xi * yi;
        }

        double[][] matrix = {
                { sumX4, sumX3, sumX2 },
                { sumX3, sumX2, sumX },
                { sumX2, sumX, n }
        };

        double[] constants = { sumX2Y, sumXY, sumY };

        GaussianElimination ge = new GaussianElimination(matrix, constants);
        result[0] = ge.getSolution(0);
        result[1] = ge.getSolution(1);
        result[2] = ge.getSolution(2);

        return result;
    }

}
