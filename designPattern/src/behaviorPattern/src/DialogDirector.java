package behaviorPattern.src;


public abstract class DialogDirector {
    abstract void showDialog();

    abstract void widgetChanged(Widget widget);

    protected abstract void createWidget();
}
