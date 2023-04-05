package dismefront.functions;

import java.util.function.DoubleUnaryOperator;

public interface EquationSystem {

    String what();
    double[] applyMatrix(double... x);
    int id();
    int equationsCount();
    Function[] getFunctions();

}
