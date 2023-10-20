import java.util.ArrayList;

import Scanner.Scanner;

public class ReverseMinCAbc {
    private static String numbers = "abcdefghij";

    private static int abcToInt(String number) {
        String answer = "";
        if (number.charAt(0) == '-') {
            answer += '-';
            number = number.substring(1, number.length());
        }
        for (int i = 0; i < number.length(); i++) {
            char cur_char = number.charAt(i);
            answer += numbers.indexOf(cur_char);
        }
        return Integer.parseInt(answer);
    }

    private static String intToAbc(int number) {
        String answer = "";
        String number_string = Integer.toString(number);
        if (number < 0) {
            answer += '-';
            number_string = number_string.substring(1, number_string.length());
        }
        for (int i = 0; i < number_string.length(); i++) {
            int cur_number = Integer.parseInt(String.valueOf(number_string.charAt(i)));
            answer += numbers.charAt(cur_number);
        }
        return answer;
    }
    
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<IntList> lines = new ArrayList<IntList>();
        int maxLenSize = 0;
        while (inputScanner.hasNextLine()) {
            var line_ = inputScanner.nextLine();
            Scanner lineScanner = new Scanner(line_);
            IntList line = new IntList();
            while (lineScanner.hasNextModInt()) {
                line.add(abcToInt(lineScanner.nextModInt()));
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
                System.out.print(intToAbc(mins[j]) + " ");
            }
            System.out.println();
        }
    }
}
