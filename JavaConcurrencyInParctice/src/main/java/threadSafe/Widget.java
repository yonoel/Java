package threadSafe;

public class Widget {
    public synchronized void doSomething() {

    }

    public class LoggingWidget extends Widget{
        @Override
        public synchronized void doSomething() {
            System.out.println(toString()+":doSomething");
            super.doSomething();
        }
    }
}
