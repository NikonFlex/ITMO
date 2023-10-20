import java.util.ArrayList;

import Scanner.Scanner;

public class Reverse {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<IntList> lines = new ArrayList<IntList>();
        while (inputScanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(inputScanner.nextLine());
            IntList line = new IntList();
            while (lineScanner.hasNextInt()) {
                line.add(lineScanner.nextInt());
            }
            lines.add(line);
            lineScanner.close();
        }
        inputScanner.close();

        for (int i = lines.size() - 1; i >= 0; i--) {
            for (int j = lines.get(i).size() - 1; j >= 0; j--) {
                System.out.print(lines.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}