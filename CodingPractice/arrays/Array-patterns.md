# LeetCode Array Patterns - Java Boilerplate

## 1. Basic Linear Scan

Use when checking every element once.

```java
for (int i = 0; i < nums.length; i++) {
    // use nums[i]
}
```

```java
int max = nums[0];

for (int x : nums) {
    max = Math.max(max, x);
}
```

## 2. Count / Frequency Array

Use when values are in a small known range.

```java
int[] freq = new int[101];

for (int x : nums) {
    freq[x]++;
}
```

For lowercase letters:

```java
int[] freq = new int[26];

for (char c : s.toCharArray()) {
    freq[c - 'a']++;
}
```

## 3. HashMap Frequency

Use when values can be large, negative, or unknown.

```java
Map<Integer, Integer> freq = new HashMap<>();

for (int x : nums) {
    freq.put(x, freq.getOrDefault(x, 0) + 1);
}
```

```java
for (int key : freq.keySet()) {
    int count = freq.get(key);
}
```

## 4. HashSet Seen Values

Use for duplicates, existence checks, uniqueness.

```java
Set<Integer> seen = new HashSet<>();

for (int x : nums) {
    if (seen.contains(x)) {
        // duplicate found
    }

    seen.add(x);
}
```

## 5. Prefix Sum

Use for range sum, subarray sum, or sum from `i` to `j`.

```java
int n = nums.length;
int[] prefix = new int[n + 1];

for (int i = 0; i < n; i++) {
    prefix[i + 1] = prefix[i] + nums[i];
}

// sum from l to r inclusive
int sum = prefix[r + 1] - prefix[l];
```

## 6. Prefix Sum + HashMap

Use for counting subarrays with sum `k`.

```java
Map<Integer, Integer> map = new HashMap<>();
map.put(0, 1);

int prefix = 0;
int count = 0;

for (int x : nums) {
    prefix += x;

    if (map.containsKey(prefix - k)) {
        count += map.get(prefix - k);
    }

    map.put(prefix, map.getOrDefault(prefix, 0) + 1);
}
```

## 7. Kadane's Algorithm

Use for maximum subarray sum.

```java
int curr = nums[0];
int best = nums[0];

for (int i = 1; i < nums.length; i++) {
    curr = Math.max(nums[i], curr + nums[i]);
    best = Math.max(best, curr);
}
```

## 8. Two Pointers From Both Ends

Use on sorted arrays, pair sum, or palindrome-style checks.

```java
int left = 0;
int right = nums.length - 1;

while (left < right) {
    int sum = nums[left] + nums[right];

    if (sum == target) {
        // found
        left++;
        right--;
    } else if (sum < target) {
        left++;
    } else {
        right--;
    }
}
```

## 9. Same-Direction Two Pointers

Use for removing duplicates, compacting arrays, or partitioning.

```java
int write = 0;

for (int read = 0; read < nums.length; read++) {
    if (shouldKeep(nums[read])) {
        nums[write] = nums[read];
        write++;
    }
}

// new valid length = write
```

Example: move non-zero values forward.

```java
int write = 0;

for (int read = 0; read < nums.length; read++) {
    if (nums[read] != 0) {
        nums[write++] = nums[read];
    }
}
```

## 10. Sliding Window - Fixed Size

Use for subarrays of exactly size `k`.

```java
int windowSum = 0;
int best = Integer.MIN_VALUE;

for (int right = 0; right < nums.length; right++) {
    windowSum += nums[right];

    if (right >= k - 1) {
        best = Math.max(best, windowSum);

        int left = right - k + 1;
        windowSum -= nums[left];
    }
}
```

## 11. Sliding Window - Variable Size

Use when expanding/shrinking while a condition is invalid.

```java
int left = 0;
int sum = 0;
int best = 0;

for (int right = 0; right < nums.length; right++) {
    sum += nums[right];

    while (sum > target) {
        sum -= nums[left];
        left++;
    }

    best = Math.max(best, right - left + 1);
}
```

## 12. Count Subarrays Ending At Each Index

