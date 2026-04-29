# 02. From Recursion To Memoization To Tabulation

Most DP solutions can be developed in three steps.

## Step 1: Brute Force Recursion

Write the natural recursive choice tree.

Example shape:

```java
int solve(int i) {
    if (i == n) return 0;

    int skip = solve(i + 1);
    int take = value[i] + solve(i + 1);

    return Math.max(skip, take);
}
```

This is often exponential because the same `i` is solved many times.

## Step 2: Memoization

Store answers for states already computed.

```java
int[] memo = new int[n];
Arrays.fill(memo, -1);

int solve(int i) {
    if (i == n) return 0;
    if (memo[i] != -1) return memo[i];

    int skip = solve(i + 1);
    int take = value[i] + solve(i + 1);

    return memo[i] = Math.max(skip, take);
}
```

Top-down DP is often easiest in interviews because it follows the brute force idea directly.

## Step 3: Tabulation

Compute smaller states first.

```java
int[] dp = new int[n + 1];

for (int i = n - 1; i >= 0; i--) {
    int skip = dp[i + 1];
    int take = value[i] + dp[i + 1];
    dp[i] = Math.max(skip, take);
}
```

## When To Prefer Top-Down

Use top-down when:

- the recurrence is easier to express recursively
- not all states are reachable
- the state has several dimensions
- interval or game logic is tricky

## When To Prefer Bottom-Up

Use bottom-up when:

- recursion depth may be too large
- the order of states is obvious
- you need space optimization
- the interviewer asks for iterative DP

## The Translation Rule

Top-down:

```text
answer(state) depends on smaller states
```

Bottom-up:

```text
compute smaller states before larger states
```

