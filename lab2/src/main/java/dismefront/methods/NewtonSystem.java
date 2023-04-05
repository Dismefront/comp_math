package dismefront.methods;

import dismefront.functions.EquationSystem;

import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class NewtonSystem {

    private static final int MAX_ITERATIONS = 1000;
    private static final double H = 1e-9;

    public double[][] jacobian(EquationSystem f, double[] x, double h) {
        int n = f.equationsCount();
        double[][] jacobian = new double[n][n];
        // Iterate over each function
        for (int i = 0; i < n; i++) {
            // Iterate over each variable
            for (int j = 0; j < n; j++) {
                // Calculate the partial derivative
                x[j] += h;
                double fPlusH = f.getFunctions()[i].f(x);
                x[j] -= h;
                double partialDerivative = (fPlusH - f.getFunctions()[i].f(x)) / h;
                jacobian[i][j] = partialDerivative;
            }
        }

        return jacobian;
    }

    public double[] solve(EquationSystem f, double[] x0, double eps) {
        int iterations = 0;
        double[] x = Arrays.copyOf(x0, x0.length);

        while (iterations < MAX_ITERATIONS) {
            double[] fValues = f.applyMatrix(x);
            double[][] jacobianValues = jacobian(f, x, H);
            double[] deltaX = gaussianElimination(jacobianValues, fValues);
            for (int i = 0; i < x.length; i++) {
                x[i] -= deltaX[i];
            }

            if (maxAbs(deltaX) < eps) {
                return x;
            }

            iterations++;
        }

        throw new RuntimeException("Failed to converge after " + MAX_ITERATIONS + " iterations.");
    }

    private double maxAbs(double[] arr) {
        double max = Math.abs(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(arr[i]) > max) {
                max = Math.abs(arr[i]);
            }
        }
        return max;
    }

    private double[] gaussianElimination(double[][] a, double[] b) {
        int n = a.length;
        for (int p = 0; p < n; p++) {
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(a[i][p]) > Math.abs(a[max][p])) {
                    max = i;
                }
            }

            double[] temp = a[p];
            a[p] = a[max];
            a[max] = temp;
            double t = b[p];
            b[p] = b[max];
            b[max] = t;

            for (int i = p + 1; i < n; i++) {
                double factor = a[i][p] / a[p][p];
                b[i] -= factor * b[p];
                for (int j = p; j < n; j++) {
                    a[i][j] -= factor * a[p][j];
                }
            }
        }

        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += a[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / a[i][i];
        }

        return x;
    }

    @Override
    public String toString() {
        return "Newton system method";
    }

}
