package q30651;

import java.util.*;

public class JobSequencingGreedy {

    // Class to store Job details
    static class Job {
        char id;       // Job ID (like A, B, C)
        int deadline;  // Deadline (time slot)
        int profit;    // Profit if job is done

        Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();   // number of jobs
        Job[] jobs = new Job[n];

        // -------------------------------
        // STEP 1: TAKE INPUT
        // -------------------------------
        for (int i = 0; i < n; i++) {
            char id = sc.next().charAt(0);
            int deadline = sc.nextInt();
            int profit = sc.nextInt();

            jobs[i] = new Job(id, deadline, profit);
        }

        int slots = sc.nextInt();  // total available time slots

        // -------------------------------
        // STEP 2: SORT JOBS BY PROFIT (DESCENDING)
        // -------------------------------
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // -------------------------------
        // STEP 3: CREATE SLOT ARRAY
        // -------------------------------
        char[] result = new char[slots];     // to store job IDs
        boolean[] filled = new boolean[slots]; // track occupied slots

        // -------------------------------
        // STEP 4: ASSIGN JOBS GREEDILY
        // -------------------------------
        for (int i = 0; i < n; i++) {

            // Find last possible slot for this job
            int lastSlot = Math.min(slots, jobs[i].deadline) - 1;

            // Try to place job from last slot backwards
            for (int j = lastSlot; j >= 0; j--) {

                // If slot is free → assign job
                if (!filled[j]) {
                    filled[j] = true;
                    result[j] = jobs[i].id;
                    break;  // move to next job
                }
            }
        }

        // -------------------------------
        // STEP 5: PRINT RESULT
        // -------------------------------
        for (int i = 0; i < slots; i++) {
            if (filled[i]) {
                System.out.print(result[i] + " ");
            }
        }

        System.out.println();
        sc.close();
    }
}
