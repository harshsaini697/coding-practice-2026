class Solution {
    int[] memo;
    public int tribonacci(int n) {
        memo = new int[n + 1];
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        memo[n] = 0;
        memo[n - 1] = 1;
        memo[n - 2] = 1;
        for (int i = n - 3; i >= 0; i--) {
            memo[i] = memo[i + 1] + memo[i + 2] + memo[i + 3];
        }
        
        return memo[0];
    }
}