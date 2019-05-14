package behaviorPattern.src;

public abstract class BooleanExp {
    abstract boolean evaluate(Context context);

    abstract BooleanExp replace(char c, BooleanExp booleanExp);

    abstract BooleanExp copy();

}
