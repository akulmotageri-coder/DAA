import java.util.*;

class Binary {

  // Binary Search Function
  static void search(int arr[], int key, int n) {
    int l = 0;
    int h = n - 1;

    while (l <= h) {
      int mid = (l + h) / 2;

      // ✅ Key found
      if (arr[mid] == key) {
        System.out.println("Key found at position: " + mid);
        return;
      }

      // ✅ Move left
      else if (arr[mid] > key) {
        h = mid - 1;
      }

      // ✅ Move right
      else {
        l = mid + 1;
      }
    }

    // ❌ Not found
    System.out.println("Key not found");
  }

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter size: ");
    int n = sc.nextInt();

    int arr[] = new int[n];  // ✅ fixed

    System.out.println("Enter sorted array elements: ");
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    System.out.println("Enter key to be searched: ");
    int key = sc.nextInt();

    long start = System.nanoTime();

    search(arr, key, n);

    long stop = System.nanoTime();

    System.out.println("Time taken: " + (stop - start) + " ns");
  }
}
