import java.util.*;

public class HappyNumbers {
    public static boolean determine(int num) {
        TreeSet<Integer> set = new TreeSet<>();
        while (num != 1) {
            System.out.println(set);
            if (set.contains(num)) {return false;}
            else {
                set.add(num);
                String num_str = Integer.toString(num);
                int new_num = 0;
                for (int i = 0; i < num_str.length(); i++) {
                    int digit = Character.getNumericValue(num_str.charAt(i));
                    new_num += digit*digit;
                }
                num = new_num;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        System.out.println(determine(34));
    }
}