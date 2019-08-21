import java.util.*;

public class BlackRock {
    public static String reverse(String a) {
        String answer = "";
        for (int i = a.length()-1; i >= 0; i--) {
            char cr = a.charAt(i);
            if (Character.isDigit(cr) || Character.isLetter(cr)) {
                answer += cr + "-";
            }
        }
        return answer.substring(0,answer.length()-1);
    }

    // round to 2 decimal places
    public static Double round(double val) {
        val = val*100;
        val = Math.round(val);
        return val/100;
    }
    public static String returnChange(double a, double b) {
        if (b < a) return "ERROR";
        else if (b == a) return "Zero";
        else {
            ArrayList<String> values = new ArrayList<String>();
            double change = b-a;
            while (change > 0) {
                if (change >= 50) {
                    values.add("FIFTY");
                    change=round(change-50);
                }else if (change >= 20) {
                    values.add("TWENTY");
                    change=round(change-20);
                } else if (change >= 10) {
                    values.add("TEN");
                    change=round(change-10);
                } else if (change >= 5) {
                    values.add("FIVE");
                    change=round(change-5);
                } else if (change >= 2) {
                    values.add("TWO");
                    change=round(change-2);
                } else if (change >= 1) {
                    values.add("ONE");
                    change=round(change-1);
                } else if (change >= 0.5) {
                    values.add("HALF DOLLAR");
                    change=round(change-0.50);
                } else if (change >= 0.25) {
                    values.add("QUARTER");
                    change=round(change-0.25);
                } else if (change >= 0.1) {
                    values.add("DIME");
                    change=round(change-0.10);
                } else if (change >= 0.05) {
                    values.add("NICKEL");
                    change=round(change-0.05);
                } else {
                    values.add("PENNY");
                    change=round(change-0.01);
                }
            }
            // remove square brackets at the front and end
            String answer = values.toString().substring(1,values.toString().length()-1);
            // return values that are separated by semi-colon
            return answer;
        }
    }

    public static String buyAndSellStocks(String input) {
        TreeMap<String, Integer> list = new TreeMap<>();

        String[] portfolio = input.split(";")[0].split("\\|");
        String[] benchmark = input.split(";")[1].split("\\|");

        for (int i = 0; i < portfolio.length; i++) {
            String p_name = portfolio[i].split(",")[0] + "," + portfolio[i].split(",")[1];
            int p_num = -Integer.parseInt(portfolio[i].split(",")[2]);

            if (list.containsKey(p_name)) {
                p_num = list.get(p_name) - p_num;
            }
            list.put(p_name, p_num);
        }

        for (int j = 0; j < benchmark.length; j++) {
            String b_name = benchmark[j].split(",")[0] + "," + benchmark[j].split(",")[1];
            int b_num = Integer.parseInt(benchmark[j].split(",")[2]);

            if (list.containsKey(b_name)) {
                b_num = list.get(b_name) + b_num;
            }
            list.put(b_name, b_num);
        }

        String answer = "";
        for (String key: list.keySet()) {
            int diff = list.get(key);
            String info = key + "," + Math.abs(diff) + "\n";
            if (diff > 0) {      answer += "BUY," + info;}
            else if (diff < 0) { answer += "SELL," + info;}
        }

        return answer.substring(0,answer.length()-1);
    }

    public static void main(String args[]) {
        /* FOR REVERSE AND SUM */
        // System.out.print("please insert string for test: ");
        // String str = new Scanner(System.in).nextLine();
        // System.out.println(reverse(str));

        /* FOR RETURN CHANGE */
        // System.out.print("please insert value of item and cash given (separated by ';'): ");
        // String str = new Scanner(System.in).nextLine();
        // String[] arg = str.split(";");
        // double arg1 = Double.parseDouble(arg[0]);
        // double arg2 = Double.parseDouble(arg[1]);
        // System.out.println(returnChange(arg1, arg2));

        /* FOR BUY AND SELL STOCKS */
        System.out.println(buyAndSellStocks("VODAFONE,STOCK,5|GOOGLE,STOCK,5;VODAFONE,STOCK,3|APPLE,BOND,10"));
    }
}
