public class NQueens {

    private int[] result;
    private boolean[] column;
    private boolean[] leftDiagonal;
    private boolean[] rightDiagonal;
    private int n;

    public NQueens(int n) {
        this.n = n;
        result = new int[n];
        column = new boolean[n];
        leftDiagonal = new boolean[2 * n - 1];
        rightDiagonal = new boolean[2 * n - 1];
    }

    public boolean solve() {
        return solveNQueens(0);
    }

    private boolean solveNQueens(int row) {
        if (row == n) {
            printSolution();
            return true;
        }

        boolean res = false;

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                placeQueen(row, col);
                res = solveNQueens(row + 1) || res;
                removeQueen(row, col); // backtrack
            }
        }
        return res;
    }

    private boolean isSafe(int row, int col) {
        return !column[col] &&
               !leftDiagonal[row - col + n - 1] &&
               !rightDiagonal[row + col];
    }

    private void placeQueen(int row, int col) {
        result[row] = col;
        column[col] = true;
        leftDiagonal[row - col + n - 1] = true;
        rightDiagonal[row + col] = true;
    }

    private void removeQueen(int row, int col) {
        column[col] = false;
        leftDiagonal[row - col + n - 1] = false;
        rightDiagonal[row + col] = false;
    }

    private void printSolution() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (result[i] == j)
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 6; // change value here
        NQueens queens = new NQueens(n);

        if (!queens.solve()) {
            System.out.println("No solution exists");
        }
    }
}
