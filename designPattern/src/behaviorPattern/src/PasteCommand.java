package behaviorPattern.src;

public class PasteCommand extends Command {
    private Document document;

    public PasteCommand(Document document) {
        this.document = document;
    }

    @Override
    void execute() {
        document.paste();
    }
}
