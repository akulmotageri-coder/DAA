import java.util.Scanner;

public class PrimsClass {

    static int n;          // number of nodes
    static int cost[][];  // adjacency matrix

    public static void main(String[] args) {
        readMatrix();  // Step 1: input
        prims();       // Step 2: find MST
    }

    // -------------------------------
    // STEP 1: READ INPUT MATRIX
    // -------------------------------
    static void readMatrix() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter number of nodes:");
        n = scan.nextInt();

        cost = new int[n][n]; // 0-based matrix

        System.out.println("Enter adjacency matrix:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                cost[i][j] = scan.nextInt();

                // If no edge, replace 0 with large value
                if (cost[i][j] == 0)
                    cost[i][j] = 999;
            }
        }
    }

    // -------------------------------
    // STEP 2: PRIM'S ALGORITHM
    // -------------------------------
    static void prims() {

        int visited[] = new int[n]; // track visited nodes (0 = no, 1 = yes)
        int ne = 0;                 // number of edges in MST
        int min, a = 0, b = 0;      // edge (a → b)
        int mincost = 0;            // total cost

        visited[0] = 1; // Start from first node

        // We need exactly (n-1) edges
        while (ne < n - 1) {

            min = 999; // reset minimum

            // Find minimum edge from visited → unvisited
            for (int i = 0; i < n; i++) {

                if (visited[i] == 1) { // only from visited nodes

                    for (int j = 0; j < n; j++) {

                        // choose edge to unvisited node with minimum cost
                        if (visited[j] == 0 && cost[i][j] < min) {
                            min = cost[i][j];
                            a = i;
                            b = j;
                        }
                    }
                }
            }

            // Print selected edge
            System.out.println("Edge " + (ne + 1) + ": (" + a + "," + b + ") cost: " + min);

            mincost += min;   // add cost
            visited[b] = 1;   // mark new node visited

            // Remove edge (avoid reuse)
            cost[a][b] = cost[b][a] = 999;

            ne++; // increment edge count
        }

        System.out.println("Minimum cost = " + mincost);
    }
}