Use when each index contributes several valid subarrays.

```java
long res = 0;
long len = 0;

for (int i = 0; i < nums.length; i++) {
    if (i > 0 && nums[i] == nums[i - 1]) {
        len = 1;
    } else {
        len++;
    }

    res += len;
}
```

Mental model:

```text
len = number of valid subarrays ending at i
res += len
```

## 13. Monotonic Stack - Next Greater Element

Use for next greater / next smaller problems.

```java
int n = nums.length;
int[] nextGreater = new int[n];
Arrays.fill(nextGreater, -1);

Deque<Integer> stack = new ArrayDeque<>(); // stores indexes

for (int i = 0; i < n; i++) {
    while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
        int idx = stack.pop();
        nextGreater[idx] = nums[i];
    }

    stack.push(i);
}
```

## 14. Monotonic Stack - Previous Smaller

```java
Deque<Integer> stack = new ArrayDeque<>();
int[] prevSmaller = new int[nums.length];
Arrays.fill(prevSmaller, -1);

for (int i = 0; i < nums.length; i++) {
    while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
        stack.pop();
    }

    if (!stack.isEmpty()) {
        prevSmaller[i] = stack.peek();
    }

    stack.push(i);
}
```

## 15. Binary Search On Sorted Array

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

## 16. Binary Search - Lower Bound

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

## 17. Binary Search On Answer

Use when the answer is a number and you can check if a candidate works.

```java
int left = 1;
int right = 1_000_000_000;

while (left < right) {
    int mid = left + (right - left) / 2;

    if (canDo(mid, nums)) {
        right = mid;
    } else {
        left = mid + 1;
    }
}

return left;
```

```java
private boolean canDo(int guess, int[] nums) {
    // return true if guess works
    return true;
}
```

## 18. Sorting + Scan

Use when order does not matter or grouping is easier after sorting.

```java
Arrays.sort(nums);

for (int i = 0; i < nums.length; i++) {
    // process sorted nums
}
```

Grouping after sort:

```java
Arrays.sort(nums);

int i = 0;

while (i < nums.length) {
    int value = nums[i];
    int count = 0;

    while (i < nums.length && nums[i] == value) {
        count++;
        i++;
    }

    // value appears count times
}
```

## 19. Intervals

Sort by start, then merge.

```java
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

List<int[]> merged = new ArrayList<>();

for (int[] interval : intervals) {
    if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
        merged.add(interval);
    } else {
        int[] last = merged.get(merged.size() - 1);
        last[1] = Math.max(last[1], interval[1]);
    }
}
```

## 20. Difference Array

Use for many range updates.

```java
int n = nums.length;
int[] diff = new int[n + 1];

for (int[] update : updates) {
    int l = update[0];
    int r = update[1];
    int val = update[2];

    diff[l] += val;

    if (r + 1 < diff.length) {
        diff[r + 1] -= val;
    }
}

int curr = 0;

for (int i = 0; i < n; i++) {
    curr += diff[i];
    nums[i] += curr;
}
```

## 21. In-Place Marking

Use when values are from `1` to `n`.

```java
for (int i = 0; i < nums.length; i++) {
    int idx = Math.abs(nums[i]) - 1;

    if (nums[idx] < 0) {
        // seen before
    } else {
        nums[idx] = -nums[idx];
    }
}
```

## 22. Cyclic Sort

Use when numbers are in range `1...n`.

```java
int i = 0;

while (i < nums.length) {
    int correctIdx = nums[i] - 1;

    if (nums[i] >= 1 && nums[i] <= nums.length && nums[i] != nums[correctIdx]) {
        int temp = nums[i];
        nums[i] = nums[correctIdx];
        nums[correctIdx] = temp;
    } else {
        i++;
    }
}
```

Then scan:

```java
for (int j = 0; j < nums.length; j++) {
    if (nums[j] != j + 1) {
        return j + 1;
    }
}
```

## 23. Matrix Traversal

