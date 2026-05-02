class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n + 2];

        for (int i = n - 1; i >= 0; i--) {
            memo[i] = cost[i] + Math.min(memo[i + 1], memo[i + 2]);
        }

        return Math.min(memo[0], memo[1]);
    }
}
