package dismefront.logic;

import java.util.function.Function;

public interface Equation<T, R> extends Function<T, R> {

    String what();

}
