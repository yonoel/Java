package structuralPattern;

public abstract class Scanner {
    private BytecodeStream stream;

    abstract Token scan();

    public Scanner(BytecodeStream stream) {
        this.stream = stream;
    }
}
