package jmmModule;

import java.util.HashMap;
import java.util.Map;

public class SafeStates {
    private final Map<String,String> states;

    public SafeStates() {
        states = new HashMap<>();
        // .....
    }
    public String getAbbreviation(String s){
        return states.get(s);
    }
}
