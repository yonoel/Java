package structuralPattern;

public abstract class Decorator extends VisualComponent {
    public VisualComponent visualComponent;

    public Decorator() {
    }

    public Decorator(VisualComponent visualComponent) {
        this.visualComponent = visualComponent;
    }

    @Override
    void draw() {
        visualComponent.draw();
    }

    @Override
    void resize() {
        visualComponent.resize();
    }
}
