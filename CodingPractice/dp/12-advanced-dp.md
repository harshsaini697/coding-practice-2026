# 12. Advanced DP Patterns

These patterns appear less often, but they are useful for harder interviews.

## Probability DP

State stores probability instead of max/min/count.

Example state:

```text
dp[i][j] = probability of having i heads after j coin flips
```

Transition:

```text
dp[i][j] = dp[i][j - 1] * P(tails) + dp[i - 1][j - 1] * P(heads)
```

Problems:

- Soup Servings
- New 21 Game
- Knight Probability in Chessboard

## Game DP

Used when two players play optimally.

State:

```text
dp[l][r] = best score difference current player can achieve from nums[l..r]
```

Transition:

```text
takeLeft = nums[l] - dp[l + 1][r]
takeRight = nums[r] - dp[l][r - 1]
dp[l][r] = max(takeLeft, takeRight)
```

Problems:

- Predict the Winner
- Stone Game
- Stone Game II

## LIS With Binary Search

Classic LIS can be `O(n^2)` DP:

```text
dp[i] = longest increasing subsequence ending at i
```

But can be optimized to `O(n log n)` using a tails array.

```java
int lengthOfLIS(int[] nums) {
    int[] tails = new int[nums.length];
    int size = 0;

    for (int x : nums) {
        int l = 0;
        int r = size;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (tails[m] < x) l = m + 1;
            else r = m;
        }

        tails[l] = x;
        if (l == size) size++;
    }

    return size;
}
```

## Optimization Tricks To Know

- rolling arrays
- prefix sums for fast range cost
- monotonic queue optimization
- divide and conquer DP optimization
- convex hull trick

For most interviews, master regular DP first. These tricks are for harder rounds and competitive programming-heavy companies.

