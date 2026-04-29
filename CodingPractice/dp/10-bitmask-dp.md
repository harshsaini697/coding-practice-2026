# 10. Bitmask DP

Use bitmask DP when the state is a set of chosen or visited items.

Usually `n` is small:

```text
n <= 20
```

State:

```text
mask = which items have been chosen
```

## TSP Style

State:

```text
dp[mask][last] = min cost to visit set mask and end at last
```

Transition:

```text
dp[mask | (1 << next)][next] =
    min(dp[mask | (1 << next)][next],
        dp[mask][last] + cost[last][next])
```

Java template:

```java
int tsp(int[][] cost) {
    int n = cost.length;
    int full = 1 << n;
    int INF = 1_000_000_000;
    int[][] dp = new int[full][n];

    for (int mask = 0; mask < full; mask++) {
        Arrays.fill(dp[mask], INF);
    }

    dp[1][0] = 0;

    for (int mask = 0; mask < full; mask++) {
        for (int last = 0; last < n; last++) {
            if (dp[mask][last] == INF) continue;

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

    int ans = INF;
    for (int last = 0; last < n; last++) {
        ans = Math.min(ans, dp[full - 1][last] + cost[last][0]);
    }

    return ans;
}
```

## Recognition Clues

- visit all nodes
- assign workers to jobs
- choose subsets
- `n` is small but brute force over permutations is too large

## Interview Problems

- Shortest Path Visiting All Nodes
- Partition to K Equal Sum Subsets
- Maximum Students Taking Exam
- Minimum Cost to Connect Two Groups
- Can I Win

