package dismefront.methods;

import java.util.ArrayList;

public class Methods {

    private static ArrayList<Object> methods;

    static {
        methods = new ArrayList<>();
        methods.add(new Newton());
        methods.add(new Secant());
        methods.add(new SimpleIteration());
    }

    public static ArrayList<Object> getMethods() {
        return methods;
    }

    public static void show() {
        for (int i = 0; i < methods.size(); i++)
            System.out.printf("%d) %s\n", i + 1, methods.get(i));
    }

}
