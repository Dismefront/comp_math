package com.dismefront.lab6spring.Methods;

import com.dismefront.lab6spring.Function;

public class RungeMethod {


    public double[][] method(double a, double b, double y0, double h, int functionNumber, double e) {

        if (a >= b){
            return null;
        }
        int p = 4;
        int n = (int) (Math.abs(b - a) / h) + 1;
        if (h >= Math.abs(a-b)) {
            return null;
        }
        Function function = new Function();
        double[][] result = new double[n][4];
        result[0][0] = a;
        result[0][1] = function.f1(a, y0, functionNumber);;
        double k1, k2, k3, k4;
        result[0][2] = function.f(result[0][0], result[0][1], functionNumber);
        result[0][3] = function.f1(result[0][0], result[0][1], functionNumber);


        for (int i = 1; i < result.length; i++) {
            result[i][0] = result[i - 1][0] + h;

            k1 = h * function.f(result[i - 1][0], result[i - 1][1], functionNumber);
            k2 = h * function.f(result[i - 1][0] + h / 2, result[i - 1][1] + k1 / 2, functionNumber);
            k3 = h * function.f(result[i - 1][0] + h / 2, result[i - 1][1] + k2 / 2, functionNumber);
            k4 = h * function.f(result[i - 1][0] + h, result[i - 1][1] + k3, functionNumber);
            result[i][1] = result[i - 1][1] + (1 / 6.0) * (k1 + 2 * k2 + 2 * k3 + k4);
            result[i][2] = function.f(result[i][0], result[i][1], functionNumber);
            result[i][3] = function.f1(result[i][0], result[i][1], functionNumber);
        }



        return result;

    }

}

