package dismefront.logic;

public class Equation4 implements Equation<Double, Double> {

    public Double apply(Double x) {
        return 2 * Math.pow(3, x) - 4;
    }

    public String what() {
        return "2 * 3^x - 4";
    }

}
