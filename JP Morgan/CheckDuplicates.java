import java.util.*;

public class CheckDuplicates {
    public static String sumReverse(String num) {
        String result = "";
        for (int i = num.length()-1; i >= 0; i--) {
            char c = num.charAt(i);
            result += Character.toString(c);
        }
        return Integer.toString(Integer.parseInt(num) + Integer.parseInt(result));
    }

    public static boolean duplicate(String num) {
        TreeSet<Character> set = new TreeSet<>();
        for (int i = 0; i < num.length(); i++) {
            set.add(num.charAt(i));
        }
        if (set.size() == num.length()) {return false;}
        return true;
    }

    public static void main(String args[]) {
        String number = sumReverse("122");
        int count = 1;
        while (duplicate(number)) {
            count += 1;
            number = sumReverse(number);
        }
        System.out.print("number: ");
        System.out.println(number);
        System.out.print("count: ");
        System.out.println(count);
    }
}