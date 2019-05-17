package behaviorPattern.src;

public class MoveCommand extends Command {
    private Point point;
    private Graphic graphic;
    private ConstraintSolverMemento memento;

    public MoveCommand(Point point, Graphic graphic, ConstraintSolverMemento memento) {
        this.point = point;
        this.graphic = graphic;
        this.memento = memento;
    }

    @Override
    public void execute() {

    }

    public void unexecute() {

    }
}
