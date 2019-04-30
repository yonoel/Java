package builerPattern.src;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/20
 */
public class EnchantedMazeFactory extends MazeFactory implements Spell{

    public Room makeRoom(int n){
        return new EnchantedRoom(n,costSpell());
    }
    public Door makeDoor(Room room1,Room room2){
        return new DoorNeedindSpell(room1, room2);
    }
    @Override
    public String costSpell() {
        return "123";
    }
}
