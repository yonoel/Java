package behaviorPattern.src;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MacroCommand extends Command {
    private List<Command> commands;

    public MacroCommand() {
        commands = new LinkedList<>();
    }

    public void add(Command command) {
        this.commands.add(command);
    }

    public void remove(Command command) {
        this.commands.removeIf(command1 -> command == command1);
    }

    @Override
    void execute() {
        Iterator<Command> iterator = this.commands.iterator();
        while (iterator.hasNext()){
            iterator.next().execute();
        }
    }
}