```java
int rows = grid.length;
int cols = grid[0].length;

for (int r = 0; r < rows; r++) {
    for (int c = 0; c < cols; c++) {
        // grid[r][c]
    }
}
```

Directions:

```java
int[][] dirs = {
    {1, 0},
    {-1, 0},
    {0, 1},
    {0, -1}
};

for (int[] d : dirs) {
    int nr = r + d[0];
    int nc = c + d[1];

    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
        // valid neighbor
    }
}
```

## 24. Backtracking Subsets

Use for all subsequences/subsets.

```java
List<List<Integer>> res = new ArrayList<>();

backtrack(nums, 0, new ArrayList<>(), res);

private void backtrack(
    int[] nums,
    int index,
    List<Integer> current,
    List<List<Integer>> res
) {
    if (index == nums.length) {
        res.add(new ArrayList<>(current));
        return;
    }

    // do not take
    backtrack(nums, index + 1, current, res);

    // take
    current.add(nums[index]);
    backtrack(nums, index + 1, current, res);
    current.remove(current.size() - 1);
}
```

## 25. Backtracking Permutations

```java
List<List<Integer>> res = new ArrayList<>();
boolean[] used = new boolean[nums.length];

backtrack(nums, used, new ArrayList<>(), res);

private void backtrack(
    int[] nums,
    boolean[] used,
    List<Integer> current,
    List<List<Integer>> res
) {
    if (current.size() == nums.length) {
        res.add(new ArrayList<>(current));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) {
            continue;
        }

        used[i] = true;
        current.add(nums[i]);

        backtrack(nums, used, current, res);

        current.remove(current.size() - 1);
        used[i] = false;
    }
}
```

## 26. Min / Max Heap

Min heap:

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

minHeap.offer(x);
minHeap.peek();
minHeap.poll();
```

Max heap:

```java
PriorityQueue<Integer> maxHeap =
    new PriorityQueue<>((a, b) -> Integer.compare(b, a));
```

Heap of pairs:

```java
PriorityQueue<int[]> pq =
    new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
```

## 27. Top K Elements

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

for (int x : nums) {
    minHeap.offer(x);

    if (minHeap.size() > k) {
        minHeap.poll();
    }
}

// heap contains k largest elements
```

## 28. Quickselect

Use for kth largest / kth smallest.

```java
public int findKthLargest(int[] nums, int k) {
    int target = nums.length - k;
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
        int pivotIndex = partition(nums, left, right);

        if (pivotIndex == target) {
            return nums[pivotIndex];
        } else if (pivotIndex < target) {
            left = pivotIndex + 1;
        } else {
            right = pivotIndex - 1;
        }
    }

    return -1;
}

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

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

## 29. DP On Array

Use when answer at `i` depends on previous answers.

```java
int n = nums.length;
int[] dp = new int[n];

dp[0] = nums[0];

for (int i = 1; i < n; i++) {
    dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
}
```

Constant space:

```java
int prev = nums[0];

for (int i = 1; i < nums.length; i++) {
    int curr = Math.max(nums[i], prev + nums[i]);
    prev = curr;
}
```

## 30. Left / Right Precomputation

Use when each index needs information from both sides.

```java
int n = nums.length;
int[] left = new int[n];
int[] right = new int[n];

left[0] = nums[0];

for (int i = 1; i < n; i++) {
    left[i] = Math.max(left[i - 1], nums[i]);
}

right[n - 1] = nums[n - 1];

for (int i = n - 2; i >= 0; i--) {
    right[i] = Math.max(right[i + 1], nums[i]);
}
```

## 31. Product Except Self

```java
int n = nums.length;
int[] res = new int[n];

res[0] = 1;

for (int i = 1; i < n; i++) {
    res[i] = res[i - 1] * nums[i - 1];
}

int suffix = 1;

for (int i = n - 1; i >= 0; i--) {
    res[i] *= suffix;
    suffix *= nums[i];
}
```

## 32. All Subarrays

Subarray means contiguous.

```java
for (int start = 0; start < nums.length; start++) {
    for (int end = start; end < nums.length; end++) {
        for (int i = start; i <= end; i++) {
            // nums[i] is inside current subarray
        }
    }
}
```

## 33. All Subsequences

Subsequence can skip elements.

```java
List<List<Integer>> res = new ArrayList<>();

