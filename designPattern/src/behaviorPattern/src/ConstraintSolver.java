package behaviorPattern.src;

public class ConstraintSolver {
    public static ConstraintSolver getInstance() {
        return new ConstraintSolver();
    }

    public void solve() {
    }

    public void addConstraint(Graphic startConnection, Graphic endConnection) {
    }

    public void removeConstraint(Graphic startConnection, Graphic endConnection) {

    }

    public ConstraintSolverMemento createMemento() {
        return new ConstraintSolverMemento();
    }

    public void setMemento(ConstraintSolverMemento memento) {

    }

}
