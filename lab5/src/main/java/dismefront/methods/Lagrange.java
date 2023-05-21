package dismefront.methods;

import java.util.ArrayList;

public class Lagrange implements Interpolation {

    ArrayList<Double> x, y;

    public Lagrange(ArrayList<Double> x, ArrayList<Double> y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double interpolate(double val) {
        double result = 0.0;
        int n = x.size();

        for (int i = 0; i < n; i++) {
            double term = y.get(i);
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    term *= (val - x.get(j)) / (x.get(i) - x.get(j));
                }
            }
            result += term;
        }

        return result;
    }

}
