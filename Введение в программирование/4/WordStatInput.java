import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class WordStatInput {
    public static void main(String[] args) {
        Word[] stat = calcFileStat(args[0]);
        if (stat == null) {
            System.err.println("stat is null from file " + args[0]);
            return;
        }
        writeStatToFile(args[1], stat);
    }

    private static Word[] calcFileStat(String fileName) {
        StatCalculator stat = new StatCalculator();
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(fileName),
                    "utf8"
                )
            );

            try {
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        return stat.toStatArray();
                    }
                    readWordsFromLine(line, stat);
                }
            } catch (IOException e) {
                System.err.println("can't to read lines in " + fileName + " " + e.getMessage());
                return null;
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println(fileName + " not found " + e.getMessage());
            return null;
        } catch (UnsupportedEncodingException e) {
            System.err.println("utf8 unsupported for " + fileName + " " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.err.println("can't to close reader in " + fileName + " " + e.getMessage());
            return null;
        }
    }

    private static void readWordsFromLine(String line, StatCalculator stat) {
        int number_start = 0;
        for (int i = 0; i < line.length(); i++) {
            if (!isWordChar(line.charAt(i))) {
                if (number_start != i) {
                    stat.addWord(line.substring(number_start, i)); 
                }
                number_start = i + 1;
            }
        }
        if (number_start != line.length()) {
            stat.addWord(line.substring(number_start, line.length()));
        }
    }

    private static boolean isWordChar(char x) {
        return Character.isLetter(x) || Character.getType(x) == Character.DASH_PUNCTUATION || x == '\'' ? true : false;
    }

    private static void writeStatToFile(String fileName, Word[] stats) {
        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(fileName), 
                    "utf8"
                )
            );
            
            try {
                for (Word word : stats) {
                    writer.write(word.getWord() + " " + word.getQuantity() + "\n");
                }
            } catch (IOException e) {
                System.err.println("can't write lines to " + fileName + " " + e.getMessage());
            } finally {
                writer.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println(fileName + " not found " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println("utf8 is unsupported for file " + fileName + " " + e.getMessage());
        } catch (IOException e) {
            System.err.println("can't to close writer in " + fileName + " " + e.getMessage());
        }
    }
}