class Solution {
    int[] memo;
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        memo = new int[n + 3];
        return solve(n, n);
    }
    
    private int solve(int n, int i) {
        if (i == 0) {
            return 0;
        }
        
        if (i == 1 || i == 2) {
            return 1;
        }
        
        if (memo[i] != 0) {
            return memo[i];
        }
        
        return memo[i] = solve(n, i - 1) + solve(n, i - 2) + solve(n, i - 3);
    }
}