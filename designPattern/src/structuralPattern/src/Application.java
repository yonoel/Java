package structuralPattern.src;

public class Application {
    public static void main(String[] args) {
        Cabinet cabinet = new Cabinet();
        Chassis chassis = new Chassis();
        cabinet.add(chassis);
        Bus bus = new Bus();
        bus.add(new Card());
        chassis.add(bus);
        chassis.add(new FloppyDisk());

        Window window = new WindowImpl();
//        TextView view = new TextView();
        new BorderDecorator(new ScrollDecorator());
    }
}
