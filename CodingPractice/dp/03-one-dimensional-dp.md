# 03. One-Dimensional DP

Use 1D DP when the state can be described by one index.

```text
dp[i] = answer for prefix ending at i
or
dp[i] = answer starting from i
```

## Pattern A: Climbing Stairs

Question shape:

```text
How many ways to reach step n?
```

State:

```text
dp[i] = ways to reach step i
```

Transition:

```text
dp[i] = dp[i - 1] + dp[i - 2]
```

## Pattern B: House Robber

Question shape:

```text
Choose items, cannot choose adjacent items.
```

State:

```text
dp[i] = max money from houses 0..i
```

Transition:

```text
dp[i] = max(dp[i - 1], nums[i] + dp[i - 2])
```

## Pattern C: Kadane Style

Question shape:

```text
Best subarray ending here.
```

State:

```text
dp[i] = best subarray sum ending exactly at i
```

Transition:

```text
dp[i] = max(nums[i], nums[i] + dp[i - 1])
```

## Java Template

```java
int solve(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];

    dp[0] = nums[0];

    for (int i = 1; i < n; i++) {
        dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
    }

    int ans = dp[0];
    for (int x : dp) ans = Math.max(ans, x);
    return ans;
}
```

## Interview Problems

- Climbing Stairs
- Min Cost Climbing Stairs
- House Robber
- House Robber II
- Maximum Subarray
- Decode Ways
- Word Break

