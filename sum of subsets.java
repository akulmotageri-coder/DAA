import java.util.Scanner;

public class SumOfSubsets {

    static int solutionCount = 1; // to number solutions

    // BACKTRACKING FUNCTION
    static void findSubset(int[] set, int index, int currentSum,
                            int targetSum, int[] selected) {

        // CASE 1 → solution found
        if (currentSum == targetSum) {

            System.out.print("Solution " + solutionCount + ": { ");

            for (int i = 0; i < set.length; i++) {
                if (selected[i] == 1)
                    System.out.print(set[i] + " ");
            }

            System.out.println("}");
            solutionCount++;
            return;
        }

        // CASE 2 → stop condition
        if (index == set.length || currentSum > targetSum)
            return;

        // STEP 1 → INCLUDE current element
        selected[index] = 1;
        findSubset(set,
                   index + 1,
                   currentSum + set[index],
                   targetSum,
                   selected);

        // STEP 2 → EXCLUDE current element (backtrack)
        selected[index] = 0;
        findSubset(set,
                   index + 1,
                   currentSum,
                   targetSum,
                   selected);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // INPUT: number of elements
        int n = sc.nextInt();

        int[] set = new int[n];        // input elements
        int[] selected = new int[n];    // 0/1 array for choices

        // INPUT: elements
        for (int i = 0; i < n; i++) {
            set[i] = sc.nextInt();
            selected[i] = 0;
        }

        // INPUT: target sum
        int targetSum = sc.nextInt();

        // START BACKTRACKING
        findSubset(set, 0, 0, targetSum, selected);

        sc.close();
    }
}
