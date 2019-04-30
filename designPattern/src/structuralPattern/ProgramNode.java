package structuralPattern;

public abstract class ProgramNode {
    private ProgramNode node;
    abstract void getSourcePosition(int line,int index);
    abstract void add(ProgramNode node);
    abstract void remove(ProgramNode node);
    abstract void traverse(CodeGenerator generator);

}
