package jmmModule;

public class UnsafeLazyInitialization {
    private static UnsafeLazyInitialization resource;

    public static UnsafeLazyInitialization getInstance() {
        if (resource == null) {
            resource = new UnsafeLazyInitialization();
        }
        return resource;
    }
}
