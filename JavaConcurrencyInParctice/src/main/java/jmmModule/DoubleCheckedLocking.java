package jmmModule;

public class DoubleCheckedLocking {
    private static DoubleCheckedLocking resource;

    public static DoubleCheckedLocking getInstance() {
        if (resource == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (resource == null)
                    resource = new DoubleCheckedLocking();
            }
        }
        return resource;
    }
}
