package behaviorPattern.src;

public class AndExp extends BooleanExp {
    private BooleanExp operand1;
    private BooleanExp operand2;

    public AndExp(BooleanExp operand1, BooleanExp operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    boolean evaluate(Context context) {
        return operand1.evaluate(context) && operand2.evaluate(context);
    }

    @Override
    BooleanExp replace(char c, BooleanExp booleanExp) {
        return new AndExp(operand1.replace(c, booleanExp), operand2.replace(c, booleanExp));
    }

    @Override
    BooleanExp copy() {
        return new AndExp(operand1.copy(), operand2.copy());
    }
}
