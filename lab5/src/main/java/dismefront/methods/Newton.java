package dismefront.methods;

import java.util.ArrayList;

public class Newton implements Interpolation {

    private ArrayList<Double> x, y;
    private double[][] dividedDifferences;

    private void calculateDividedDifferences() {
        int n = x.size();
        dividedDifferences = new double[n][n];

        for (int i = 0; i < n; i++) {
            dividedDifferences[i][0] = y.get(i);
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                dividedDifferences[i][j] = (dividedDifferences[i + 1][j - 1] - dividedDifferences[i][j - 1]) / (x.get(i + j) - x.get(i));
            }
        }
    }

    public void printDifference() {
        for (int j = 1; j < x.size(); j++) {
            for (int i = 0; i < x.size() - j; i++) {
                System.out.printf("\t|%f|", dividedDifferences[i][j]);
            }
            System.out.println();
        }
    }

    public Newton(ArrayList<Double> x, ArrayList<Double> y) {
        this.x = x;
        this.y = y;
        calculateDividedDifferences();
    }

    @Override
    public double interpolate(double val) {
        int n = x.size();
        double result = dividedDifferences[0][0];
        double term = 1.0;

        for (int i = 1; i < n; i++) {
            term *= (val - x.get(i - 1));
            result += dividedDifferences[0][i] * term;
        }

        return result;
    }
}
