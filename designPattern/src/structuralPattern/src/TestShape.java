package structuralPattern.src;

import java.awt.*;

/**
 * @program: Java
 * @description: 采用接口，或者说多继承的方法来实现适配器
 * @author: Qian Yi Zhen
 * @create: 2019/04/26
 */
public class TestShape extends Shape implements TextView {
    // 父类只有2个函数
    @Override
    void boundingBox(Point bottomLeft, Point topRight) {
        // 父类的抽象不足，需要实现别样的操作,此时实现了View接口，是伪代码
//        Coord bottom = null, left = null, width = null, height = null;
//        getOrigin(bottom, left);
//        getExtend(width, height);
//        bottomLeft = new Point(bottom, left);
//        topRight = new Point(bottom+height, left +width);
    }

    @Override
    Manipulate createManipulator() {
        return new TextManipulator(this);
    }

    @Override
    public void getOrigin(Coord x, Coord y) {

    }

    @Override
    public void getExtend(Coord width, Coord height) {

    }

    @Override
    public boolean isEmpty() {
        // 直接转发给adaptee操作
//        return new TextView().isEmpty();
        return false;
    }
}
