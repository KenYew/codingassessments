import java.util.*;

public class Checksum {
    /**
    Explanation: After taking in a number (in string format):
    - Divide it up into respective digits
    - For every even digit from the right, multiply them by 2
    - If the resulting product (by multiplying them by 2) is double digit, take the sum of the 2 digits (instead of the number)
    - Add all the digits from right to left and that is the "checksum"
    
    E.g. Input (String): 5476
    Expected output (Integer): 6 + (1 + 4) + 4 + (1 + 0) = 16
    **/
    public static void main(String args[]) {
        System.out.print("enter a number: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int sum = 0;

        for (int i = input.length()-1; i >= 0; i--) {
            int digit = Character.getNumericValue(input.charAt(i));

            if (i % 2 == 0) {digit *= 2;}
            if (digit > 9) {digit = 1 + digit%10;}
            sum += digit;
        }

        System.out.println(sum);
    }
}