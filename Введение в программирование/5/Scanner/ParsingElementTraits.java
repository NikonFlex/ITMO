package Scanner;

public class ParsingElementTraits implements ParsingTraits {
    @Override
    public boolean isSeparator(Scanner scanner, char ch) {
        if (System.lineSeparator().contains(String.valueOf(ch))) {
            scanner.incrementLine();
        }
        return Character.isWhitespace(ch);
    }
}
