package dismefront.methods;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

import java.util.ArrayList;

public class Polynomial3D {
    public static double[] solve(ArrayList<Double> x, ArrayList<Double> y) {
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("x and y arrays must have the same length");
        }

        // Create a WeightedObservedPoints object to hold the data points
        WeightedObservedPoints points = new WeightedObservedPoints();
        for (int i = 0; i < x.size(); i++) {
            points.add(x.get(i), y.get(i));
        }

        // Use Apache Commons Math to fit a third degree polynomial curve to the data
        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(3);

        return fitter.fit(points.toList());
    }
}