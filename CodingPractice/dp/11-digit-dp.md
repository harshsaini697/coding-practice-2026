# 11. Digit DP

Use digit DP when counting numbers in a range with digit constraints.

Examples:

```text
count numbers <= N with no repeated digits
count numbers with digit sum divisible by k
count numbers containing digit 7
```

## Standard State

```text
dp(pos, tight, started, otherState)
```

Meaning:

```text
pos        = current digit index
tight      = whether prefix is equal to N so far
started    = whether we have placed a non-leading-zero digit
otherState = digit sum, mask, remainder, previous digit, etc.
```

## Tight Flag

If `tight` is true:

```text
max digit = digits[pos]
```

Otherwise:

```text
max digit = 9
```

## Skeleton

```java
class DigitDP {
    private char[] digits;
    private Long[][][][] memo;

    long count(long n) {
        digits = Long.toString(n).toCharArray();
        memo = new Long[digits.length][2][2][100];
        return dfs(0, 1, 0, 0);
    }

    private long dfs(int pos, int tight, int started, int state) {
        if (pos == digits.length) {
            return started == 1 ? 1 : 0;
        }

        if (memo[pos][tight][started][state] != null) {
            return memo[pos][tight][started][state];
        }

        int limit = tight == 1 ? digits[pos] - '0' : 9;
        long ans = 0;

        for (int digit = 0; digit <= limit; digit++) {
            int nextTight = tight == 1 && digit == limit ? 1 : 0;
            int nextStarted = started == 1 || digit != 0 ? 1 : 0;
            int nextState = state;

            if (nextStarted == 1) {
                nextState = updateState(state, digit);
            }

            ans += dfs(pos + 1, nextTight, nextStarted, nextState);
        }

        return memo[pos][tight][started][state] = ans;
    }

    private int updateState(int state, int digit) {
        return state;
    }
}
```

## Counting Range [L, R]

```text
count(R) - count(L - 1)
```

## Interview Problems

- Numbers At Most N Given Digit Set
- Count Numbers With Unique Digits
- Non-negative Integers Without Consecutive Ones
- Count Special Integers

