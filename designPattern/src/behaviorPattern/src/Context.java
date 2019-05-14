package behaviorPattern.src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Context {
    private Map<Character, Boolean> meaning;

    public Context() {
        meaning = new HashMap<>();
    }

    public boolean lookup(char name) {
        return meaning.get(name);
    }

    public void assign(VariableExp variableExp, boolean flag) {
        meaning.put(variableExp.getName(), flag);
    }
}