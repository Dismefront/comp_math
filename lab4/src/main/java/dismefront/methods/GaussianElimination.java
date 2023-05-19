package dismefront.methods;

public class GaussianElimination {

    private final double[][] matrix;
    private final double[] constants;
    private final double[] solution;

    public GaussianElimination(double[][] matrix, double[] constants) {
        this.matrix = matrix;
        this.constants = constants;
        this.solution = new double[constants.length];
        solve();
    }

    private void solve() {
        int n = constants.length;
        for (int i = 0; i < n - 1; i++) {
            int pivot = i;
            double max = Math.abs(matrix[i][i]);
            for (int j = i + 1; j < n; j++) {
                double abs = Math.abs(matrix[j][i]);
                if (abs > max) {
                    max = abs;
                    pivot = j;
                }
            }
            if (pivot != i) {
                swapRows(pivot, i);
                double temp = constants[pivot];
                constants[pivot] = constants[i];
                constants[i] = temp;
            }
            for (int j = i + 1; j < n; j++) {
                double factor = matrix[j][i] / matrix[i][i];
                constants[j] -= factor * constants[i];
                for (int k = i; k < n; k++) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += matrix[i][j] * solution[j];
            }
            solution[i] = (constants[i] - sum) / matrix[i][i];
        }
    }

    private void swapRows(int i, int j) {
        double[] tempRow = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = tempRow;
    }

    public double getSolution(int index) {
        return solution[index];
    }
}