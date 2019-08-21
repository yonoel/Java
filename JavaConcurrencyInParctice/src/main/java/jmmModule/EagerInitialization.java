package jmmModule;

public class EagerInitialization {
    private static EagerInitialization resource = new EagerInitialization();

    public static EagerInitialization getInstance() {
        return resource;
    }
}
