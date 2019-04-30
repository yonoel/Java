package builerPattern.src;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/20
 */
public class Door extends MapSite {
    @Override
    protected Door clone() {
        Door door = new Door(room1.clone(), room2.clone());
//        Door door = new Door(this);
        return door;
    }


    private Room room1;
    private Room room2;
    private boolean isOpen;

    @Override
    public void Enter() {
    }

    public Door(Room room1, Room room2) {
        this.room1 = room1;
        this.room2 = room2;
    }

    public Room getOtherRoom() {
        return room1;
    }

    public void initialize(Room room1, Room room2) {
        this.room1 = room1;
        this.room2 = room2;
    }
}
