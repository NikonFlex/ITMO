import java.util.HashMap;

public class StatCalculator extends HashMap<String, Word> {
    public void addWord(String word, int sequenceNumber) {
        word = word.toLowerCase();
        Word value = putIfAbsent(word, new Word(word, size(), sequenceNumber));
        if (value != null) {
            value.incrementQuantity();
            value.addInclude(sequenceNumber);
        }
    }

    public Word[] toStatArray() {
        Word[] stat = new Word[size()];
        for (Word value : values()) {
            stat[value.getWordNumber()] = value;
        }
        
        return stat;
    }
}