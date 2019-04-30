package structuralPattern;



public abstract class CodeGenerator {
    private BytecodeStream stream;
    abstract void visit(StatementNode node);
    abstract void visit(ExpressionNode node);
}
