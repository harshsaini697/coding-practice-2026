class Solution {
    public int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }

        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return solve(boxes, 0, n - 1, 0, dp);
    }

    public int solve(int[] boxes, int i, int j, int k, int[][][] dp){
        if (i > j) return 0;
        if (dp[i][j][k] > 0) return dp[i][j][k];

        int i0 = i, k0 = k;
        for (; i + 1 <= j && boxes[i + 1] == boxes[i]; i++, k++); // optimization: all boxes of the same color counted continuously from the first box should be grouped together
        int res = (k + 1) * (k + 1) + solve(boxes, i + 1, j, 0, dp);

        for (int m = i + 1; m <= j; m++) {
            if (boxes[m] == boxes[i]) {
                res = Math.max(res, solve(boxes, i + 1, m - 1, 0, dp) + solve(boxes, m, j, k + 1, dp));
            }
        }

        dp[i0][j][k0] = res;
        return res;
    }
}