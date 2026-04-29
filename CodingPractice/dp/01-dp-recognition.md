# 01. How To Recognize DP

DP usually appears when the problem has this shape:

```text
Try many choices now.
The choice affects the future.
The same smaller situations repeat.
```

Short version:

```text
recursion + repeated states = DP
```

## Common Signals

The problem asks for one of these:

- maximum or minimum
- number of ways
- true or false possible
- longest or shortest
- best score or optimal cost

Examples:

```text
maximum points
minimum cost
number of paths
can we form target?
longest subsequence
```

## Greedy Feels Suspicious

If the best local move is not clearly always best globally, suspect DP.

Example:

```text
boxes = [1, 2, 2, 1]
```

Remove left `1` first:

```text
1 + 4 + 1 = 6
```

Remove middle `[2,2]` first:

```text
4 + 4 = 8
```

The future merge matters, so greedy fails.

## DP Checklist

Ask these questions:

1. Is the problem asking for max, min, count, possible, longest, or shortest?
2. Do I have choices at each step?
3. After a choice, do I get a smaller version of the same problem?
4. Can different choices lead to the same future state?
5. Can I describe the current situation using a small state?

If yes, DP is likely.

## Common State Shapes

| Pattern | State |
|---|---|
| Linear DP | `dp[i]` |
| Grid DP | `dp[row][col]` |
| Knapsack | `dp[i][capacity]` or `dp[target]` |
| String DP | `dp[i][j]` |
| Interval DP | `dp[left][right]` |
| Tree DP | `dp[node][state]` |
| Bitmask DP | `dp[mask]` or `dp[mask][last]` |
| Digit DP | `dp(pos, tight, started, state)` |

## Interview Habit

Always say:

```text
First I would write the brute force recursion.
Then I would memoize the repeated states.
Then I would consider bottom-up if needed.
```

