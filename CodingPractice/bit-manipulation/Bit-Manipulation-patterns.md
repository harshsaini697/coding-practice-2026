# LeetCode Bit Manipulation Patterns - Java Boilerplate

## 1. Check Bit

```java
boolean isSet = ((num >> bit) & 1) == 1;
```

## 2. Set Bit

```java
num = num | (1 << bit);
```

## 3. Clear Bit

```java
num = num & ~(1 << bit);
```

## 4. Toggle Bit

```java
num = num ^ (1 << bit);
```

## 5. XOR For Single Number

```java
int ans = 0;

for (int x : nums) {
    ans ^= x;
}
```

## 6. Count Bits

```java
int count = 0;

while (num != 0) {
    num &= num - 1;
    count++;
}
```

## 7. Bitmask Subsets

```java
int n = nums.length;

for (int mask = 0; mask < (1 << n); mask++) {
    List<Integer> curr = new ArrayList<>();

    for (int i = 0; i < n; i++) {
        if (((mask >> i) & 1) == 1) {
            curr.add(nums[i]);
        }
    }
}
```

## 8. DP With Bitmask

Use when state is a subset of items.

```java
int totalMasks = 1 << n;
int[] dp = new int[totalMasks];
Arrays.fill(dp, Integer.MAX_VALUE / 2);
dp[0] = 0;

for (int mask = 0; mask < totalMasks; mask++) {
    for (int i = 0; i < n; i++) {
        if (((mask >> i) & 1) == 0) {
            int next = mask | (1 << i);
            dp[next] = Math.min(dp[next], dp[mask] + cost);
        }
    }
}
```

# LeetCode Practice Problems By Pattern

| # | Pattern | Practice Problem |
|---|---|---|
| 1 | Check / Set Bits | [191. Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/) |
| 2 | XOR For Single Number | [136. Single Number](https://leetcode.com/problems/single-number/) |
| 3 | Count Bits | [338. Counting Bits](https://leetcode.com/problems/counting-bits/) |
| 4 | Bitmask Subsets | [78. Subsets](https://leetcode.com/problems/subsets/) |
| 5 | DP With Bitmask | [1879. Minimum XOR Sum of Two Arrays](https://leetcode.com/problems/minimum-xor-sum-of-two-arrays/) |

# Recommended Study Order

| Order | Pattern | Practice Problem |
|---|---|---|
| 2 | [XOR For Single Number](#5-xor-for-single-number) | [136. Single Number](https://leetcode.com/problems/single-number/) |
| 3 | [Count Bits](#6-count-bits) | [338. Counting Bits](https://leetcode.com/problems/counting-bits/) |
| 4 | [Bitmask Subsets](#7-bitmask-subsets) | [78. Subsets](https://leetcode.com/problems/subsets/) |
| 5 | [DP With Bitmask](#8-dp-with-bitmask) | [1879. Minimum XOR Sum of Two Arrays](https://leetcode.com/problems/minimum-xor-sum-of-two-arrays/) |
