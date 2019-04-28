package structuralPattern;

import java.awt.*;

/**
 * @program: Java
 * @description: 适配器采用对象组合的方式实现功能的实现
 * @author: Qian Yi Zhen
 * @create: 2019/04/26
 */
public class TextShapeCopy extends Shape {
    private TextView textView;

    public TextShapeCopy(TextView view) {
        textView = view;
    }

    @Override
    void boundingBox(Point bottomLeft, Point topRight) {
        Coord bottom = null, left = null, width = null, height = null;
//        textView.getOrigin(bottom, left);
//        textView.getExtend(width, height);
//        bottomLeft = new Point(bottom, left);
//        topRight = new Point(bottom + height,left+width);
    }

    @Override
    Manipulate createManipulator() {
        return new TextManipulator(this);
    }

    public boolean isEmpty() {
        return textView.isEmpty();
    }
}
