import java.util.*;
import java.lang.Math;

public class Amazon {
    // practice Q1:
    // There is a colony of 8 cells arranged in a straight line where each day every cell competes with its adjacent cells (neighbours).
    // Each day, for each cell, if its neighbours are both active or both inactive, the cell becomes inactive the next day;
    // otherwise it becomes active the next day
    // An integers value of 1 represents an active cell and value of 0 represents an inactive cell

    // Assumptions:
    // - the two cells at the end have single adjacent cell, so the other adjacent cell can be assumed to be always inactive
    // - even after updating the cell state, consider previous state for updating the state of other cells.
    // Update the cell information of all cells simulataneously

    // input:
    // - array: current state of 8 cells
    // - integer: days to simulate
    // output:
    // - array: state of 8 cells after given days

    public static int[] cellComplete(int[] cells, int days) {
        int[] answer = cells;

        for (int day = 0; day < days; day++) {
            int[] temp = answer;
            System.out.print("day: ");
            System.out.println(day);
            for (int i = 0; i < cells.length; i++) {
                System.out.print(answer[i]);
                // if ((i == 0) || (i == cells.length-1)) {

                // } else {

                // }
            }
            System.out.println("");
        }
        return answer;
    }

    // practice Q2:
    // The Greatest Common Divisor (also called Highest Common Factor) of n numbers is the largets positive integer
    // that divides all numbers without a remainder.
    // Write a method generalisedGCD of class GCD to find the GCD of n positive integers.

    // input:
    // - array arr of positive integers
    // output:
    // - GCD of the array of integers

    public static int generalisedGCD(int arr[]) {
        int counter = 1;

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        return counter;
    }

    // Q1:
    // Amazon prides itself on protecting customers and sellers from fraud. Amazon assigns fraud ratings to actions taken on the website.
    // We scan these rating values to try and determine when to accept a given request. Fraud ratings are assigned in a complementary manner.
    // When two ratings add up to a certain value, it increases the risk that the request may be fraudulent.
    // Write an algorithm to find the number of distinct pair of ratings from an array of all fraud ratings for the request that sum to a target value

    // input:
    //   numCount: an integer representing the number of rating
    //   ratingValues: a list of integers representing the value of fraud ratings
    //   target: an integer representing the target value
    // output:
    //   Return an integer representing the number of distinct pairs of ratings from a list of fraud ratings for the request that sum to a target value

    public static int countPairs(int numCount, List<Integer> ratingValues, int target) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numCount; i++) {
            int value = ratingValues.get(i);
            if (map.get(target-value) != null) {
                count++;
            } else {
                map.put(value, target-value);
            }
        }

        return count;
    }

    // Q2:
    // AMZN525 has a "connected" network of data servers, i.e. every server can communicate with the rest of the network.
    // The data servers are connected by point-to-point cables to establish "critical" or "non-critical" connections.
    // A connection is considered "critical" if its removal results in a disconnected network of servers.
    // Write a method that returns the critical connections in AMZN525

    // input:
    //   numOfServers: an integer representing the number of servers in the data center
    //   numOfConnections: an integer representing the number of connections between the servers
    //   connections: a list of pairs of integers representing the connections between 2 servers
    // output:
    //   Return a list of integer pairs representing the critical connections. Output an empty list

    // Constraints:
    //   0 <= numOfServers <= 100,000
    //   0 <= numOfConnections <= 100,000
    //   1 <= connections[i][j] <= numOfServers
    //   0 <= i < numOfConnections
    //   0 <= j < 2

    // example:
    // input: 5, 5, [[1,2],[1,3],[3,4],[1,4],[4,5]]
    // output: [[1,2],[4,5]]

    public static void visualise(List<Integer>[] graph) {
        // for visualising only
        for (int i = 0; i < graph.length; i++) {
            System.out.print("From " + Integer.toString(i) + " : To ");
            for (int j = 0; j < graph[i].size(); j++) {
                System.out.print(graph[i].get(j));
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    static int time = 0;
    public static void dfs(int u, int[] low, int[] disc, List<Integer>[] graph, List<List<Integer>> res, int pre) {
        disc[u] = low[u] = ++time;
        
        for (int i = 0; i < graph[u].size(); i++) {
            int v = graph[u].get(i);

            if (v == pre) { continue; }
            if (disc[v] == -1) {
                dfs(v, low, disc, graph, res, u);

                low[u] = Math.min(low[u], low[v]);
                
                if (low[v] > disc[u]) {
                    res.add(Arrays.asList(u,v));
                }
            } else {
                low[u] = Math.min(low[u], disc[v]);
            }

        }
    }

    public static List<PairInt> criticalConnections(int numOfServers, int numOfConnections, List<PairInt> connections) {
        int[] disc = new int[numOfServers];
        int[] low = new int[numOfServers];

        List<Integer>[] graph = new ArrayList[numOfServers];
        List<List<Integer>> res = new ArrayList<>();

        Arrays.fill(disc, -1);

        for (int i = 0; i < numOfServers; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < connections.size(); i++) {
            int from = connections.get(i).first;
            int to = connections.get(i).second;

            graph[from - 1].add(to - 1);
            graph[to - 1].add(from - 1);
        }

        for (int i = 0; i < numOfServers; i++) {
            if (disc[i] == -1) {
                dfs(i, low, disc, graph, res, i);
            }
        }

        List<PairInt> result = new ArrayList<>();

        for (List<Integer> li : res) {
            result.add(new PairInt(li.get(0)+1, li.get(1)+1));
        }

        // visualise(graph);

        return result;
    }

    public static void main(String[] args) {
        // int[] q1_practice = cellComplete(new int[]{0,0,0,0,0,1,1,0,0,1,0,1}, 5);
        // for (int i = 0; i < q1_practice.length; i++) {
        //     System.out.println(q1_practice[i]);
        // }

        // System.out.println(countPairs(10, new ArrayList<Integer>(Arrays.asList(10,3,5,7,2,8,9,6,1,4)), 7));

        List<PairInt> result = criticalConnections(5, 5, new ArrayList<>(Arrays.asList(
            new PairInt(1,2),
            new PairInt(1,3),
            new PairInt(3,4),
            new PairInt(1,4),
            new PairInt(4,5)
        )));
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i).first);
            System.out.print(" ");
            System.out.println(result.get(i).second);
        }
    }
}

class PairInt {
    int first, second;
    PairInt() {}
    PairInt(int first, int second) {
        this.first = first;
        this.second = second;
    }
};