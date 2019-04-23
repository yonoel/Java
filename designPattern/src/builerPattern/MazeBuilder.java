package builerPattern;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/21
 */
public abstract class MazeBuilder {
    // 可以将build其他产品的操作都放在maze里，但是这样不利于分离代码，也不利于理解，其次分离还可以写多种builder
    abstract void buildMaze();


    abstract void buildRoom(int n);

    abstract void buildDoor(int n1,int n2);

    abstract Maze getMaze();


    protected MazeBuilder() {
    }
}
