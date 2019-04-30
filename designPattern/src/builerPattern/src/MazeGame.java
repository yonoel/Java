package builerPattern.src;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/20
 */
public  class MazeGame {
    public MazeFactory createMazeFactory(String name) {
        return new MazeFactory(name);
    }

    // factory Method
    public Maze createMaze() {
        Maze maze = new Maze();
        Room room1 = createRoom(1);
        Room room2 = createRoom(2);
        Door door = createDoor(room1, room2);

        maze.addRoom(room1);
        maze.addRoom(room2);

        room1.setSide(Direction.North, createWall());
        room1.setSide(Direction.East, createWall());
        room1.setSide(Direction.West, createWall());
        room1.setSide(Direction.South, door);

        room2.setSide(Direction.North, createWall());
        room2.setSide(Direction.East, door);
        room2.setSide(Direction.West, createWall());
        room2.setSide(Direction.South, createWall());
        return maze;
    }


    public Door createDoor(Room room1, Room room2) {
        return new Door(room1, room2);
    }


    public Room createRoom(int n) {
        return new Room(n);
    }


    public Wall createWall() {
        return new Wall();
    }


    // builder-->隐藏了内部表示，比如墙壁
    public Maze createMaze(MazeBuilder builder) {
        builder.buildMaze();
        builder.buildRoom(1);
        builder.buildRoom(2);
        builder.buildDoor(1, 2);
        return builder.getMaze();
    }

    // abstractFactory,factory是base类，然后不同产品的体系的工程类是他的子
    public Maze createMaze(MazeFactory factory) {
        Maze maze = factory.makeMaze();
        Room room1 = factory.makeRoom(1);
        Room room2 = factory.makeRoom(2);
        Door door = factory.makeDoor(room1, room2);

        maze.addRoom(room1);
        maze.addRoom(room2);

        room1.setSide(Direction.North, factory.makeWall());
        room1.setSide(Direction.East, factory.makeWall());
        room1.setSide(Direction.West, factory.makeWall());
        room1.setSide(Direction.South, door);

        room2.setSide(Direction.North, factory.makeWall());
        room2.setSide(Direction.East, door);
        room2.setSide(Direction.West, factory.makeWall());
        room2.setSide(Direction.South, factory.makeWall());
        return maze;
    }
    // normal
//    public Maze createMaze() {
//        Maze maze = new Maze();
//        Room room1 = new Room(1);
//        Room room2 = new Room(2);
//        Door door = new Door(room1, room2);
//        maze.addRoom(room1);
//        maze.addRoom(room2);
//
//        room1.setSide(Direction.North, new Wall());
//        room1.setSide(Direction.East, door);
//        room1.setSide(Direction.South, new Wall());
//        room1.setSide(Direction.North, new Wall());
//
//        room2.setSide(Direction.North, new Wall());
//        room2.setSide(Direction.East, new Wall());
//        room2.setSide(Direction.South, new Wall());
//        room2.setSide(Direction.West, door);
//
//        return maze;
//
//    }
}
