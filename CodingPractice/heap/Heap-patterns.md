# LeetCode Heap Patterns - Java Boilerplate

## 1. Min Heap

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

minHeap.offer(x);
minHeap.peek();
minHeap.poll();
```

## 2. Max Heap

```java
PriorityQueue<Integer> maxHeap =
    new PriorityQueue<>((a, b) -> Integer.compare(b, a));
```

## 3. Heap Of Pairs

```java
PriorityQueue<int[]> pq =
    new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

pq.offer(new int[] {value, index});
```

## 4. Top K With Min Heap

Keep heap size at most `k`.

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

for (int x : nums) {
    pq.offer(x);

    if (pq.size() > k) {
        pq.poll();
    }
}
```

## 5. K-Way Merge

Use when merging sorted lists/arrays.

```java
PriorityQueue<int[]> pq =
    new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

// {value, row, col}
```

## 6. Two Heaps

Use for median stream.

```java
PriorityQueue<Integer> small =
    new PriorityQueue<>((a, b) -> Integer.compare(b, a));
PriorityQueue<Integer> large = new PriorityQueue<>();
```

## 7. Greedy Heap

Use when repeatedly taking the smallest/largest available item.

```java
while (!pq.isEmpty()) {
    int curr = pq.poll();
    // make greedy choice
}
```

# LeetCode Practice Problems By Pattern

| # | Pattern | Practice Problem |
|---|---|---|
| 1 | Min Heap | [703. Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/) |
| 2 | Max Heap | [1046. Last Stone Weight](https://leetcode.com/problems/last-stone-weight/) |
| 3 | Heap Of Pairs | [973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) |
| 4 | Top K With Min Heap | [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) |
| 5 | K-Way Merge | [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) |
| 6 | Two Heaps | [295. Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) |
| 7 | Greedy Heap | [1642. Furthest Building You Can Reach](https://leetcode.com/problems/furthest-building-you-can-reach/) |

# Recommended Study Order

| Order | Pattern | Practice Problem |
|---|---|---|
| 4 | [Top K With Min Heap](#4-top-k-with-min-heap) | [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) |
| 3 | [Heap Of Pairs](#3-heap-of-pairs) | [973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) |
| 5 | [K-Way Merge](#5-k-way-merge) | [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) |
| 6 | [Two Heaps](#6-two-heaps) | [295. Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) |
| 7 | [Greedy Heap](#7-greedy-heap) | [1642. Furthest Building You Can Reach](https://leetcode.com/problems/furthest-building-you-can-reach/) |
