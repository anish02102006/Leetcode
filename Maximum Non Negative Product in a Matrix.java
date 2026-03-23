class Solution {
    int m, n;
    final int MOD = 1000000007;

    // Memoization table for storing (max, min) pairs
    Pair<Long, Long>[][] t;

    public Pair<Long, Long> solve(int i, int j, int[][] grid) {
        // Base case: If we're at the bottom-right corner, return the value
        if (i == m - 1 && j == n - 1) {
            return new Pair<>((long) grid[i][j], (long) grid[i][j]);
        }

        long maxVal = Long.MIN_VALUE;
        long minVal = Long.MAX_VALUE;

        // If already computed, return the memoized result
        if (t[i][j] != null) {
            return t[i][j];
        }

        // Move down
        if (i + 1 < m) {
            Pair<Long, Long> down = solve(i + 1, j, grid);
            maxVal = Math.max(maxVal, Math.max(grid[i][j] * down.getKey(), grid[i][j] * down.getValue()));
            minVal = Math.min(minVal, Math.min(grid[i][j] * down.getKey(), grid[i][j] * down.getValue()));
        }

        // Move right
        if (j + 1 < n) {
            Pair<Long, Long> right = solve(i, j + 1, grid);
            maxVal = Math.max(maxVal, Math.max(grid[i][j] * right.getKey(), grid[i][j] * right.getValue()));
            minVal = Math.min(minVal, Math.min(grid[i][j] * right.getKey(), grid[i][j] * right.getValue()));
        }

        // Memoize results
        t[i][j] = new Pair<>(maxVal, minVal);

        return t[i][j];
    }

    public int maxProductPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Initialize the memoization table
        t = new Pair[m][n];

        // Get the result from the top-left corner
        Pair<Long, Long> result = solve(0, 0, grid);

        // If the result is negative, return -1, otherwise return the result modulo MOD
        return result.getKey() < 0 ? -1 : (int) (result.getKey() % MOD);
    }
}
