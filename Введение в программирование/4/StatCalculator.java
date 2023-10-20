import java.util.HashMap;

public class StatCalculator extends HashMap<String, Word> {
    public void addWord(String word) {
        word = word.toLowerCase();
        if (this.containsKey(word)) {
            this.get(word).incrementQuantity();
        } else {
            this.put(word, new Word(word, this.size()));
        }
    }

    public Word[] toStatArrayForMod() {
        return sortArray(toStatArray());
    }

    public Word[] toStatArray() {
        Word[] stat = new Word[this.size()];
        for (Word value : this.values()) {
            stat[value.getSequenceNumber()] = value;
        }
        
        return stat;
    }

    private Word[] sortArray(Word[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = words.length - 1; j > i; j--) {
                if (words[j - 1].greaterThan(words[j])) {
                    Word tmp = words[j - 1];
                    words[j - 1] = words[j];
                    words[j] = tmp;
                }
            }
        }
        return words;
    }
}