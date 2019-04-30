package structuralPattern.src;

import java.awt.*;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/26
 */
public abstract class Shape {
    abstract void boundingBox(Point bottomLeft,Point topRight);
    abstract Manipulate createManipulator();
}
