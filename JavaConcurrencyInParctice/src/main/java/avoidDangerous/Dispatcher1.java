package avoidDangerous;

import net.jcip.annotations.GuardedBy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class Dispatcher1 {
    @GuardedBy("this")
    private final Set<Taxi1> taxis;
    @GuardedBy("this")
    private final Set<Taxi1> availableTaxis;

    public Dispatcher1() {
        taxis = new HashSet<>();
        availableTaxis = new HashSet<>();
    }

    public synchronized void notifyAvailable(Taxi1 taxi) {
        availableTaxis.add(taxi);
    }

    public Image getImage() {
        Set<Taxi1> copy;
        synchronized (this){
            copy = new HashSet<>(taxis);
        }
        Image image = new BufferedImage(100, 100, BufferedImage.TYPE_BYTE_GRAY);
        Graphics graphics = image.getGraphics();
        for (Taxi1 taxi : copy) {
            int x = taxi.getLocation().x;
            int y = taxi.getLocation().y;
            graphics.drawLine(x, y, x + 1, y + 1);
        }
        return image;
    }
}
