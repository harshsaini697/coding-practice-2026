# LeetCode Sorting And Searching Patterns - Java Boilerplate

## 1. Binary Search

```java
int left = 0;
int right = nums.length - 1;

while (left <= right) {
    int mid = left + (right - left) / 2;

    if (nums[mid] == target) {
        return mid;
    } else if (nums[mid] < target) {
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}

return -1;
```

## 2. Lower Bound

First index where `nums[i] >= target`.

```java
int left = 0;
int right = nums.length;

while (left < right) {
    int mid = left + (right - left) / 2;

    if (nums[mid] >= target) {
        right = mid;
    } else {
        left = mid + 1;
    }
}

return left;
```

## 3. Upper Bound

First index where `nums[i] > target`.

```java
int left = 0;
int right = nums.length;

while (left < right) {
    int mid = left + (right - left) / 2;

    if (nums[mid] > target) {
        right = mid;
    } else {
        left = mid + 1;
    }
}

return left;
```

## 4. Binary Search On Answer

Use when answers are monotonic.

```java
int left = 1;
int right = 1_000_000_000;

while (left < right) {
    int mid = left + (right - left) / 2;

    if (canDo(mid)) {
        right = mid;
    } else {
        left = mid + 1;
    }
}

return left;
```

## 5. Sorting + Scan

```java
Arrays.sort(nums);

for (int i = 0; i < nums.length; i++) {
    // scan sorted values
}
```

## 6. Custom Comparator

```java
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
```

For strings:

```java
Arrays.sort(words, (a, b) -> a.length() - b.length());
```

## 7. Quickselect

Use for kth largest/smallest.

```java
private int partition(int[] nums, int left, int right) {
    int pivot = nums[right];
    int write = left;

    for (int i = left; i < right; i++) {
        if (nums[i] <= pivot) {
            swap(nums, i, write);
            write++;
        }
    }

    swap(nums, write, right);
    return write;
}
```

# LeetCode Practice Problems By Pattern

| # | Pattern | Practice Problem |
|---|---|---|
| 1 | Binary Search | [704. Binary Search](https://leetcode.com/problems/binary-search/) |
| 2 | Lower Bound | [35. Search Insert Position](https://leetcode.com/problems/search-insert-position/) |
| 3 | Upper Bound | [34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) |
| 4 | Binary Search On Answer | [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) |
| 5 | Sorting + Scan | [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/) |
| 6 | Custom Comparator | [179. Largest Number](https://leetcode.com/problems/largest-number/) |
| 7 | Quickselect | [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) |

# Recommended Study Order

| Order | Pattern | Practice Problem |
|---|---|---|
| 1 | [Binary Search](#1-binary-search) | [704. Binary Search](https://leetcode.com/problems/binary-search/) |
| 2 | [Lower Bound](#2-lower-bound) | [35. Search Insert Position](https://leetcode.com/problems/search-insert-position/) |
| 4 | [Binary Search On Answer](#4-binary-search-on-answer) | [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) |
| 5 | [Sorting + Scan](#5-sorting--scan) | [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/) |
| 7 | [Quickselect](#7-quickselect) | [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) |
