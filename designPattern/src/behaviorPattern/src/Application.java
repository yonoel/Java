package behaviorPattern.src;

import java.util.ArrayList;
import java.util.LinkedList;

public class Application {
    public static void main(String[] args) {
        Handler handler = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler1();
        Handler handler4 = new ConcreteHandler2();
        int no_help = -1;
        handler.setNextHandler(handler2, no_help);
        handler2.setNextHandler(handler3, no_help);
        handler3.setNextHandler(handler4, no_help);
        handler4.setNextHandler(null, 1);
        handler.doRequest();
    }

    private ArrayList<Document> documents;

    public void add(Document document) {
        this.documents.add(document);
    }
}
