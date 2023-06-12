package com.dismefront.lab6spring.Methods;

import com.dismefront.lab6spring.Function;

public class AdamsMethod {

    Function function = new Function();

    public double[][] method(double a, double b, double y0, double h, int functionNumber, double e) {
        if (a >= b) {
            return null;
        }
        int p = 4;
        int n = (int) (Math.abs(b - a) / h) + 1;
        if (h >= Math.abs(a-b)) {
            return null;
        }


        double[][] result = new double[n][4];
        result[0][0] = a;
        result[0][1] = function.f1(a, y0, functionNumber);
        double k1, k2, k3, k4;
        result[0][2] = function.f(result[0][0], result[0][1], functionNumber);
        result[0][3] = function.f1(result[0][0], result[0][1], functionNumber);

        for (int i = 1; i <= 5; i++) {

            result[i][0] = result[i - 1][0] + h;

            k1 = h * function.f(result[i - 1][0], result[i - 1][1], functionNumber);
            k2 = h * function.f(result[i - 1][0] + h / 2, result[i - 1][1] + k1 / 2, functionNumber);
            k3 = h * function.f(result[i - 1][0] + h / 2, result[i - 1][1] + k2 / 2, functionNumber);
            k4 = h * function.f(result[i - 1][0] + h, result[i - 1][1] + k3, functionNumber);
            result[i][1] = result[i - 1][1] + (1 / 6.0) * (k1 + 2 * k2 + 2 * k3 + k4);
            result[i][2] = function.f(result[i][0], result[i][1], functionNumber);
            result[i][3] = function.f1(result[i][0], result[i][1], functionNumber);
        }

        double yp = 0;
        for (int i = 5; i < result.length; i++) {
            result[i][0] = result[i - 1][0] + h;
            result[i][3] = function.f1(result[i][0], result[i][1], functionNumber);

            double del1 = result[i - 1][2] - result[i -2][2];
            double del2 = result[i - 1][2] - 2 * result[i - 2][2] - result[i - 3][2];
            double del3 = result[i - 1][2] - 3 * result[i - 2][2] + 3 * result[i - 3][2] - result[i - 4][2];
            double ans = result[i - 1][1] + h * result[i - 1][2] + h * h / 2 * del1 + 5 * h * h * h / 12 * del2 + 3 * h * h * h * h / 8 * del3;


            if (e == 0) {
                return null;
            }

            result[i][1] = ans;
            result[i][2] = function.f(result[i][0], yp, functionNumber);
        }

        return result;
    }
}
