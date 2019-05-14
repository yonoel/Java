package behaviorPattern.src;

public abstract class Handler {
    protected final int NO_HELP_TOPIC = -1;
    private int topic;
    private Handler nextHandler;

    public void setNextHandler(Handler nextHandler, int topic) {
        // 需要处理链里不能有重复
        this.nextHandler = nextHandler;
        this.topic = topic;
    }

    public boolean hasHelp() {
        return this.topic != NO_HELP_TOPIC;
    }

    public void doRequest() {
        if (!hasHelp()) {
            System.out.println("请求转发");
            this.nextHandler.doRequest();
        } else echo();
    }

    protected abstract void echo();
}
