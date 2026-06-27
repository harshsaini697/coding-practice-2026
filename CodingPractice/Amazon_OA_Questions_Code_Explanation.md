# Amazon OA Questions, Approaches & Code (Java)

> This file contains the coding questions we discussed, the core idea
> behind each solution, and a Java template.

------------------------------------------------------------------------

# 1. Car Pooling (LeetCode 1094)

## Pattern

-   Difference Array
-   Prefix Sum
-   Sweep Line

## Idea

Instead of simulating every passenger, record only where passengers
enter and leave.

    diff[start] += passengers
    diff[end]   -= passengers

Taking the prefix sum reconstructs the number of passengers at every
point.

## Code

``` java
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[1002];

        for (int[] trip : trips) {
            diff[trip[1]] += trip[0];
            diff[trip[2]] -= trip[0];
        }

        int current = 0;

        for (int x : diff) {
            current += x;
            if (current > capacity)
                return false;
        }

        return true;
    }
}
```

Complexity

-   Time: O(N + MaxLocation)
-   Space: O(MaxLocation)

------------------------------------------------------------------------

# 2. Sliding Window Maximum (LeetCode 239)

## Pattern

Monotonic Deque

## Idea

Maintain indices in decreasing order of values.

-   Remove expired indices.
-   Remove smaller elements from the back.
-   Front always contains the maximum.

## Code

``` java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        int[] ans = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {

            while (!dq.isEmpty() && dq.peekFirst() <= i - k)
                dq.pollFirst();

            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i])
                dq.pollLast();

            dq.offerLast(i);

            if (i >= k - 1)
                ans[i - k + 1] = nums[dq.peekFirst()];
        }

        return ans;
    }
}
```

Complexity

-   Time: O(N)
-   Space: O(K)

------------------------------------------------------------------------

# 3. Top K Frequent Elements

## Pattern

HashMap + Bucket Sort

## Idea

Count frequencies, bucket numbers by frequency, then iterate buckets
backwards.

## Code

``` java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer,Integer> freq = new HashMap<>();

        for(int n : nums)
            freq.put(n, freq.getOrDefault(n,0)+1);

        List<Integer>[] bucket = new ArrayList[nums.length+1];

        for(int key : freq.keySet()) {
            int f = freq.get(key);

            if(bucket[f]==null)
                bucket[f]=new ArrayList<>();

            bucket[f].add(key);
        }

        int[] ans = new int[k];
        int idx=0;

        for(int i=bucket.length-1;i>=0 && idx<k;i--){
            if(bucket[i]==null) continue;

            for(int val : bucket[i]){
                ans[idx++]=val;
                if(idx==k) break;
            }
        }

        return ans;
    }
}
```

Complexity

-   Time: O(N)
-   Space: O(N)

------------------------------------------------------------------------

# 4. Sum of Total Strength of Wizards

## Pattern

-   Monotonic Stack
-   Prefix Sum
-   Prefix of Prefix

## Idea

Each element contributes as the minimum exactly once.

Steps

1.  Prefix Sum
2.  Prefix of Prefix
3.  Previous Smaller
4.  Next Smaller
5.  Contribution formula

Overall complexity

-   O(N)

------------------------------------------------------------------------

# 5. Maximum Coins from Segments

## Pattern

-   Difference Array
-   Coordinate Compression
-   Sweep Line
-   Sliding Window

## Idea

Never expand every coordinate.

1.  Record events.
2.  Sort coordinates.
3.  Compute prefix values.
4.  Slide a window of size K.

Useful when coordinates are very large.

------------------------------------------------------------------------

# 6. Category Hierarchy Design

## Pattern

-   Composite Pattern
-   Strategy Pattern

## Core Classes

``` text
Category
 ├── children
 └── products

Product

Filter
 ├── NameFilter
 ├── BrandFilter
 └── PriceFilter

CategoryService
```

Reason

Adding new filters never changes existing code.

------------------------------------------------------------------------

# General Interview Tips

## Difference Array

Use when updates affect ranges.

## Prefix Sum

Use for repeated range sum queries.

## Monotonic Stack

Use for previous/next greater or smaller element problems.

## Sliding Window

Use for contiguous subarray problems.

## Sweep Line

Convert intervals into events.

## Bucket Sort

Prefer when frequency \<= N.

## Common Amazon Follow-up Questions

-   Can you reduce the memory?
-   Can you make it O(N)?
-   What happens for duplicate values?
-   How would this work in a distributed system?
-   How would you test this?
-   What are the edge cases?
