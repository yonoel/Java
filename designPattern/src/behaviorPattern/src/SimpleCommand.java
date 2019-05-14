package behaviorPattern.src;

import java.lang.reflect.Method;

public abstract class SimpleCommand<Action extends Method, Receiver> extends Command {
    private Action action;
    private Receiver receiver;

    public SimpleCommand(Action action, Receiver receiver) {
        this.action = action;
        this.receiver = receiver;
    }

    @Override
    void execute() {
        // receiver execute action
    }
}
