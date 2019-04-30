package structuralPattern.src;


import java.awt.*;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/26
 */
public abstract class Window {
    private WindowImpl windowImpl;
    private View contents;
    // requests handled by window
    abstract void drawContents();

    abstract void open();

    abstract void close();

    abstract void iconify();

    abstract void deiconify();

    // requests forwarded to implementation
    abstract void setOrigin(Point point);

    abstract void setExtent(Point point);

    abstract void raise();

    abstract void lower();

    abstract void drawLine();

    abstract void drawRect();

    abstract void drawPolygon();

    abstract void drawText();

}
