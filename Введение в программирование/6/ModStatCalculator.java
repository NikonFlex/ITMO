import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ModStatCalculator extends HashMap<String, ModWord> {
    public void addWord(String word, int lineNumber, int sequenceNumber) {
        word = word.toLowerCase();
        ModWord value = putIfAbsent(word, new ModWord(word, sequenceNumber, lineNumber));
        if (value != null) {
            value.incrementQuantity();
            value.addInclude(lineNumber + ":" + sequenceNumber);
        }
    }
    
    public List<ModWord> toStatArray() {
        List<ModWord> stat = new ArrayList<ModWord>(values().size());
        for (ModWord value : values()) {
            stat.add(value);
        }
        
        return stat;
    }

    public ModWord[] toStatArrayForMod() {
        String[] keys_sorted = keySet().toArray(new String[values().size()]);
        Arrays.sort(keys_sorted);

        List<ModWord> stat = new ArrayList<ModWord>(values().size());
        for (String key : keys_sorted) {
            stat.add(get(key));
        }
        
        return stat.toArray(new ModWord[stat.size()]);
    }
}