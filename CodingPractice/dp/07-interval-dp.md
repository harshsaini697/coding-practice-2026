# 07. Interval DP

Use interval DP when the problem is about a range and choices split, remove, merge, or combine parts of that range.

State usually looks like:

```text
dp[left][right]
```

Sometimes it needs an extra dimension:

```text
dp[left][right][extra]
```

## Recognition Clues

Look for:

- remove elements from a sequence
- merge stones or files
- burst balloons
- choose a split point
- print or delete ranges
- palindrome ranges
- score from subarrays

## Basic Split Recurrence

```text
dp[l][r] = best over split point m:
           dp[l][m] + dp[m + 1][r] + cost(l, m, r)
```

## Burst Balloons Style

Choose the last balloon in an interval.

```text
dp[l][r] = max score from open interval (l, r)
```

Transition:

```text
dp[l][r] = max over k:
           dp[l][k] + nums[l] * nums[k] * nums[r] + dp[k][r]
```

## Remove Boxes Style

Plain interval state is not enough because boxes can merge after the middle disappears.

Use:

```text
dp[l][r][k]
```

Meaning:

```text
max score from boxes[l..r],
with k extra boxes of the same color attached to one side
```

Transition idea:

```text
1. remove edge group now
2. or remove the middle first so the edge can merge with same color later
```

## Top-Down Remove Boxes Template

```java
class Solution {
    private int[] boxes;
    private int[][][] memo;

    public int removeBoxes(int[] boxes) {
        this.boxes = boxes;
        int n = boxes.length;
        this.memo = new int[n][n][n];
        return dp(0, n - 1, 0);
    }

    private int dp(int left, int right, int count) {
        if (left > right) return 0;
        if (memo[left][right][count] != 0) return memo[left][right][count];

        while (left < right && boxes[right] == boxes[right - 1]) {
            right--;
            count++;
        }

        int best = dp(left, right - 1, 0) + (count + 1) * (count + 1);

        for (int i = left; i < right; i++) {
            if (boxes[i] == boxes[right]) {
                best = Math.max(
                    best,
                    dp(left, i, count + 1) + dp(i + 1, right - 1, 0)
                );
            }
        }

        return memo[left][right][count] = best;
    }
}
```

## Interview Problems

- Burst Balloons
- Remove Boxes
- Strange Printer
- Matrix Chain Multiplication
- Minimum Cost to Cut a Stick
- Merge Stones
- Palindrome Partitioning II

