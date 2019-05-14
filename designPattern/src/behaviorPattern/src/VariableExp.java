package behaviorPattern.src;

public class VariableExp extends BooleanExp {
    private char name;

    public char getName() {
        return name;
    }

    public VariableExp(char name) {
        this.name = name;
    }

    @Override
    boolean evaluate(Context context) {
        return context.lookup(this.name);
    }

    @Override
    BooleanExp replace(char c, BooleanExp booleanExp) {
        if (c == this.name) return booleanExp.copy();
        return new VariableExp(this.name);
    }

    @Override
    BooleanExp copy() {
        return new VariableExp(this.name);
    }
}
