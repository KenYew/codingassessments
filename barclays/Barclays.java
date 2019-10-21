import java.util.*;

public class Barclays {
    public static int operations(String input) {
        String numbers = input.split(" ")[0];
        String commands = input.split(" ")[1];
        int number1 = 0;
        int number2 = 0;
        int answer = 0;

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
            answer = number1 + number2;
        } else if (operation == '-') {
            answer = number1 - number2;
        }

        return answer;
    }

    public static int checkSum(String input) {
        int sum = 0;
        for (int i = input.length()-1; i >= 0; i--) {
            int digit = Character.getNumericValue(input.charAt(i));

            if (i % 2 == 0) {digit *= 2;}
            if (digit > 9) {digit = 1 + digit%10;}
            sum += digit;
        }
        return sum;
    }

    public static void main(String args[]) {
        // for operations
        String input = "34576 a-bcd";
        System.out.println(operations(input));

        // // for checksum
        // System.out.print("enter a number: ");
        // Scanner sc = new Scanner(System.in);
        // System.out.println(checkSum(sc.nextLine()));
    }
}