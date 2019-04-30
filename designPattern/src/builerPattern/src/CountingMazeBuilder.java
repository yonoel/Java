package builerPattern.src;


/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/21
 */
public class CountingMazeBuilder extends MazeBuilder {
    private int doors;
    private int rooms;

    public CountingMazeBuilder() {
        doors = rooms = 0;
    }

    public void setCounts() {
    }

    @Override
    void buildMaze() {

    }

    @Override
    void buildRoom(int n) {
        rooms++;
    }

    @Override
    void buildDoor(int n1, int n2) {
        doors++;
    }

    @Override
    Maze getMaze() {
        return null;
    }
}
