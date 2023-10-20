package Scanner;

public class ParsingModIntTraits implements ParsingTraits {
    private String numbers = "abcdefghij";

    @Override
    public boolean isSeparator(Scanner scanner, char ch) {
        if (System.lineSeparator().contains(String.valueOf(ch))) {
            scanner.incrementLine();
        }
        return !(ch =='-' || numbers.contains(String.valueOf(ch)));
    }
}