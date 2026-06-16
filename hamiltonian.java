import java.util.*;

class Hamiltonian {

    static int V;              // number of vertices
    static int[] path;        // stores Hamiltonian path
    static int[][] graph;     // adjacency matrix
    static int position;      // current position in path

    // MAIN FUNCTION → start solving
    static void findCycle(int[][] g) {

        V = g.length;                 // size of graph
        graph = g;                   // store graph
        path = new int[V];           // path array

        Arrays.fill(path, -1);       // initialize path

        position = 1;                // first vertex already placed
        path[0] = 0;                 // start from vertex 0

        try {
            solve(0);                // start recursion from vertex 0
            System.out.println("No solution");
        }
        catch (Exception e) {
            // if solution found → exception used to stop recursion
            System.out.println(e.getMessage());
            display();
        }
    }

    // BACKTRACKING FUNCTION
    static void solve(int currentVertex) throws Exception {

        // CASE 1 → Hamiltonian cycle completed
        if (graph[currentVertex][0] == 1 && position == V) {
            throw new Exception("Solution found");
        }

        // CASE 2 → all vertices used but no cycle
        if (position == V) {
            return;
        }

        // TRY ALL POSSIBLE VERTICES
        for (int nextVertex = 0; nextVertex < V; nextVertex++) {

            // CHECK:
            // 1. edge exists
            // 2. vertex not already in path
            if (graph[currentVertex][nextVertex] == 1 &&
                !isVisited(nextVertex)) {

                // STEP 1 → add vertex to path
                path[position++] = nextVertex;

                // STEP 2 → mark edge as used
                graph[currentVertex][nextVertex] = 0;
                graph[nextVertex][currentVertex] = 0;

                // STEP 3 → recursion
                solve(nextVertex);

                // STEP 4 → backtrack
                graph[currentVertex][nextVertex] = 1;
                graph[nextVertex][currentVertex] = 1;
                path[--position] = -1;
            }
        }
    }

    // CHECK IF VERTEX IS ALREADY IN PATH
    static boolean isVisited(int vertex) {

        for (int i = 0; i < position; i++) {
            if (path[i] == vertex)
                return true;
        }

        return false;
    }

    // PRINT FINAL HAMILTONIAN CYCLE
    static void display() {

        System.out.println("Hamiltonian Cycle:");

        for (int i = 0; i < V; i++) {
            System.out.print(path[i] + " ");
        }

        System.out.println(path[0]); // return to start
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // INPUT SIZE
        System.out.println("Enter number of vertices:");
        int n = sc.nextInt();

        int[][] g = new int[n][n];

        // INPUT MATRIX
        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = sc.nextInt();
            }
        }

        // START
        findCycle(g);
    }
}
