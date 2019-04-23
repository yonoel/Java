package builerPattern;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/20
 */
public class Maze {

    private LinkedList<Room> rooms;

    public Maze() {
        rooms = new LinkedList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(LinkedList<Room> rooms) {
        this.rooms = rooms;
    }
}
