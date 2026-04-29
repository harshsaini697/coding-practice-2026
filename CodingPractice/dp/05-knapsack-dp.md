# 05. Knapsack DP

Knapsack appears when you choose or skip items under a capacity or target.

## 0/1 Knapsack

Each item can be used at most once.

State:

```text
dp[i][cap] = best value using first i items with capacity cap
```

Transition:

```text
skip = dp[i - 1][cap]
take = value[i - 1] + dp[i - 1][cap - weight[i - 1]]
dp[i][cap] = max(skip, take)
```

## 0/1 Space-Optimized Template

Loop capacity backward so each item is used once.

```java
int knapsack01(int[] weight, int[] value, int capacity) {
    int[] dp = new int[capacity + 1];

    for (int i = 0; i < weight.length; i++) {
        for (int cap = capacity; cap >= weight[i]; cap--) {
            dp[cap] = Math.max(dp[cap], value[i] + dp[cap - weight[i]]);
        }
    }

    return dp[capacity];
}
```

## Unbounded Knapsack

Each item can be used unlimited times.

Loop capacity forward.

```java
int unboundedKnapsack(int[] weight, int[] value, int capacity) {
    int[] dp = new int[capacity + 1];

    for (int i = 0; i < weight.length; i++) {
        for (int cap = weight[i]; cap <= capacity; cap++) {
            dp[cap] = Math.max(dp[cap], value[i] + dp[cap - weight[i]]);
        }
    }

    return dp[capacity];
}
```

## Subset Sum

Question:

```text
Can we make target using each number at most once?
```

```java
boolean canMakeTarget(int[] nums, int target) {
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;

    for (int num : nums) {
        for (int sum = target; sum >= num; sum--) {
            dp[sum] = dp[sum] || dp[sum - num];
        }
    }

    return dp[target];
}
```

## Coin Change Min Coins

Unlimited coins, minimize count.

```java
int coinChange(int[] coins, int amount) {
    int INF = amount + 1;
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, INF);
    dp[0] = 0;

    for (int coin : coins) {
        for (int x = coin; x <= amount; x++) {
            dp[x] = Math.min(dp[x], 1 + dp[x - coin]);
        }
    }

    return dp[amount] == INF ? -1 : dp[amount];
}
```

## Interview Problems

- Partition Equal Subset Sum
- Target Sum
- Coin Change
- Coin Change II
- Last Stone Weight II
- Ones and Zeroes

