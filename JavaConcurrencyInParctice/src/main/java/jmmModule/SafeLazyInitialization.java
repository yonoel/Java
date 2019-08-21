package jmmModule;

public class SafeLazyInitialization {
    private static SafeLazyInitialization resource;

    public synchronized static SafeLazyInitialization getInstance() {
        if (resource == null) {
            resource = new SafeLazyInitialization();
        }
        return resource;
    }
}
