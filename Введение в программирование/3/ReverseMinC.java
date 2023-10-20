import java.util.ArrayList;
import java.util.Scanner;

public class ReverseMinC {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<IntList> lines = new ArrayList<IntList>();
        int maxLenSize = 0;
        while (inputScanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(inputScanner.nextLine());
            IntList line = new IntList();
            while (lineScanner.hasNextInt()) {
                line.add(lineScanner.nextInt());
                maxLenSize = Math.max(maxLenSize, line.size());
            }
            lines.add(line);
            lineScanner.close();
        }
        inputScanner.close();

        int[] mins = new int[maxLenSize];
        for (int i = 0; i < mins.length; i++) {
            mins[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).size(); j++) {
                mins[j] = Math.min(lines.get(i).get(j), mins[j]);
                System.out.print(mins[j] + " ");
            }
            System.out.println();
        }
    }
}