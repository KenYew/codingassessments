import java.util.*;

public class Test {
    public static int reverseAndSum(int a) {
        String aString = Integer.toString(a);
        StringBuilder sb = new StringBuilder(aString);
        String bString = sb.reverse().toString();
        int b = Integer.parseInt(bString);
        return a + b;
    }

    public static String setInequality(int a, int b) {
        if (a < b) return "<";
        else if (a > b) return ">";
        else return "=";
    }

    // round to 2 decimal places
    public static Double round(double val) {
        val = val*100;
        val = Math.round(val);
        return val/100;
    }
    public static String returnChange(double a, double b) {
        if (b < a) return "ERROR";
        else if (b == a) return "ZERO";
        else {
            ArrayList<String> values = new ArrayList<String>();
            double change = b-a;
            while (change > 0) {
                if (change >= 100) {values.add("ONE HUNDRED");change=round(change-100);}
                else if (change >= 50) {values.add("FIFTY");change=round(change-50);}
                else if (change >= 20) {values.add("TWENTY");change=round(change-20);}
                else if (change >= 10) {values.add("TEN");change=round(change-10);}
                else if (change >= 5) {values.add("FIVE");change=round(change-5);}
                else if (change >= 2) {values.add("TWO");change=round(change-2);}
                else if (change >= 1) {values.add("ONE");change=round(change-1);}
                else if (change >= 0.5) {values.add("HALF DOLLAR");change=round(change-0.50);}
                else if (change >= 0.25) {values.add("QUARTER");change=round(change-0.25);}
                else if (change >= 0.1) {values.add("DIME");change=round(change-0.10);}
                else if (change >= 0.05) {values.add("NICKEL");change=round(change-0.05);}
                else {values.add("PENNY");change=round(change-0.01);}
            }
            // remove square brackets at the front and end
            String answer = values.toString().substring(1,values.toString().length()-1);
            // return values that are separated by semi-colon
            return answer.replace(", ", ";");
        }
    }

    public static int findingWorstStock(double[][] stocks) {
        if (stocks.length == 0) return 0;
        double min_rate = 100;
        int index = 0;
        for (int i=0; i<stocks.length; i++) {
            double rate = (stocks[i][2]-stocks[i][1])/stocks[i][1];
            if (rate < min_rate) {
                min_rate = rate;
                index = (int)stocks[i][0];
            }
        }
        return index;
    }

    public static String creditCheckSum(String[] cards) {
        String results = "";
        for (int i=0; i<cards.length; i++) {
            // reverse card number
            StringBuilder sb = new StringBuilder(cards[i]);
            String card_rev = sb.reverse().toString();

            long oddsum = 0;
            long evensum = 0;
            for (int j=0; j<card_rev.length(); j++) {
                // sum all odd digits
                if (j % 2 == 0) {
                    oddsum += Character.getNumericValue(card_rev.charAt(j));
                }
                // multiply even digits by 2
                // if the result is double digit, sum the individual digits instead
                // or else sum the digit
                else {
                    int digit = Character.getNumericValue(card_rev.charAt(j))*2;
                    if (digit > 9) {evensum += (Math.round(digit/10) + digit%10);}
                    else {evensum += digit;}
                }
            }
            // if sum of oddsum and evensum is divisible by 10, credit card cleared
            if ((oddsum + evensum) % 10 == 0) {results += "YES\n";}
            else {results += "NO\n";}
        }
        return results;
    }

    public static int studyTip(int[] tm, int t) {
        Arrays.sort(tm);
        int sum = 0;
        int count = 0;

        for (int i = 0; i < tm.length; i++) {
            if (sum + tm[i] <= t) {
                sum += tm[i];
                count += 1;
            }
        }
        return count;
    }

    public static String checkTopple(int n, int[] height, int[] position) {
        boolean left = true;
        boolean right = true;
        
        // tip left
        for (int i = 0; i < n-1; i++) {
            // current server cannot topple next server
            if (position[i]+height[i] < position[i+1]) {
                left = false;
                int j = i;
                while (j > 0) {
                    j -= 1;
                    // check if any server before can topple next server
                    if (position[j]+height[j] >= position[i+1]) {left = true; break;}
                }
                if (left==false) {break;}
            }
        }
        
        //tip right
        for (int i = n-1; i > 0; i--) {
            // current server cannot topple next server
            if (position[i]-height[i] > position[i-1]) {
                right = false;
                int j = i;
                while (j < n-1) {
                    j += 1;
                    // check if any server before can topple next server
                    if (position[j]-height[j] <= position[i-1]) {right = true; break;}
                }
                if (right==false) {break;}
            }
        }
        
        if (left & right) {return "BOTH";}
        else if (left) {return "LEFT";}
        else if (right) {return "RIGHT";}
        return "NONE";
    }

    public static int winningHands(int m, int x, int[] a) {
        // m: modulo
        // x: target
        // a: playing hands

        int count = 0;
        for (int i = 0; i < a.length; i++) {
            List<Integer> hands = new ArrayList<Integer>();
            
            for (int j = i; j < a.length; j++) {
                hands.add(a[j] % m);
                int product = 1;
                for (int hand = 0; hand < hands.size(); hand++) {
                    product *= hands.get(hand);
                    product %= m;
                }
                if (product == x) {count+=1;}
            }
        }
        return count;
    }

