package structuralPattern;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/26
 */
public interface TextView {

    void getOrigin(Coord x, Coord y);

    void getExtend(Coord width, Coord height);

    boolean isEmpty();
}
