import java.util.*;

/*
 * Java DP templates for interview prep.
 *
 * These methods are intentionally generic. Copy the shape, then adapt the
 * state and transition to the actual problem.
 */
class JavaTemplates {

    // 1D DP: max subarray ending at each index.
    int maxSubarray(int[] nums) {
        int bestEndingHere = nums[0];
        int best = nums[0];

        for (int i = 1; i < nums.length; i++) {
            bestEndingHere = Math.max(nums[i], nums[i] + bestEndingHere);
            best = Math.max(best, bestEndingHere);
        }

        return best;
    }

    // Grid DP: minimum path sum with moves down/right.
    int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];

        dp[0][0] = grid[0][0];

        for (int r = 1; r < rows; r++) {
            dp[r][0] = dp[r - 1][0] + grid[r][0];
        }

        for (int c = 1; c < cols; c++) {
            dp[0][c] = dp[0][c - 1] + grid[0][c];
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                dp[r][c] = grid[r][c] + Math.min(dp[r - 1][c], dp[r][c - 1]);
            }
        }

        return dp[rows - 1][cols - 1];
    }

    // 0/1 knapsack: each item can be used once.
    int knapsack01(int[] weight, int[] value, int capacity) {
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < weight.length; i++) {
            for (int cap = capacity; cap >= weight[i]; cap--) {
                dp[cap] = Math.max(dp[cap], value[i] + dp[cap - weight[i]]);
            }
        }

        return dp[capacity];
    }

    // Unbounded knapsack: each item can be used unlimited times.
    int unboundedKnapsack(int[] weight, int[] value, int capacity) {
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < weight.length; i++) {
            for (int cap = weight[i]; cap <= capacity; cap++) {
                dp[cap] = Math.max(dp[cap], value[i] + dp[cap - weight[i]]);
            }
        }

        return dp[capacity];
    }

    // Longest common subsequence.
    int lcs(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }

    // Interval DP skeleton.
    int intervalDpSkeleton(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int len = 1; len <= n; len++) {
            for (int left = 0; left + len - 1 < n; left++) {
                int right = left + len - 1;

                if (left == right) {
                    dp[left][right] = nums[left];
                    continue;
                }

                for (int mid = left; mid < right; mid++) {
                    dp[left][right] = Math.max(
                        dp[left][right],
                        dp[left][mid] + dp[mid + 1][right]
                    );
                }
            }
        }

        return dp[0][n - 1];
    }

    // Bitmask DP skeleton: visit all nodes and end anywhere.
    int shortestVisitAll(int[][] cost) {
        int n = cost.length;
        int full = 1 << n;
        int inf = 1_000_000_000;
        int[][] dp = new int[full][n];

        for (int mask = 0; mask < full; mask++) {
            Arrays.fill(dp[mask], inf);
        }

        for (int i = 0; i < n; i++) {
            dp[1 << i][i] = 0;
        }

        for (int mask = 0; mask < full; mask++) {
            for (int last = 0; last < n; last++) {
                if (dp[mask][last] == inf) continue;

                for (int next = 0; next < n; next++) {
                    if ((mask & (1 << next)) != 0) continue;

                    int nextMask = mask | (1 << next);
                    dp[nextMask][next] = Math.min(
                        dp[nextMask][next],
                        dp[mask][last] + cost[last][next]
                    );
                }
            }
        }

        int ans = inf;
        for (int last = 0; last < n; last++) {
            ans = Math.min(ans, dp[full - 1][last]);
        }

        return ans;
    }
}

