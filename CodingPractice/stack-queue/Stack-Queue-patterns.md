# LeetCode Stack And Queue Patterns - Java Boilerplate

## 1. Stack With ArrayDeque

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(x);
stack.peek();
stack.pop();
```

## 2. Queue With LinkedList

```java
Queue<Integer> q = new LinkedList<>();

q.offer(x);
q.peek();
q.poll();
```

## 3. Monotonic Increasing Stack

Use for previous/next smaller.

```java
Deque<Integer> stack = new ArrayDeque<>();

for (int i = 0; i < nums.length; i++) {
    while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
        stack.pop();
    }

    stack.push(i);
}
```

## 4. Monotonic Decreasing Stack

Use for next greater.

```java
Deque<Integer> stack = new ArrayDeque<>();

for (int i = 0; i < nums.length; i++) {
    while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
        int idx = stack.pop();
        // nums[i] is next greater for idx
    }

    stack.push(i);
}
```

## 5. Monotonic Queue

Use for sliding window maximum/minimum.

```java
Deque<Integer> dq = new ArrayDeque<>();

for (int i = 0; i < nums.length; i++) {
    while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
        dq.pollFirst();
    }

    while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
        dq.pollLast();
    }

    dq.offerLast(i);

    if (i >= k - 1) {
        int max = nums[dq.peekFirst()];
    }
}
```

## 6. Parentheses Stack

```java
Deque<Character> stack = new ArrayDeque<>();

for (char c : s.toCharArray()) {
    if (c == '(') {
        stack.push(c);
    } else if (c == ')') {
        if (stack.isEmpty()) {
            return false;
        }

        stack.pop();
    }
}

return stack.isEmpty();
```

# LeetCode Practice Problems By Pattern

| # | Pattern | Practice Problem |
|---|---|---|
| 1 | Stack With ArrayDeque | [155. Min Stack](https://leetcode.com/problems/min-stack/) |
| 2 | Queue With LinkedList | [933. Number of Recent Calls](https://leetcode.com/problems/number-of-recent-calls/) |
| 3 | Monotonic Increasing Stack | [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) |
| 4 | Monotonic Decreasing Stack | [496. Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) |
| 5 | Monotonic Queue | [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) |
| 6 | Parentheses Stack | [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) |

# Recommended Study Order

| Order | Pattern | Practice Problem |
|---|---|---|
| 6 | [Parentheses Stack](#6-parentheses-stack) | [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) |
| 4 | [Monotonic Decreasing Stack](#4-monotonic-decreasing-stack) | [496. Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) |
| 3 | [Monotonic Increasing Stack](#3-monotonic-increasing-stack) | [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) |
| 5 | [Monotonic Queue](#5-monotonic-queue) | [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) |
