import java.util.*;

public class SpecificSumPair {
    public static void brute(int[] list, int target) {
        String ans = "";
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (list[i] + list[j] == target) {
                    ans = Integer.toString(list[i]) + "," + Integer.toString(list[j]);
                    System.out.println(ans);
                    return;
                }
            }
        }
    }

    public static void dict(int[] list, int target) {
        String ans = "";
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < list.length; i++) {
            if (map.containsKey(list[i])) {
                System.out.println(Integer.toString(map.get(list[i])) + "," + Integer.toString(list[i]));
                return;
            } else {
                map.put(target-list[i], list[i]);
            }
        }
    }

    public static void efficient(int[] list, int target) {
        String ans = "";
        int i = 0;
        int j = list.length-1;

        while (i <= j) {
            if (list[i] + list[j] == target) {
                System.out.println(Integer.toString(list[i]) + "," + Integer.toString(list[j]));
                return;
            } else if (list[i] + list[j] < target) {
                i += 1;
            } else if (list[i] + list[j] > target) {
                j -= 1;
            }
        }
    }

    public static void main(String args[]) {
        brute(new int[]{0,1,4,6,7}, 10);
        dict(new int[]{0,1,4,6,7}, 10);
        efficient(new int[]{0,1,4,6,7}, 10);
    }
}