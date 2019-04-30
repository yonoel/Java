package structuralPattern;

public abstract class Glyph {
    abstract void draw(Window window,GlyphContent content);
    abstract void setFont(Font font,GlyphContent content);
    abstract Font getFont(GlyphContent content);
    abstract void first(GlyphContent content);
    abstract void next(GlyphContent content);
    abstract boolean isDone(GlyphContent content);
    abstract Glyph current(GlyphContent content);

    abstract void insert(Glyph glyph,GlyphContent content);
    abstract void remove(GlyphContent content);
}
