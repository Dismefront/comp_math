package com.dismefront.lab6spring;

public class Function {
    public double f(double x, double y, int functionNumber) {
        switch (functionNumber) {
            case (1) -> {
                return y + (1 + x) * (y * y);
            }
            case (2) -> {
                return y + Math.cos(x / Math.sqrt(5));
            }
            default -> {
                return x + y;
            }
        }
    }


    public double f1(double x, double y, int functionNumber) {
        switch (functionNumber) {
            case (1) -> {
                return -1 * Math.pow(Math.E, x) / (Math.pow(Math.E, x) * x);
            }
            case (2) -> {
                return Math.sqrt(5) / 6 * Math.sin(x / Math.sqrt(5)) / 5.0 / 6 * Math.cos(x / Math.sqrt(5)) + Math.exp(x);
            }
            default -> {
                return Math.exp(x) - x - 1;
            }
        }
    }


    public boolean compareColumn(double[][] array1, double[][] array2, double e, int p) {
        if (array1 == null || array2 == null) {
            return false;
        }

        for (int i = 0; i < array2.length; i++) {
            if (array1.length - 1 < i) {
                break;
            }
            if (Math.abs((array1[i][1] - array2[i][1])) / (Math.pow(2, p) - 1) >= e) {
                return false; // Значения в указанном столбце не совпадают
            }
        }

        return true; // Значения в указанном столбце совпадают в обоих массивах
    }

}
