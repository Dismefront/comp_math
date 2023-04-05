package dismefront.methods;

import java.util.ArrayList;

public class SystemMethods {

    private static ArrayList<Object> methods;

    static {
        methods = new ArrayList<>();
        methods.add(new NewtonSystem());
    }

    public static ArrayList<Object> getMethods() {
        return methods;
    }

    public static void show() {
        for (int i = 0; i < methods.size(); i++)
            System.out.printf("%d) %s\n", i + 1, methods.get(i));
    }

}