backtrack(nums, 0, new ArrayList<>(), res);
```

Subarray:

```text
[1, 2], [2, 3]
```

Subsequence:

```text
[1, 3]
```

## 34. All Pairs

```java
for (int i = 0; i < nums.length; i++) {
    for (int j = i + 1; j < nums.length; j++) {
        // pair nums[i], nums[j]
    }
}
```

## 35. All Triplets

```java
for (int i = 0; i < nums.length; i++) {
    for (int j = i + 1; j < nums.length; j++) {
        for (int k = j + 1; k < nums.length; k++) {
            // triplet nums[i], nums[j], nums[k]
        }
    }
}
```

## 36. Three Sum Pattern

```java
Arrays.sort(nums);

List<List<Integer>> res = new ArrayList<>();

for (int i = 0; i < nums.length - 2; i++) {
    if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
    }

    int left = i + 1;
    int right = nums.length - 1;

    while (left < right) {
        int sum = nums[i] + nums[left] + nums[right];

        if (sum == 0) {
            res.add(Arrays.asList(nums[i], nums[left], nums[right]));

            left++;
            right--;

            while (left < right && nums[left] == nums[left - 1]) {
                left++;
            }

            while (left < right && nums[right] == nums[right + 1]) {
                right--;
            }
        } else if (sum < 0) {
            left++;
        } else {
            right--;
        }
    }
}
```

# Mental Shortcuts

## Subarray Sum

Think:

```text
prefix sum / sliding window
```

## Longest Contiguous

Think:

```text
sliding window
```

## Sorted Array

Think:

```text
two pointers / binary search
```

## Next Greater / Next Smaller

Think:

```text
monotonic stack
```

## Top K / Kth Largest

Think:

```text
heap / quickselect
```

## Range Update / Many Queries

Think:

```text
prefix sum / difference array
```

## Values From 1 To N

Think:

```text
cyclic sort / in-place marking
```

# LeetCode Practice Problems By Pattern

| # | Pattern | Practice Problem |
|---|---|---|
| 1 | Basic Linear Scan | [1295. Find Numbers with Even Number of Digits](https://leetcode.com/problems/find-numbers-with-even-number-of-digits/) |
| 2 | Frequency Array | [1365. How Many Numbers Are Smaller Than the Current Number](https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/) |
| 3 | HashMap Frequency | [1. Two Sum](https://leetcode.com/problems/two-sum/) |
| 4 | HashSet Seen Values | [217. Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) |
| 5 | Prefix Sum | [303. Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/) |
| 6 | Prefix Sum + HashMap | [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) |
| 7 | Kadane's Algorithm | [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) |
| 8 | Two Pointers From Ends | [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) |
| 9 | Same-Direction Two Pointers | [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) |
| 10 | Fixed Sliding Window | [643. Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i/) |
| 11 | Variable Sliding Window | [209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) |
| 12 | Count Subarrays Ending at `i` | [3101. Count Alternating Subarrays](https://leetcode.com/problems/count-alternating-subarrays/) |
| 13 | Monotonic Stack - Next Greater | [496. Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) |
| 14 | Monotonic Stack - Previous Smaller | [907. Sum of Subarray Minimums](https://leetcode.com/problems/sum-of-subarray-minimums/) |
| 15 | Binary Search Sorted Array | [704. Binary Search](https://leetcode.com/problems/binary-search/) |
| 16 | Lower Bound Binary Search | [35. Search Insert Position](https://leetcode.com/problems/search-insert-position/) |
| 17 | Binary Search on Answer | [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) |
| 18 | Sorting + Scan | [242. Valid Anagram](https://leetcode.com/problems/valid-anagram/) |
| 19 | Intervals | [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/) |
| 20 | Difference Array | [370. Range Addition](https://leetcode.com/problems/range-addition/) |
| 21 | In-Place Marking | [448. Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) |
| 22 | Cyclic Sort | [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive/) |
| 23 | Matrix Traversal | [200. Number of Islands](https://leetcode.com/problems/number-of-islands/) |
| 24 | Backtracking Subsets | [78. Subsets](https://leetcode.com/problems/subsets/) |
| 25 | Backtracking Permutations | [46. Permutations](https://leetcode.com/problems/permutations/) |
| 26 | Min / Max Heap | [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) |
| 27 | Top K Elements | [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) |
| 28 | Quickselect | [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) |
| 29 | DP on Array | [198. House Robber](https://leetcode.com/problems/house-robber/) |
| 30 | Left / Right Precomputation | [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) |
| 31 | Product Except Self | [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) |
| 32 | All Subarrays | [152. Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/) |
| 33 | All Subsequences | [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/) |
| 34 | All Pairs | [1. Two Sum](https://leetcode.com/problems/two-sum/) |
| 35 | All Triplets | [15. 3Sum](https://leetcode.com/problems/3sum/) |
| 36 | Three Sum Pattern | [15. 3Sum](https://leetcode.com/problems/3sum/) |

# Recommended Study Order

Do these first to build the strongest array foundation:

```text
1, 4, 3, 5, 6, 7, 8, 9, 10, 11, 12,
15, 16, 17, 13, 14, 19, 21, 22, 31, 36
```

Expanded:

| Order | Pattern | Practice Problem |
|---|---|---|
| 1 | [Basic Linear Scan](#1-basic-linear-scan) | [1295. Find Numbers with Even Number of Digits](https://leetcode.com/problems/find-numbers-with-even-number-of-digits/) |
| 4 | [HashSet Seen Values](#4-hashset-seen-values) | [217. Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) |
| 3 | [HashMap Frequency](#3-hashmap-frequency) | [1. Two Sum](https://leetcode.com/problems/two-sum/) |
| 5 | [Prefix Sum](#5-prefix-sum) | [303. Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/) |
| 6 | [Prefix Sum + HashMap](#6-prefix-sum--hashmap) | [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) |
| 7 | [Kadane's Algorithm](#7-kadanes-algorithm) | [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) |
| 8 | [Two Pointers From Ends](#8-two-pointers-from-both-ends) | [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) |
| 9 | [Same-Direction Two Pointers](#9-same-direction-two-pointers) | [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) |
| 10 | [Fixed Sliding Window](#10-sliding-window---fixed-size) | [643. Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i/) |
| 11 | [Variable Sliding Window](#11-sliding-window---variable-size) | [209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) |
| 12 | [Count Subarrays Ending at `i`](#12-count-subarrays-ending-at-each-index) | [3101. Count Alternating Subarrays](https://leetcode.com/problems/count-alternating-subarrays/) |
| 15 | [Binary Search Sorted Array](#15-binary-search-on-sorted-array) | [704. Binary Search](https://leetcode.com/problems/binary-search/) |
| 16 | [Lower Bound Binary Search](#16-binary-search---lower-bound) | [35. Search Insert Position](https://leetcode.com/problems/search-insert-position/) |
| 17 | [Binary Search on Answer](#17-binary-search-on-answer) | [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) |
| 13 | [Monotonic Stack - Next Greater](#13-monotonic-stack---next-greater-element) | [496. Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) |
| 14 | [Monotonic Stack - Previous Smaller](#14-monotonic-stack---previous-smaller) | [907. Sum of Subarray Minimums](https://leetcode.com/problems/sum-of-subarray-minimums/) |
| 19 | [Intervals](#19-intervals) | [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/) |
| 21 | [In-Place Marking](#21-in-place-marking) | [448. Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) |
| 22 | [Cyclic Sort](#22-cyclic-sort) | [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive/) |
| 31 | [Product Except Self](#31-product-except-self) | [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) |
| 36 | [Three Sum Pattern](#36-three-sum-pattern) | [15. 3Sum](https://leetcode.com/problems/3sum/) |
