package behaviorPattern.src;

import java.awt.event.MouseEvent;

public abstract class Widget {
    private DialogDirector director;

    public Widget(DialogDirector director) {
        this.director = director;
    }

    abstract void changed();

    abstract void handleMouse(MouseEvent event);
    // ....

}
