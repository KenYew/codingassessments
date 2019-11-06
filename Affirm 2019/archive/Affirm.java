import java.util.*;

public class Affirm {
    public static ArrayList<String> amounts_to_return_users(String[] transaction_activities) {
        // sort array according to date
        String[] sorted_t = new String[transaction_activities.length];
        for (int i = 0; i < transaction_activities.length; i++) {
            String card = transaction_activities[i].split(",")[0];
            String command = transaction_activities[i].split(",")[1];
            String value = transaction_activities[i].split(",")[2];
            String timestamp = transaction_activities[i].split(",")[3];

            sorted_t[i] = card+","+timestamp+","+command+","+value;
        }
        Arrays.sort(sorted_t);

        HashMap<String, int[]> cards = new HashMap<>();
        // [0] => Initial funding
        // [1] => Affirm's stash
        // [2] => User's stash
        // [3] => Whether purchase has been made
        for (int i = 0; i < sorted_t.length; i++) {
            String card = sorted_t[i].split(",")[0];
            String command = sorted_t[i].split(",")[2];
            int value = Integer.parseInt(sorted_t[i].split(",")[3]);

            // CREATION
            if (command.equals("Creation")) {
                cards.put(card, new int[]{value,value,0,0});
            }
            // RETURN
            else if (command.equals("Load") && (cards.get(card)[3] == 1)) {
                if (value <= cards.get(card)[0]-cards.get(card)[1]) {
                    cards.get(card)[1] += value;
                } else {
                    cards.get(card)[2] += value-(cards.get(card)[0]-cards.get(card)[1]);
                    cards.get(card)[1] = cards.get(card)[0];
                }
            }
            // LOAD
            else if (command.equals("Load") && (cards.get(card)[3] == 0)) {
                cards.get(card)[2] += value;
            }
            // PURCHASE
            else if (command.equals("Purchase")) {
                cards.get(card)[3] = 1;
                if (cards.get(card)[1] >= value) {
                    cards.get(card)[1] -= value;
                } else {
                    cards.get(card)[2] -= (value-cards.get(card)[1]);
                    cards.get(card)[1] = 0;
                }
            }
        }

        ArrayList<String> answer = new ArrayList<>();
        for (String key: cards.keySet()) {
            if ((cards.get(key)[2] > 0) && (cards.get(key)[3] > 0)) {
                answer.add(key + "**" + cards.get(key)[2]);
            }
        }
        return answer;
    }

    public static ArrayList<String> amounts_to_return_users_for_date(String curr_date, String[] date_and_transaction_activities) {
        // sort array according to date
        String[] sorted_t = new String[date_and_transaction_activities.length];
        for (int i = 0; i < date_and_transaction_activities.length; i++) {
            String card = date_and_transaction_activities[i].split(",")[0];
            String command = date_and_transaction_activities[i].split(",")[1];
            String value = date_and_transaction_activities[i].split(",")[2];
            String timestamp = date_and_transaction_activities[i].split(",")[3];

            sorted_t[i] = card+","+timestamp+","+command+","+value;
        }
        Arrays.sort(sorted_t);

        HashMap<String, int[]> cards = new HashMap<>();
        HashMap<String, String> card_dates = new HashMap<>();
        // [0] => Initial funding
        // [1] => Affirm's stash
        // [2] => User's stash
        // [3] => Whether purchase has been made
        for (int i = 0; i < sorted_t.length; i++) {
            String card = sorted_t[i].split(",")[0];
            String date_ = sorted_t[i].split(",")[1].split(" ")[0];
            String time_ = sorted_t[i].split(",")[1].split(" ")[1];
            String command = sorted_t[i].split(",")[2];
            int value = Integer.parseInt(sorted_t[i].split(",")[3]);

            if ((cards.get(card) != null) && !card_dates.get(card).equals(date_)) {
                card_dates.put(card, date_);
                if (cards.get(card)[3] == 1) { cards.get(card)[2] = 0; }
            }

            // CREATION
            if (command.equals("Creation")) {
                cards.put(card, new int[]{value,value,0,0});
                card_dates.put(card, date_);
            }
            // RETURN
            else if (command.equals("Load") && (cards.get(card)[3] == 1)) {
                if (value <= cards.get(card)[0]-cards.get(card)[1]) {
                    cards.get(card)[1] += value;
                } else {
                    cards.get(card)[2] += value-(cards.get(card)[0]-cards.get(card)[1]);
                    cards.get(card)[1] = cards.get(card)[0];
                }
            }
            // LOAD
            else if (command.equals("Load") && (cards.get(card)[3] == 0)) {
                cards.get(card)[2] += value;
            }
            // PURCHASE
            else if (command.equals("Purchase")) {
                cards.get(card)[3] = 1;
                if (cards.get(card)[1] >= value) {
                    cards.get(card)[1] -= value;
                } else {
                    cards.get(card)[2] -= (value-cards.get(card)[1]);
                    cards.get(card)[1] = 0;
                }
            }
        }

        ArrayList<String> answer = new ArrayList<>();
        for (String key: cards.keySet()) {
            if ((cards.get(key)[2] > 0) && (cards.get(key)[3] > 0)) {
                answer.add(key + "**" + cards.get(key)[2]);
            }
        }
        return answer;
    }

    public static void main(String args[]) {
        // System.out.println(amounts_to_return_users(new String[]{
        //     "1289,Creation,120000,2019-05-18 05:30:00",
        //     "1289,Load,40000,2019-05-18 05:31:00",
        //     "510,Creation,120000,2019-05-18 02:30:00",
        //     "510,Load,50000,2019-05-18 02:31:00",
        //     "1289,Load,10000,2019-05-18 05:31:30",
        //     "361,Purchase,100000,2019-05-18 06:32:00",
        //     "361,Load,90000,2019-05-18 06:33:00",
        //     "1289,Purchase,150000,2019-05-18 05:32:00",
        //     "1289,Load,140000,2019-05-18 05:34:00",
        //     "510,Purchase,150000,2019-05-18 02:32:00",
        //     "510,Load,10000,2019-05-18 02:33:00",
        //     "510,Load,100000,2019-05-18 02:34:00",
        //     "361,Creation,120000,2019-05-18 06:30:00",
        //     "361,Load,50000,2019-05-18 06:31:00",
        //     "7,Creation,120000,2019-05-18 09:30:00",
        //     "8888,Creation,50000,2019-05-18 15:30:00",
        //     "8888,Load,50000,2019-05-18 15:35:00",
        //     "10,Creation,10000,2019-05-18 14:29:00",
        //     "10,Load,70000,2019-05-18 14:30:00",
        //     "8888,Purchase,100000,2019-05-18 15:40:00",
        //     "8888,Load,50000,2019-05-18 15:47:00"
        // }));

        System.out.println(amounts_to_return_users_for_date("2019-05-19", new String[]{
            "1289,Creation,120000,2019-05-18 05:30:00",
            "1289,Load,50000,2019-05-18 05:31:00",
            "777,Creation,120000,2019-05-18 05:30:00",
            "777,Load,50000,2019-05-18 05:31:00",
            "1289,Purchase,150000,2019-05-18 05:32:00",
            "1289,Load,130000,2019-05-19 05:33:00",
            "868,Creation,120000,2019-05-19 05:30:00",
            "868,Load,40000,2019-05-19 05:31:00",
            "777,Purchase,150000,2019-05-19 05:32:00",
            "777,Load,130000,2019-05-19 05:33:00"
        }));
    }
}