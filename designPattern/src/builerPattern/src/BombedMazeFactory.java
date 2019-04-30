package builerPattern.src;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/20
 */
public class BombedMazeFactory extends MazeFactory {
    public Wall makeWall(){
        return new BombedWall(true);
    }
    public Room makeRoom(int n){
        return new RoomWithABomb(n);
    }
}
