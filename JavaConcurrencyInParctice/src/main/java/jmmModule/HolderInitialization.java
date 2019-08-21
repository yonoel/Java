package jmmModule;

public class HolderInitialization {
    private static class HolderInitializationHolder{
        public static HolderInitialization  resource = new HolderInitialization();
    }
    public static HolderInitialization getInstance() {
        return HolderInitializationHolder.resource;
    }
}
