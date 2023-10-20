import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Scanner.Element;
import Scanner.Scanner;

public class WsppSortedPosition {
    public static void main(String[] args) {
        ModWord[] stat = calcFileStat(args[0]);
        if (stat == null) {
            System.err.println("stat is null from file " + args[0]);
            return;
        }
        writeStatToFile(args[1], stat);
    }

    private static ModWord[] calcFileStat(String fileName) {
        ModStatCalculator stat = new ModStatCalculator();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            
            List<Element> words = new ArrayList<Element>();
            int currentline = 1;
            while (scanner.hasNextWord()) {
                Element word = scanner.nextMetaWord();
                if (word.getLine() == currentline) {
                    words.add(word);
                } else {
                    for (int i = 0; i < words.size(); i++) {
                        stat.addWord(words.get(i).getElement(), words.get(i).getLine(), words.size() - i);
                    }
                    words.clear();
                    words.add(word);
                    currentline = word.getLine();
                }
            }
            if (words.size() > 0) {
                for (int i = 0; i < words.size(); i++) {
                    stat.addWord(words.get(i).getElement(), words.get(i).getLine(), words.size() - i);
                }
            }
            scanner.close();
            return stat.toStatArrayForMod();
        } catch (FileNotFoundException e) {
            System.err.println(fileName + " not found " + e.getMessage());
            return null;
        } catch (UnsupportedEncodingException e) {
            System.err.println("utf8 unsupported for " + fileName + " " + e.getMessage());
            return null;
        }
    }

    private static void writeStatToFile(String fileName, ModWord[] stats) {
        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(fileName), 
                    "utf8"
                )
            );
            
            try {
                for (ModWord word : stats) {
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