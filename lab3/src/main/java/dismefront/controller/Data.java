package dismefront.controller;

import dismefront.logic.Equation;
import dismefront.logic.Method;

public record Data(Method method, Equation<Double, Double> equation, Double eps, Double leftB, Double rightB) {}
