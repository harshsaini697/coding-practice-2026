# LeetCode Linked List Patterns - Java Boilerplate

## 1. ListNode Definition

```java
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}
```

## 2. Dummy Head

Use when building or modifying a list from the front.

```java
ListNode dummy = new ListNode(0);
ListNode curr = dummy;

while (condition) {
    curr.next = new ListNode(value);
    curr = curr.next;
}

return dummy.next;
```

## 3. Fast / Slow Pointers

Use for middle node and cycle detection.

```java
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
```

## 4. Reverse Linked List

```java
ListNode prev = null;
ListNode curr = head;

while (curr != null) {
    ListNode next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
}

return prev;
```

## 5. Merge Two Sorted Lists

```java
ListNode dummy = new ListNode(0);
ListNode tail = dummy;

while (l1 != null && l2 != null) {
    if (l1.val <= l2.val) {
        tail.next = l1;
        l1 = l1.next;
    } else {
        tail.next = l2;
        l2 = l2.next;
    }

    tail = tail.next;
}

tail.next = l1 != null ? l1 : l2;
return dummy.next;
```

## 6. Remove Node With Dummy

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode curr = dummy;

while (curr.next != null) {
    if (curr.next.val == target) {
        curr.next = curr.next.next;
    } else {
        curr = curr.next;
    }
}

return dummy.next;
```

## 7. Find Intersection

Switch heads when a pointer reaches null.

```java
ListNode a = headA;
ListNode b = headB;

while (a != b) {
    a = a == null ? headB : a.next;
    b = b == null ? headA : b.next;
}

return a;
```

## 8. Cycle Detection

```java
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;

    if (slow == fast) {
        return true;
    }
}

return false;
```

## 9. Reverse In Groups

Use when reversing a fixed-size segment.

```java
private ListNode reverse(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }

    return prev;
}
```

# LeetCode Practice Problems By Pattern

| # | Pattern | Practice Problem |
|---|---|---|
| 1 | ListNode Definition | [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) |
| 2 | Dummy Head | [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers/) |
| 3 | Fast / Slow Pointers | [876. Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/) |
| 4 | Reverse Linked List | [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) |
| 5 | Merge Two Sorted Lists | [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) |
| 6 | Remove Node With Dummy | [203. Remove Linked List Elements](https://leetcode.com/problems/remove-linked-list-elements/) |
| 7 | Find Intersection | [160. Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/) |
| 8 | Cycle Detection | [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) |
| 9 | Reverse In Groups | [25. Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/) |

# Recommended Study Order

| Order | Pattern | Practice Problem |
|---|---|---|
| 4 | [Reverse Linked List](#4-reverse-linked-list) | [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) |
| 2 | [Dummy Head](#2-dummy-head) | [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers/) |
| 3 | [Fast / Slow Pointers](#3-fast--slow-pointers) | [876. Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/) |
| 8 | [Cycle Detection](#8-cycle-detection) | [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) |
| 5 | [Merge Two Sorted Lists](#5-merge-two-sorted-lists) | [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) |
| 7 | [Find Intersection](#7-find-intersection) | [160. Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/) |
