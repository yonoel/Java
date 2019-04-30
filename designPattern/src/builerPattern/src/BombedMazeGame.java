package builerPattern.src;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/21
 */
public class BombedMazeGame extends MazeGame {
    @Override
    public Wall createWall() {
        return new BombedWall(true);
    }

    @Override
    public Room createRoom(int n) {
        return new RoomWithABomb(n);
    }
}
