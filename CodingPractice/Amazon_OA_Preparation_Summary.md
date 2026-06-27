# Amazon OA Preparation Summary

This document summarizes the Amazon Online Assessment (OA) problems and
solution approaches we discussed.

## 1. Maximum Coins from Segments (Sweep Line / Difference Array)

-   Build a difference array instead of expanding every segment.
-   Sort change points (coordinate compression if needed).
-   Compute prefix sums to reconstruct values.
-   Use a sliding window of length `k` to find the maximum.
-   **Key patterns:** Difference Array, Sweep Line, Prefix Sum.

## 2. Sum of Total Strength of Wizards

-   Compute prefix sums and prefix-of-prefix sums.
-   Use monotonic stacks to find previous/next smaller elements.
-   Treat each element as the minimum of its contribution interval.
-   Complexity: `O(N)`
-   **Key patterns:** Contribution Technique, Monotonic Stack, Prefix of
    Prefix.

## 3. Car Pooling

-   Difference array + prefix sum.
-   Verify capacity is never exceeded.
-   Complexity: `O(N + maxLocation)` or `O(N log N)`.
-   Pattern: Sweep Line.

## 4. Sliding Window Maximum

-   Maintain a decreasing deque.
-   Remove expired indices.
-   Remove smaller elements from the back.
-   Front always stores the maximum.
-   Complexity: `O(N)`.

## 5. Top K Frequent Elements

-   HashMap + Bucket Sort (preferred).
-   Alternative: Min Heap.

## 6. Category Hierarchy Design

-   Composite Pattern.
-   Strategy Pattern for filters.
-   Classes: Category, Product, Filter, CategoryService.

## 7. Distributed Car Pooling

-   Shard data.
-   Local difference arrays.
-   Merge events.
-   Global prefix sum.

## 8. Thread Migration Framework

-   Durable workflows.
-   Retry + Checkpointing.
-   Idempotency.
-   Resume after failure.

## 9. Backup Store Validation

-   Random sampling.
-   Reconstruct thread.
-   Compare with primary.

## 10. L1 Cache Design

-   Cache before DB.
-   Populate on miss.
-   TTL, invalidation, write-through, write-around, write-back.

## 11. AWS CDK Infrastructure

-   Infrastructure as Code.
-   Reusable CDK constructs.
-   Standardized deployments.

## 12. Prefix Sum Family

-   Prefix Sum
-   Difference Array
-   Prefix of Prefix
-   2D Prefix Sum

## 13. Monotonic Stack Family

-   Previous/Next Smaller
-   Previous/Next Greater
-   Histogram
-   Stock Span
-   Rain Water

## 14. Sliding Window Family

-   Fixed & Variable Windows
-   Frequency Map
-   Monotonic Deque

## 15. Sweep Line Family

-   Difference Arrays
-   Coordinate Compression
-   Event Processing

## 16. Amazon Priority Patterns

1.  Prefix Sum + Difference Array
2.  Sliding Window
3.  Monotonic Stack / Deque
4.  Heap / Bucket Sort
5.  Binary Search on Answer
6.  Graphs
7.  Greedy
8.  Dynamic Programming
9.  Trie
10. Backtracking

## Interview Takeaways

Recognize patterns instead of memorizing solutions. Explain trade-offs,
analyze complexity, write clean code, and consider edge cases.
