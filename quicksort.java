import java.util.*;

class QuickSort {
    void quick(int arr[], int l, int h) {
        if (l < h) {
            int s = partition(arr, l, h);
            quick(arr, l, s - 1);
            quick(arr, s + 1, h);
        }
    }

    int partition(int arr[], int l, int h) {
        int p = arr[l];
        int i = l + 1;
        int j = h;

        while (i <= j) {
            while (i <= h && arr[i] < p) i++;
            while (arr[j] > p) j--;

            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            } else {
                int temp = arr[l];
                arr[l] = arr[j];
                arr[j] = temp;
                return j;
            }
        }
        return j;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int arr[] = new int[n];
        Random gen = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = gen.nextInt(1000);
        }

        System.out.println("Random elements:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        long start = System.nanoTime();

        QuickSort qs = new QuickSort();
        qs.quick(arr, 0, n - 1);

        long stop = System.nanoTime();

        System.out.println("\nSorted array:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println("\nTime taken: " + (stop - start));
    }
}
