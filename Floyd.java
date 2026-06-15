import java.util.*; // import Scanner

class Floyds {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in); // input object

    System.out.println("Enter the number of vertices: ");
    int n = sc.nextInt(); // number of vertices

    // adjacency matrix to store graph
    // arr[i][j] = weight from i → j
    int adj[][] = new int[n][n];

    System.out.println("Enter the adjacency matrix (enter 999 for infinity): ");

    // take matrix input
    for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
        adj[i][j] = sc.nextInt(); // read weight
      }
    }

    // apply Floyd-Warshall algorithm
    floyd(adj, n);

    // print final shortest path matrix
    System.out.println("The all-pairs shortest path is:");
    for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
        System.out.print(adj[i][j] + " "); // print shortest distance
      }
      System.out.println(); // next row
    }
  }

  // Floyd-Warshall Algorithm
  static void floyd(int arr[][], int n){

    // k → intermediate node
    for(int k = 0; k < n; k++){

      // i → source node
      for(int i = 0; i < n; i++){

        // j → destination node
        for(int j = 0; j < n; j++){

          // check if path through k is shorter
          if(arr[i][k] + arr[k][j] < arr[i][j]){

            // update shortest path
            arr[i][j] = arr[i][k] + arr[k][j];
          }
        }
      }
    }
  }
}
