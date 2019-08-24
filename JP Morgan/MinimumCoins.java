import java.util.*;

public class MinimumCoins {
    public static int count(int num) {
        int[] list = new int[]{4,2,1};
        int count = 0;
        while (num > 0) {
            for (int i: list) {
                if (num >= i) {
                    // find the largest value of coins to satisfy number
                    num -= i;
                    count += 1;
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String args[]) {
        System.out.println(count(20));
    }
}