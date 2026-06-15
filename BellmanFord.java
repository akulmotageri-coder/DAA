import java.util.*; // for basic utilities

class Graph {

    // EDGE CLASS → represents one connection in graph
    class Edge {
        int src, dest, weight; // u → v with weight

        Edge() {
            src = dest = weight = 0; // default values
        }
    }

    int V, E;        // V = number of vertices, E = number of edges
    Edge edge[];     // array to store all edges

    // CONSTRUCTOR → creates graph with V vertices and E edges
    Graph(int v, int e) {
        V = v; // assign number of vertices
        E = e; // assign number of edges

        edge = new Edge[e]; // create array for edges

        // initialize each edge object
        for (int i = 0; i < e; i++) {
            edge[i] = new Edge();
        }
    }

    // BELLMAN-FORD ALGORITHM
    void BellmanFord(Graph graph, int source) {

        int V = graph.V; // total vertices
        int E = graph.E; // total edges

        int dist[] = new int[V]; // distance array

        // STEP 1: INITIALIZATION
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE; // set all distances to infinity
        }

        dist[source] = 0; // distance from source to itself = 0

        // STEP 2: RELAX EDGES (V-1 TIMES)
        // repeat V-1 times because shortest path can have at most V-1 edges
        for (int i = 1; i < V; i++) {

            // go through every edge
            for (int j = 0; j < E; j++) {

                int u = graph.edge[j].src;     // start node
                int v = graph.edge[j].dest;    // end node
                int weight = graph.edge[j].weight; // edge weight

                // RELAXATION CONDITION
                // check if:
                // 1. u is reachable
                // 2. going through u gives shorter path to v
                if (dist[u] != Integer.MAX_VALUE &&
                    dist[u] + weight < dist[v]) {

                    dist[v] = dist[u] + weight; // update shortest distance
                }
            }
        }

        // STEP 3: NEGATIVE CYCLE CHECK
        // if we can still relax → graph has negative cycle
        for (int j = 0; j < E; j++) {

            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            int weight = graph.edge[j].weight;

            if (dist[u] != Integer.MAX_VALUE &&
                dist[u] + weight < dist[v]) {

                System.out.println("Graph contains negative weight cycle");
                return; // stop execution
            }
        }

        // STEP 4: PRINT RESULT
        printDistances(dist, V);
    }

    // FUNCTION TO PRINT DISTANCES
    void printDistances(int dist[], int V) {

        System.out.println("Vertex Distance from Source");

        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t" + dist[i]); // print vertex and distance
        }
    }

    public static void main(String[] args) {

        int V = 5; // number of vertices
        int E = 8; // number of edges

        Graph graph = new Graph(V, E); // create graph object

        // DEFINING EDGES (u → v with weight)

        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = -1;

        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 4;

        graph.edge[2].src = 1;
        graph.edge[2].dest = 2;
        graph.edge[2].weight = 3;

        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 2;

        graph.edge[4].src = 1;
        graph.edge[4].dest = 4;
        graph.edge[4].weight = 2;

        graph.edge[5].src = 3;
        graph.edge[5].dest = 2;
        graph.edge[5].weight = 5;

        graph.edge[6].src = 3;
        graph.edge[6].dest = 1;
        graph.edge[6].weight = 1;

        graph.edge[7].src = 4;
        graph.edge[7].dest = 3;
        graph.edge[7].weight = -3;

        // CALL ALGORITHM WITH SOURCE = 0
        graph.BellmanFord(graph, 0);
    }
}
