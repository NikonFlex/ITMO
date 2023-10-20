package Scanner;

import java.io.*;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Scanner {
    private final Reader reader;
    private final int BUFFER_SIZE = 1000;

    private int pointer = 0;
    private String activeReadingField = new String();
    private int line = 1;
    private boolean inputEnded = false;
    private boolean isClosed = false;
    private boolean errorHappened = false;

    public Scanner(InputStream stream) {
        reader = new InputStreamReader(stream);
        readBlock();
    }

    public Scanner(String string) {
        reader = new StringReader(string);
        readBlock();
    }

    public Scanner(File file) throws FileNotFoundException, UnsupportedEncodingException {
        reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(file),
                        "utf8"
                    )
                );
        readBlock();
    }

    public boolean hasNextLine() {
        verifyIsOpen();
        return isInputAllowed();
    }

    public boolean hasNextInt() {
        verifyIsOpen();
        return hasNextElement(new ParsingIntTraits());
    }

    public boolean hasNextWord() {
        verifyIsOpen();
        return hasNextElement(new ParsingWordTraits());
    }

    public boolean hasNextModInt() {
        verifyIsOpen();
        return hasNextElement(new ParsingModIntTraits());
    }

    public boolean hasNext() {
        verifyIsOpen();
        return hasNextElement(new ParsingElementTraits());
    }

    public char currentChar() {
        return activeReadingField.charAt(pointer);
    }

    public void incrementLine() {
        line++;
    }

    public int nextInt() {
        verifyIsOpen();
        try {
            return Integer.parseInt(getNextElement(new ParsingIntTraits()).getElement());
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("there is no int, reader: " + reader.getClass());
        } catch (NumberFormatException e) {
            errorHappened = true;
            throw e;
        }
    }

    public String nextWord() throws NoSuchElementException {
        verifyIsOpen();
        try {
            return getNextElement(new ParsingWordTraits()).getElement();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("there is no lines, reader: " + reader.getClass());
        }
    }

    public Element nextMetaWord() throws NoSuchElementException {
        verifyIsOpen();
        try {
            return getNextElement(new ParsingWordTraits());
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("there is no lines, reader: " + reader.getClass());
        }
    }

    public String next() {
        verifyIsOpen();
        try {
            return getNextElement(new ParsingElementTraits()).getElement();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("there is no elements, reader: " + reader.getClass());
        }
    }

    public boolean isLineSeparator(String str) { return System.lineSeparator().equals(str);}

    public String nextLine() {
        int lineStart = pointer;
        String lineSeparator = new String();
        while (isInputAllowed()) {
            while (pointer < activeReadingField.length() && !isLineSeparator(lineSeparator)) {
                if (System.lineSeparator().contains(String.valueOf(currentChar()))) {
                    lineSeparator += currentChar();
                }
                pointer++;
            }
            if (isLineSeparator(lineSeparator)) {
                return extractElement(lineStart, pointer - 1);
            }
            pointer = activeReadingField.length();
            readBlock();
        }
        return extractElement(lineStart, pointer);
    }

    public String nextModInt() throws NoSuchElementException {
        verifyIsOpen();
        try {
            return getNextElement(new ParsingModIntTraits()).getElement();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("there is no ints, reader: " + reader.getClass());
        }
    }

    public void close() {
        verifyIsOpen();
        try {
            reader.close();
            isClosed = true;
        } catch (IOException e) {
            System.err.println("can't close reader, reader: " + reader.getClass() + " " + e.getMessage());
        }
    }

    public int lineNumber() {
        return line;
    }

    private boolean isInputAllowed() {
        return !(inputEnded && pointer >= activeReadingField.length()) || errorHappened;
    }

    private boolean hasNextElement(ParsingTraits traits) {
        int prevPointerPos = pointer;
        int linePrevValue = line;
        int startIndex = findElementStart(traits);
        pointer = prevPointerPos;
        line = linePrevValue;
        return !(startIndex == -1);
    }

    private Element getNextElement(ParsingTraits traits) {
        int startIndex = findElementStart(traits);
        int line = lineNumber();
        if (startIndex == -1) {
            throw new NoSuchElementException("there is no element, reader: " + reader.getClass());
        }
        int endIndex = findElementEnd(startIndex, traits);
        return new Element(extractElement(startIndex, endIndex), line);
    }

    private int findElementStart(ParsingTraits traits) {
        while (isInputAllowed()) {
            while (pointer < activeReadingField.length()) {
                if (!traits.isSeparator(this, currentChar())) {
                    return pointer;
                }
                pointer++;
            }
            pointer = activeReadingField.length();
            readBlock();
        }
        return -1;
    }

    private int findElementEnd(int startIndex, ParsingTraits traits) {
        while (isInputAllowed()) {
            while (pointer < activeReadingField.length()) {
                if (traits.isSeparator(this, currentChar())) {
                    return pointer;
                }
                pointer++;
            }
            pointer = activeReadingField.length();
            readBlock();
        }
        return pointer;
    }

    private String extractElement(int from, int to) {
        String element = activeReadingField.substring(from, to);
        pointer = to + 1;
        deleteProccesedPartOfField();
        return element;
    }

    private void verifyIsOpen() {
        if (isClosed) {
            throw new IllegalStateException("scanner is closed");
        }
    }

    private void readBlock() {
        try {
            char[] buffer = new char[BUFFER_SIZE];
            int charsRead = reader.read(buffer);
            if (charsRead != BUFFER_SIZE) {
                inputEnded = true;
            }
            if (charsRead == -1) {
                return;
            }
            activeReadingField += new String(Arrays.copyOfRange(buffer, 0, charsRead));
        } catch (IOException e) {
            System.err.println(reader.getClass() + " can't read to buffer " + e.getMessage());
            errorHappened = true;
        }
    }

    private void deleteProccesedPartOfField() {
        activeReadingField = isInputAllowed() ? activeReadingField.substring(pointer, activeReadingField.length()) : "";
        pointer = 0;
    }
}