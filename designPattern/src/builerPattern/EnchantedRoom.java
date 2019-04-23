package builerPattern;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/20
 */
public class EnchantedRoom extends Room  {
    private String spell;

    public EnchantedRoom(int roomNo, String spell) {
        super(roomNo);
        this.spell = spell;
    }


}
