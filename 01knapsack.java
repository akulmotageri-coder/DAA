import java.util.Scanner;

public class Knapsack {

    // FUNCTION → returns maximum profit
    static int knapsack(int weights[], int profits[], int numItems, int capacity) {

        // dp[i][j] → max profit using first i items and capacity j
        int dp[][] = new int[numItems + 1][capacity + 1];

        // STEP 1: Build table
        for (int i = 0; i <= numItems; i++) {
            for (int j = 0; j <= capacity; j++) {

                // BASE CASE → no items or no capacity
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }

                // IF current item can fit
                else if (weights[i - 1] <= j) {

                    // include item vs exclude item → take max
                    dp[i][j] = Math.max(
                        profits[i - 1] + dp[i - 1][j - weights[i - 1]], // include
                        dp[i - 1][j] // exclude
                    );
                }

                // IF item cannot fit
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // FINAL ANSWER
        return dp[numItems][capacity];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // INPUT: number of items
        System.out.print("Enter number of items: ");
        int numItems = sc.nextInt();

        int weights[] = new int[numItems];
        int profits[] = new int[numItems];

        // INPUT: weights and profits
        System.out.println("Enter weight and profit of each item:");

        for (int i = 0; i < numItems; i++) {
            weights[i] = sc.nextInt();
            profits[i] = sc.nextInt();
        }

        // INPUT: capacity
        System.out.print("Enter knapsack capacity: ");
        int capacity = sc.nextInt();

        // CALL FUNCTION
        int maxProfit = knapsack(weights, profits, numItems, capacity);

        // OUTPUT
        System.out.println("Maximum profit = " + maxProfit);

        sc.close();
    }
}