    /*
        Given a 7 digit telephone number, print out all the possible sequences of letters that can represent the given
        telephone number. Note that in a standard phone keypad 0 and 1 do not have any letters associated with them.
        All 0's and 1's in the telephone number should be retained in the sequence of letters. You may use the
        following mapping between numbers and characters 0=>0, 1=>1, 2=>abc, 3=>def, 4=>ghi, 5=>jkl, 6=>mno, 7=>pqrs,
        8=>tuv, 9=>wxyz
        Input:
        Your program should read lines from standard input. Each line contains a 7 digit telephone number.
        Output:
        Print to standard output the words that can produce the telephone number, alphabetically sorted and comma
        delimited.
    */
    public static String phoneNumber(String numbers) {
        HashMap<Character, ArrayList<Character>> map = new HashMap<>();
        map.put('0', new ArrayList<Character>(Arrays.asList('0', '0', '0', '0')));
        map.put('1', new ArrayList<Character>(Arrays.asList('1', '1', '1', '1')));
        map.put('2', new ArrayList<Character>(Arrays.asList('a', 'b', 'c', 'c')));
        map.put('3', new ArrayList<Character>(Arrays.asList('d', 'e', 'f', 'f')));
        map.put('4', new ArrayList<Character>(Arrays.asList('g', 'h', 'i', 'i')));
        map.put('5', new ArrayList<Character>(Arrays.asList('j', 'k', 'l', 'l')));
        map.put('6', new ArrayList<Character>(Arrays.asList('m', 'n', 'o', 'o')));
        map.put('7', new ArrayList<Character>(Arrays.asList('p', 'q', 'r', 's')));
        map.put('8', new ArrayList<Character>(Arrays.asList('t', 'u', 'v', 'v')));
        map.put('9', new ArrayList<Character>(Arrays.asList('w', 'x', 'y', 'z')));

        TreeSet<String> results = new TreeSet<>();
        for (int set = 0; set < 4; set++) {
            String letters = "";
            for (int i = 0; i < numbers.length(); i++) {
                char digit = numbers.charAt(i);
                letters += map.get(digit).get(set);
            }
            results.add(letters);
        }
        System.out.println(results);
        return "num";
    }

        
    /*
        Your friend John uses a lot of emoticons when you talk to him on Messenger. In addition to being a person who
        likes to express himself through emoticons, he hates unbalanced parenthesis so much that it makes him go :(. 
        Sometimes he puts emoticons within parentheses, and you find it hard to tell if a parenthesis really is a
        parenthesis or part of an emoticon. A message has balanced parentheses if it consists of one of the following:
        - An empty string "" 
        - One or more of the following characters: 'a' to 'z', ' ' (a space) or ':' (a colon) 
        - An open parenthesis '(', followed by a message with balanced parentheses, followed by a close parenthesis ')'. 
        - A message with balanced parentheses followed by another message with balanced parentheses. 
        - A smiley face ":)" or a frowny face ":(" 
        Write a program that determines if there is a way to interpret his message while leaving the parentheses balanced.
        Input:
        Your program should read lines from standard input. Each line contains a message that you got from John.
        Output:
        Print out the string "YES"/"NO" (all quotes for clarity only) stating whether or not it is possible that the
        message had balanced parentheses.
    */
    public static String parenthesis(int num) {
        return "YES";
    }

    public static void main(String args[]) {
        /* FOR REVERSE AND SUM */
        // String str = new Scanner(System.in).nextLine();
        // System.out.println(reverseAndSum(Integer.parseInt(str)));

        /* FOR SET INEQUALITY */
        // String str = new Scanner(System.in).nextLine();
        // String[] arg = str.split(" ");
        // int arg1 = Integer.parseInt(arg[0]);
        // int arg2 = Integer.parseInt(arg[1]);
        // System.out.println(setInequality(arg1, arg2));

        /* FOR RETURN CHANGE */
        // String str = new Scanner(System.in).nextLine();
        // String[] arg = str.split(";");
        // double arg1 = Double.parseDouble(arg[0]);
        // double arg2 = Double.parseDouble(arg[1]);
        // System.out.println(returnChange(arg1, arg2));

        /* FOR FINDING WORST STOCK */
        // double[][] values = new double[][]{{1200,100,105},{1400,50,55}};
        // System.out.println(findingWorstStock(values));

        /* FOR CREDIT CHECKSUM */
        // System.out.println(creditCheckSum(new String[]{
        //     "9795526789839145",
        //     "2861747566959730",
        //     "4498854833783559",
        //     "6301982162016598",
        //     "1131197164065322"
        // }));

        /* FOR STUDY TIP */
        // System.out.println(studyTip(new int[]{1,1,3,2}, 4));

        /* FOR CHECK TOPPLE */
        // System.out.println(checkTopple(
        //     5, new int[]{7,1,1,1,1}, new int[]{1,2,3,4,8}
        // ));

        /* FOR WINNING HANDS */
        // System.out.println(winningHands(3, 2, new int[]{2,2,2}));

        /* FOR PHONE NUMBER */
        System.out.println(phoneNumber("2243372"));

        /* FOR PARENTHESIS */
        // System.out.println(parenthesis(3));
    }
}
