package builerPattern.src;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/21
 */
public class EnchantedMazeGame extends MazeGame implements Spell {
    @Override
    public String costSpell() {
        return null;
    }

    @Override
    public Room createRoom(int n) {
        return new EnchantedRoom(n, costSpell());
    }

    @Override
    public Door createDoor(Room room1, Room room2) {
        return new DoorNeedindSpell(room1, room2);
    }
}
