package builerPattern.src;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/20
 */
public class RoomWithABomb extends Room {
    private boolean isBombed;
    public RoomWithABomb(int roomNo) {
        super(roomNo);
        isBombed = false;
    }
}
