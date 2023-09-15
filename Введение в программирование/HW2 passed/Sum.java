public class Sum {
    private static Integer safeParseInt(String str) {
        return str.isEmpty() ? 0 : Integer.parseInt(str);
    }

    public static void main(String[] args) {
        int sum = 0;
        for (String arg : args) {
            String current_number_as_string = "";
            for (int i = 0; i < arg.length(); i++) {
                if (Character.isWhitespace(arg.charAt(i))) {
                    sum += safeParseInt(current_number_as_string);
                    current_number_as_string = "";
                } else {
                    current_number_as_string += arg.charAt(i);
                }
            }
            sum += safeParseInt(current_number_as_string);
        }
        // sol 2
        /*for (String arg : args) {
            String[] elems = arg.replaceAll("[^a-zA-Z0-9-+]", " ").split("\\s+");
            for (String elem : elems) {
                if (elem != "") { 
                    sum += Integer.parseInt(elem);
                }
            }
        }*/
        System.out.println(sum);
    }
}