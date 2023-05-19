package dismefront.methods;

public class SimpleRegression {
    private double sumX;
    private double sumY;
    private double sumX2;
    private double sumXY;
    private int n;

    public void addData(double[] x, double[] y) {
        for (int i = 0; i < x.length; i++) {
            sumX += x[i];
            sumY += y[i];
            sumX2 += x[i] * x[i];
            sumXY += x[i] * y[i];
            n++;
        }
    }

    public double getIntercept() {
        double meanX = sumX / n;
        double meanY = sumY / n;
        double slope = (sumXY - sumX * meanY) / (sumX2 - sumX * meanX);
        return meanY - slope * meanX;
    }
}