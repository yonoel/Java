package builerPattern;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/20
 */
public class Application {
    public static void main(String[] args) {
        MazeGame game = new MazeGame();
//        BombedMazeFactory factory = new BombedMazeFactory();
        EnchantedMazeFactory factory = new EnchantedMazeFactory();
        EnchantedMazeFactory.register("ench", factory);

        game.createMaze(factory);

        StandardMazeBuilder builder = new StandardMazeBuilder();
        game.createMaze(builder);

        MazePrototypeFactory prototypeFactory =
                new MazePrototypeFactory(new Maze()
                        , new Wall()
                        , new Room(0)
                        , new Door(new Room(1), new Room(2)));
        MazePrototypeFactory bombedPrototype =
                new MazePrototypeFactory(
                        new Maze()
                        , new BombedWall(true)
                        , new RoomWithABomb(0)
                        , new Door(new Room(1), new Room(2))
                );

        game.createMaze(prototypeFactory);
        game.createMaze(bombedPrototype);

    }
}
