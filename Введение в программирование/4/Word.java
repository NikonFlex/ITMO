public class Word {
    private final String word;
    private final int sequenceNumber;
    private int quantity = 1;

    public Word(String word, int sequenceNumber) {
        this.word = word;
        this.sequenceNumber = sequenceNumber;
    }

    public String getWord() {
        return word;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public boolean greaterThan(Word other) {
        if (quantity > other.getQuantity()) {
            return true;
        } else if (quantity == other.getQuantity()) {
            if (sequenceNumber > other.getSequenceNumber()) {
                return true;
            }
            return false;
        }
        return false;
    }
}