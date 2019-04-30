package structuralPattern;

// 语法分析树，采用builder模式构造
public abstract class ProgramNodeBuilder {
    private ProgramNode programNode;

    abstract ProgramNodeBuilder newVariable();

    abstract ProgramNodeBuilder newAssignment();

    abstract ProgramNodeBuilder newReturnStatement();

    abstract ProgramNodeBuilder newCondition();

    abstract ProgramNode getRootNode();
}
