package structuralPattern.src;

import structuralPattern.src.Manipulate;
import structuralPattern.src.Shape;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/26
 */
public class TextManipulator implements Manipulate {
    private Shape shape;

    public TextManipulator(Shape shape) {
        this.shape = shape;
    }
}
