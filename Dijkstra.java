import java.util.Scanner; // used to take input from user

public class DijkstraFinal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // create Scanner object for input

        System.out.print("No of nodes: ");
        int numNodes = sc.nextInt(); // read number of nodes (vertices)

        // costMatrix stores graph weights (adjacency matrix)
        // size is numNodes because we are using 0-based indexing
        int[][] costMatrix = new int[numNodes][numNodes];

        // distance[i] → shortest distance from source to node i
        int[] distance = new int[numNodes];

        // visited[i] → 1 if node is processed, 0 otherwise
        int[] visited = new int[numNodes];

        // parent[i] → stores previous node in shortest path
        int[] parent = new int[numNodes];

        System.out.println("Cost matrix:");

        // read cost matrix from user (0-based)
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                costMatrix[i][j] = sc.nextInt(); // weight from i → j
            }
        }

        System.out.print("Source vertex (0 to " + (numNodes - 1) + "): ");
        int source = sc.nextInt(); // starting node

        // call Dijkstra function
        dijkstra(numNodes, costMatrix, distance, visited, parent, source);

        // print shortest paths
        printPaths(numNodes, distance, parent, source);

        sc.close(); // close scanner
    }

    // -------------------------------
    // DIJKSTRA ALGORITHM FUNCTION
    // -------------------------------
    static void dijkstra(int numNodes, int[][] costMatrix,
                         int[] distance, int[] visited,
                         int[] parent, int source) {

        // STEP 1: INITIALIZATION
        for (int i = 0; i < numNodes; i++) {

            // initially distance from source to i is direct edge weight
            distance[i] = costMatrix[source][i];

            // initially parent of every node is source
            parent[i] = source;

            // mark all nodes as unvisited
            visited[i] = 0;
        }

        // distance from source to itself is always 0
        distance[source] = 0;

        // mark source as visited
        visited[source] = 1;

        // STEP 2: MAIN LOOP (run n-1 times)
        for (int count = 1; count < numNodes; count++) {

            int minDistance = Integer.MAX_VALUE; // store minimum distance
            int nearestNode = -1; // store node with minimum distance

            // find nearest unvisited node
            for (int i = 0; i < numNodes; i++) {

                // check if node is unvisited AND has smaller distance
                if (visited[i] == 0 && distance[i] < minDistance) {
                    minDistance = distance[i]; // update min distance
                    nearestNode = i; // update nearest node
                }
            }

            // mark this nearest node as visited (finalized)
            visited[nearestNode] = 1;

            // STEP 3: UPDATE distances of neighbors
            for (int j = 0; j < numNodes; j++) {

                // check:
                // 1. node j is not visited
                // 2. new path through nearestNode is shorter
                if (visited[j] == 0 &&
                    distance[nearestNode] + costMatrix[nearestNode][j] < distance[j]) {

                    // update shortest distance
                    distance[j] = distance[nearestNode] + costMatrix[nearestNode][j];

                    // update parent to track path
                    parent[j] = nearestNode;
                }
            }
        }
    }

    // -------------------------------
    // FUNCTION TO PRINT SHORTEST PATHS
    // -------------------------------
    static void printPaths(int numNodes,
                           int[] distance,
                           int[] parent, int source) {

        // for every node
        for (int i = 0; i < numNodes; i++) {

            // skip source
            if (i != source) {

                // print shortest distance
                System.out.println("Shortest distance from " + source +
                                   " to " + i + " is: " + distance[i]);

                // print path in reverse (from destination to source)
                System.out.print("Path is: " + i);

                int current = parent[i]; // start from parent of i

                // keep moving until source is reached
                while (current != source) {
                    System.out.print(" <- " + current); // print path
                    current = parent[current]; // move to previous node
                }

                // finally print source
                System.out.println(" <- " + source);
            }
        }
    }
}
