package behaviorPattern.src;

import java.awt.event.MouseEvent;

public abstract class ListBox extends Widget {
    public ListBox(DialogDirector director) {
        super(director);
    }

    @Override
    void changed() {

    }

    @Override
    void handleMouse(MouseEvent event) {

    }
}
