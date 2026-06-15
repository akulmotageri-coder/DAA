import java.util.*; // for Scanner and Arrays

public class TSP {

    // FUNCTION → returns minimum travelling cost
    static int tsp(int[][] graph, int mask, int currentCity, int[][] dp) {

        int n = graph.length; // total number of cities

        // BASE CASE → if all cities are visited
        if (mask == (1 << n) - 1) {
            return graph[currentCity][0]; // return to starting city
        }

        // if already computed → return stored value
        if (dp[mask][currentCity] != -1) {
            return dp[mask][currentCity];
        }

        int minCost = Integer.MAX_VALUE; // initialize answer

        // try visiting all cities
        for (int nextCity = 0; nextCity < n; nextCity++) {

            // check if nextCity is NOT visited
            if ((mask & (1 << nextCity)) == 0) {

                // calculate cost:
                // current → next + remaining path cost (recursion)
                int cost = graph[currentCity][nextCity] +
                        tsp(graph,
                            mask | (1 << nextCity), // mark nextCity visited
                            nextCity,
                            dp);

                // take minimum cost
                minCost = Math.min(minCost, cost);
            }
        }

        // store result in dp and return
        return dp[mask][currentCity] = minCost;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // INPUT: number of cities
        int n = sc.nextInt();

        int[][] graph = new int[n][n];

        // INPUT: cost matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        // dp[mask][city] → stores minimum cost
        int[][] dp = new int[1 << n][n];

        // initialize dp with -1 (not computed)
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // start from city 0 with mask = 1 (only city 0 visited)
        int result = tsp(graph, 1, 0, dp);

        // OUTPUT
        System.out.println(result);

        sc.close();
    }
}
