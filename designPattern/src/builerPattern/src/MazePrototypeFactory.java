package builerPattern.src;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/21
 */
public class MazePrototypeFactory extends MazeFactory {
    private Maze maze;
    private Wall wall;
    private Room room;
    private Door door;

    public Door makeDoor(Room room1, Room room2) {
        Door door = this.door.clone();
        door.initialize(room1, room2);
        return door;
    }

    public Room makeRoom(int n) {
        Room room = this.room.clone();
        room.setRoomNo(n);
        return room;
    }

    public Wall makeWall() {
        return wall.clone();
    }


    public MazePrototypeFactory(Maze maze, Wall wall, Room room, Door door) {
        this.maze = maze;
        this.wall = wall;
        this.room = room;
        this.door = door;
    }
}
