package structuralPattern.src;

public class Proxy implements Subject {
    // 静态代理，实现同一个接口，实例作为局部变量
    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void doRequest() {
        System.out.println(" i'm proxy");
        this.subject.doRequest();

    }
}
