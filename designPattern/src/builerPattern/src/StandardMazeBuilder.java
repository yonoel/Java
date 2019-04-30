package builerPattern.src;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/21
 */
public class StandardMazeBuilder extends MazeBuilder {
    private Maze maze;

    public StandardMazeBuilder() {
        super();
        maze = null;
    }

    @Override
    void buildMaze() {
        maze = new Maze();
    }

    @Override
    void buildRoom(int n) {
        if (!maze.getRooms().stream().anyMatch(room -> room.getRoomNo() == n)) {
            Room room = new Room(n);
            maze.addRoom(room);

            room.setSide(Direction.North, new Wall());
            room.setSide(Direction.East, new Wall());
            room.setSide(Direction.South, new Wall());
            room.setSide(Direction.West, new Wall());
        }
    }

    @Override
    void buildDoor(int n1, int n2) {
        Map<Integer, List<Room>> rooms = maze.getRooms().stream().filter(room -> room.getRoomNo() == n1 || room.getRoomNo() == n2)
                .collect(Collectors.groupingBy(Room::getRoomNo));
        Room room1 = rooms.get(n1).get(1);
        Room room2 = rooms.get(n2).get(1);
        Door door = new Door(room1, room2);

        room1.setSide(commonWall(room1, room2), door);
        room2.setSide(commonWall(room2, room1), door);

    }

    @Override
    Maze getMaze() {
        return this.maze;
    }

    // 随便写吧。。。决定两个房间之间门的方向
    private Direction commonWall(Room room1, Room room2) {
        return Direction.East;
    }
}
