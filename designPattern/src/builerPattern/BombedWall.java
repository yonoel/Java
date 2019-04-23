package builerPattern;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/20
 */
public class BombedWall extends Wall {
    private boolean hasBomb;

    public BombedWall(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    @Override
    protected Wall clone() {
        BombedWall wall = new BombedWall(this.hasBomb);
        return wall;
    }
}
