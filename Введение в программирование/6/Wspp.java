import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import Scanner.Scanner;

public class Wspp {
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
        int sequenceNumber = 0;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner stringScanner = new Scanner(line);
                while (stringScanner.hasNextWord()) {
                    sequenceNumber++;
                    stat.addWord(stringScanner.nextWord(), sequenceNumber);
                }
            }
            scanner.close();
            return stat.toStatArray();
        } catch (FileNotFoundException e) {
            System.err.println(fileName + " not found " + e.getMessage());
            return null;
        } catch (UnsupportedEncodingException e) {
            System.err.println("utf8 unsupported for " + fileName + " " + e.getMessage());
            return null;
        }
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
                    writer.write(word.toString());
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