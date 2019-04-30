package builerPattern.src;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/20
 */
public class Room extends MapSite {
    private int roomNo;
    private MapSite[] mapSites;

    public MapSite getSide(Direction direction) {
        int order = direction.ordinal();
        return mapSites[order];
    }

    public Room(int roomNo) {
        this.roomNo = roomNo;
        mapSites = new MapSite[4];
    }

    public void setSide(Direction direction, MapSite mapSite) {
        int order = direction.ordinal();
        mapSites[order] = mapSite;
    }

    @Override
    public void Enter() {

    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    @Override
    protected Room clone() {
        Room room = new Room(this.roomNo);
        for (int i = 0; i < Direction.values().length; i++) {
            room.setSide(Direction.values()[i], mapSites[i]);
        }
        return room;
    }
}
