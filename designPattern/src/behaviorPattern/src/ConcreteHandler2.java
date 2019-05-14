package behaviorPattern.src;

public class ConcreteHandler2 extends Handler {
    @Override
    protected void echo() {
        System.out.println("handler2 处理请求啦");
    }
}
