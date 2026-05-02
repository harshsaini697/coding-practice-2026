class Solution {
    int[] memo;
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        
        memo = new int[cost.length];
        
        for (int i = 0; i < cost.length; i++) {
            memo[i] = -1;
        }
        return Math.min(calculate(cost, 0), calculate(cost, 1));
    }
    
    private int calculate(int[] cost, int i) {
        if (i >= cost.length) {
            return 0;
        }
        
        if(memo[i] != -1) {
            return memo[i];
        }
        
        // choose i + 1 or i + 2
        int tempCost = Math.min(cost[i] + calculate(cost, i + 1), cost[i] + calculate(cost, i + 2));
        memo[i] = tempCost;
        return memo[i];
    }
}