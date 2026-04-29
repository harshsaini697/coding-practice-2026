# Dynamic Programming Interview Prep

Use this folder as a DP map. The goal is not to memorize every solution, but to recognize the pattern, define the state, write the recurrence, and choose top-down or bottom-up confidently.

## How To Study DP

1. Start with brute force recursion.
2. Identify repeated subproblems.
3. Define a compact state.
4. Add memoization.
5. Convert to tabulation if needed.
6. Optimize space only after the recurrence is correct.

## Files

| File | What It Covers |
|---|---|
| `01-dp-recognition.md` | How to identify DP problems |
| `02-recursion-memo-tabulation.md` | Moving from brute force to memoization to bottom-up DP |
| `03-one-dimensional-dp.md` | Linear DP, house robber, climbing stairs, Kadane-style thinking |
| `04-grid-dp.md` | Matrix paths, obstacles, path sums, 2D transitions |
| `05-knapsack-dp.md` | 0/1 knapsack, unbounded knapsack, subset sum, coin change |
| `06-subsequence-string-dp.md` | LCS, edit distance, palindromes, subsequences |
| `07-interval-dp.md` | Ranges, merging, bursting, printing, removing boxes |
| `08-state-machine-dp.md` | Stock problems and mode-based DP |
| `09-tree-dp.md` | DP on trees, include/exclude, rerooting idea |
| `10-bitmask-dp.md` | Small-n set DP, TSP, assignment, visit-all problems |
| `11-digit-dp.md` | Counting numbers under constraints |
| `12-advanced-dp.md` | Probability DP, game DP, optimization tricks |
| `JavaTemplates.java` | Java templates for common DP patterns |
| `PracticeChecklist.md` | Problem list organized by pattern |

## The Core DP Question

For every DP problem, force yourself to answer:

```text
What does dp[...] mean in plain English?
```

If you cannot explain the state clearly, the recurrence will usually feel messy.

