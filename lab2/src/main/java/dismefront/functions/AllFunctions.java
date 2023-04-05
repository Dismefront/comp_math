package dismefront.functions;

import java.util.ArrayList;

public class AllFunctions {

    private static ArrayList<Function> functions;

    static {
        functions = new ArrayList<>();
        functions.add(new Function1());
        functions.add(new Function2());
        functions.add(new Function3());
        functions.add(new Function4());
    }

    public static ArrayList<Function> getFunctions() {
        return functions;
    }

    public static int size() {
        return functions.size();
    }

}
