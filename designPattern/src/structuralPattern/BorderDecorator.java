package structuralPattern;

public class BorderDecorator extends Decorator {
    public BorderDecorator(VisualComponent visualComponent) {
        super(visualComponent);
    }

    public int borderSize;

    private void drawBorder(int size) {

    }

    @Override
    void draw() {
        super.draw();
        this.drawBorder(borderSize);
    }
}
