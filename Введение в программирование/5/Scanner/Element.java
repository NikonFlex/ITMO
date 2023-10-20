package Scanner;

public class Element {
    private final String element;
    private final int lineNumber;

    public Element(String element, int lineNumber) {
        this.element = element;
        this.lineNumber = lineNumber;
    }

    public String getElement() {
        return element;
    }

    public int getLine() {
        return lineNumber;
    }
}
