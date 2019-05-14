package behaviorPattern.src;

public class ConcreteHandler1 extends Handler{
    @Override
    protected void echo() {
        System.out.println("handler1 处理请求啦");
    }
}
