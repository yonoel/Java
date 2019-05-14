package behaviorPattern.src;

public class OpenCommand extends Command {
    private Application application;
    private char response;

    public OpenCommand(Application application) {
        this.application = application;
    }

    @Override
    void execute() {
        char name = askUser();
        if (name != 0){
            Document document =new Document(name);
            application.add(document);
            document.open();
        }
    }

    private char askUser() {
        return 0;
    }
}
