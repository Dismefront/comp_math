package dismefront.methods;

import java.util.ArrayList;

public class Logarithmic implements ApproximationFunction {

    @Override
    public double[] solve(ArrayList<Double> x, ArrayList<Double> y) {
        int n = x.size();
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0, sumLnX = 0, sumLnY = 0;
        for (int i = 0; i < n; i++) {
            double lnX = Math.log(x.get(i));
            double lnY = Math.log(y.get(i));
            sumX += x.get(i);
            sumY += y.get(i);
            sumXY += x.get(i) * lnY;
            sumX2 += x.get(i) * x.get(i);
            sumLnX += lnX;
            sumLnY += lnY;
        }
        double delta = n * sumX2 - sumX * sumX;
        double a = (sumLnY * sumX2 - sumXY * sumX) / delta;
        double b = (n * sumXY - sumX * sumLnY) / delta;
        return new double[] {a, b};
    }
}
