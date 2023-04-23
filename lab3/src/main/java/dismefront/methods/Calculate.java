package dismefront.methods;

import dismefront.logic.Equation;

public interface Calculate {

    Result solve(Equation<Double, Double> eq, Double eps, Double a, Double b) throws CouldNotConvergeException;

}
