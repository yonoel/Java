package threadSynchronized;

public class FileMock {
    private String[] content;
    private int index;

    public FileMock(int size,int length) {
        this.content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder stringBuilder = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int randomChar = (int) Math.random() * 255;
                stringBuilder.append((char) randomChar);
            }
            this.content[i] = stringBuilder.toString();
        }
        this.index = 0;
    }

    public boolean hasMoreLines() {
        return index < content.length;
    }

    public String getLine() {
        if (hasMoreLines()) {
            System.out.println("Mock: " + (content.length - index));
            return content[index++];
        }
        return "";
    }
}
