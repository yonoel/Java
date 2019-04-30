package structuralPattern.src;
// 外部状态的存储库
public abstract class GlyphContent {
    int index;
    abstract void next(int step);
    abstract void insert(int quantity);
    abstract Font getFont();
    abstract void setFont(Font font,int span);
}
