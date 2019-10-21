import java.util.*;

public class Operations {
    /**
    Explanation: the alphabets on the right are position commands which computes addition or subtraction on numbers on the left
    E.g. Input (String): 34576 a-bcd
    Expected output (Integer): 3 - 457 = -454
    **/
    public static void main(String[] args) {
        String input = "34576 a-bcd";

        String numbers = input.split(" ")[0];
        String commands = input.split(" ")[1];
        int number1 = 0;
        int number2 = 0;

        int i = 0;
        while (Character.isLetter(commands.charAt(i))) {
            number1 = number1*10 + Character.getNumericValue(numbers.charAt(commands.charAt(i) - 'a'));
            i += 1;
        }

        char operation = commands.charAt(i);
        
        i += 1;
        while (i < commands.length() && Character.isLetter(commands.charAt(i))) {
            number2 = number2*10 + Character.getNumericValue(numbers.charAt(commands.charAt(i) - 'a'));
            i += 1;
        }

        if (operation == '+') {
            System.out.println(number1 + number2);
        } else if (operation == '-') {
            System.out.println(number1 - number2);
        }
    }
}