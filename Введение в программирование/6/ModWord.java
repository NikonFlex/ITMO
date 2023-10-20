import java.util.ArrayList;

public class ModWord {
    private final String word;
    private ArrayList<String> includes = new ArrayList<String>();
    private int quantity = 1;

    public ModWord(String word, int wordNumber, int lineNumber) {
        this.word = word;
        addInclude(lineNumber + ":" + wordNumber);
    }

    public String getWord() {
        return word;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void addInclude(String newInclude) {
        includes.add(newInclude);
    }

    public String toString() {
        String representation = getWord() + " " + getQuantity() + " ";
        for (int i = 0; i < includes.size(); i++) {
            representation += includes.get(i);
            if (i + 1 < includes.size()) {
                representation += " ";
            }   
        }
        representation += "\n";
        return representation;
    }
}