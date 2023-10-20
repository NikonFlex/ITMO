package Scanner;

public class ParsingWordTraits implements ParsingTraits {
    @Override
    public boolean isSeparator(Scanner scanner, char ch) {
        if (System.lineSeparator().contains(String.valueOf(ch))) {
            scanner.incrementLine();
        }
        return !(Character.isLetter(ch) || Character.getType(ch) == Character.DASH_PUNCTUATION || ch == '\'');
    }
}
