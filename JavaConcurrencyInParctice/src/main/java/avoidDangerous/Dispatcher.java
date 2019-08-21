package avoidDangerous;

import net.jcip.annotations.GuardedBy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class Dispatcher {
    @GuardedBy("this")
    private final Set<Taxi> taxis;
    @GuardedBy("this")
    private final Set<Taxi> availableTaxis;

    public Dispatcher() {
        taxis = new HashSet<>();
        availableTaxis = new HashSet<>();
    }

    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }

    public synchronized Image getImage() {
        Image image = new BufferedImage(100, 100, BufferedImage.TYPE_BYTE_GRAY);
        Graphics graphics = image.getGraphics();
        for (Taxi taxi : taxis) {
            int x = taxi.getLocation().x;
            int y = taxi.getLocation().y;
            graphics.drawLine(x, y, x + 1, y + 1);
        }
        return image;
    }
}
