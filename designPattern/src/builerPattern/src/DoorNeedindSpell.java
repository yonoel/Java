package builerPattern.src;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/20
 */
public class DoorNeedindSpell extends Door implements Spell {
    protected String spell;
    public DoorNeedindSpell(Room room1, Room room2) {
        super(room1, room2);
    }

    @Override
    public String costSpell() {
        return null;
    }
}
