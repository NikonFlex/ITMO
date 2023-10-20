public class Word {
    private final String word;
    private final int wordNumber;
    private int quantity = 1;
    private MyIntList includeIndexes = new MyIntList();

    public Word(String word, int wordNumber, int sequenceNumber) {
        this.word = word;
        this.wordNumber = wordNumber;
        this.includeIndexes.add(sequenceNumber);
    }

    public String getWord() {
        return word;
    }

    public int getWordNumber() {
        return wordNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public int[] getIncludeIndexes() {
        return includeIndexes.toArrayCopy();
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void addInclude(int includeIndex) {
        includeIndexes.add(includeIndex);
    }

    @Override
    public String toString() {
        String representation = getWord() + " " + getQuantity() + " ";
        for (int i = 0; i < includeIndexes.size(); i++) {
            representation += includeIndexes.get(i);
            if (i + 1 < includeIndexes.size()) {
                representation += " ";
            }   
        }
        representation += "\n";
        return representation;
    }
}