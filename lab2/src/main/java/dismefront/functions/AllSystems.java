package dismefront.functions;

import java.util.ArrayList;

public class AllSystems {

    private static ArrayList<EquationSystem> systems;

    static {
        systems = new ArrayList<>();
        systems.add(new System1());
        systems.add(new System2());
    }

    public static ArrayList<EquationSystem> getSystems() {
        return systems;
    }

    public static int size() {
        return systems.size();
    }

}
