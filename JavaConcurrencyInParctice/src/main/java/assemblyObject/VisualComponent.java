package assemblyObject;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VisualComponent {
    private final List<KeyListener> keyListeners =
            new CopyOnWriteArrayList<>();

    private final List<MouseListener> mouseListeners =
            new CopyOnWriteArrayList<>();
    // ....
}
