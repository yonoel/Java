package behaviorPattern.src;

public class Document {
    private char name;

    public Document(char name) {
        this.name = name;
    }

    public void open() {
        System.out.println("open the document");
    }

    public void paste() {
        System.out.println("paste the document");
    }
}
