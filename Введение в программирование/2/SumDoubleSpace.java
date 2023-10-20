public class SumDoubleSpace {
    private static double safeParseDouble(String str) {
        return str.strip().isEmpty() ? 0 : Double.parseDouble(str);
    }

    public static void main(String[] args) {
        double sum = 0;
        for (String arg : args) {
            int number_start = 0;
            for (int i = 0; i < arg.length(); i++) {
                if (Character.isSpaceChar(arg.charAt(i))) {
                    sum += safeParseDouble(arg.substring(number_start, i));
                    number_start = i + 1;
                }
            }
            sum += safeParseDouble(arg.substring(number_start, arg.length()));
        }
        System.out.println(sum);
    }
}
