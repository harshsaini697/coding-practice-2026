# LeetCode Greedy Patterns - Java Boilerplate

## 1. Sort Then Choose

Use when a local choice becomes obvious after sorting.

```java
Arrays.sort(nums);

for (int x : nums) {
    // choose smallest/largest available
}
```

## 2. Intervals Greedy

Sort by end when selecting maximum non-overlapping intervals.

```java
Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

int count = 0;
int end = Integer.MIN_VALUE;

for (int[] interval : intervals) {
    if (interval[0] >= end) {
        count++;
        end = interval[1];
    }
}
```

## 3. Jump Greedy

Track farthest reachable index.

```java
int farthest = 0;

for (int i = 0; i < nums.length; i++) {
    if (i > farthest) {
        return false;
    }

    farthest = Math.max(farthest, i + nums[i]);
}

return true;
```

## 4. Two Pointers Greedy

Use sorted data and choose left/right based on the goal.

```java
Arrays.sort(nums);

int left = 0;
int right = nums.length - 1;

while (left <= right) {
    // choose smaller or larger
}
```

## 5. Heap Greedy

Use when repeatedly choosing the best available option.

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

while (!pq.isEmpty()) {
    int best = pq.poll();
    // apply greedy choice
}
```

## 6. Prefix Feasibility Greedy

Use when every prefix must satisfy a condition.

```java
int balance = 0;

for (char c : s.toCharArray()) {
    balance += c == '(' ? 1 : -1;

    if (balance < 0) {
        return false;
    }
}
```

# LeetCode Practice Problems By Pattern

| # | Pattern | Practice Problem |
|---|---|---|
| 1 | Sort Then Choose | [455. Assign Cookies](https://leetcode.com/problems/assign-cookies/) |
| 2 | Intervals Greedy | [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) |
| 3 | Jump Greedy | [55. Jump Game](https://leetcode.com/problems/jump-game/) |
| 4 | Two Pointers Greedy | [881. Boats to Save People](https://leetcode.com/problems/boats-to-save-people/) |
| 5 | Heap Greedy | [1642. Furthest Building You Can Reach](https://leetcode.com/problems/furthest-building-you-can-reach/) |
| 6 | Prefix Feasibility Greedy | [678. Valid Parenthesis String](https://leetcode.com/problems/valid-parenthesis-string/) |

# Recommended Study Order

| Order | Pattern | Practice Problem |
|---|---|---|
| 1 | [Sort Then Choose](#1-sort-then-choose) | [455. Assign Cookies](https://leetcode.com/problems/assign-cookies/) |
| 3 | [Jump Greedy](#3-jump-greedy) | [55. Jump Game](https://leetcode.com/problems/jump-game/) |
| 2 | [Intervals Greedy](#2-intervals-greedy) | [435. Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) |
| 4 | [Two Pointers Greedy](#4-two-pointers-greedy) | [881. Boats to Save People](https://leetcode.com/problems/boats-to-save-people/) |
| 5 | [Heap Greedy](#5-heap-greedy) | [1642. Furthest Building You Can Reach](https://leetcode.com/problems/furthest-building-you-can-reach/) |
