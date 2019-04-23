package builerPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: Java
 * @description:
 * @author: Qian Yi Zhen
 * @create: 2019/04/20
 */
public class MazeFactory {
    // singleton
    private static MazeFactory staticfactory;
    private static Map<String, MazeFactory> registry;

    // 当MazeFactory 存在多个子类时我们可以根据某些条件来判断
    public static MazeFactory getInstance() {
        if (staticfactory != null) {
            Object env = System.getProperty("env");
            // balabala
            return staticfactory;
        }
        MazeFactory factory = new MazeFactory();
        staticfactory = factory;
        return staticfactory;
    }

    // 还有一种更加灵活的方式来保持单例
    public static void register(String partName,MazeFactory factory) {
        // 其实就是把上面的instance拆了,但是便于调用
        if (registry == null) registry = new HashMap<String, MazeFactory>();
        registry.put(partName,factory);
    }

    public static MazeFactory lookup(String partName) {
        return registry.values().stream().filter(factory -> factory.partName == partName).findFirst().orElseGet(MazeFactory::new);
    }

    // 一个键，指向了需要哪一种工厂
    private String partName;


    protected MazeFactory(String partName) {
        this.partName = partName;
    }

    protected MazeFactory() {
    }

    public Maze makeMaze() {
        return new Maze();
    }

    public Wall makeWall() {
        return new Wall();
    }

    public Room makeRoom(int n) {
        return new Room(n);
    }

    public Door makeDoor(Room room1, Room room2) {
        return new Door(room1, room2);
    }
}
