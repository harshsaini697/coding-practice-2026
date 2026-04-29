# 09. Tree DP

Use tree DP when each node's answer depends on its children.

## Basic Shape

```text
dfs(node) returns useful values for the subtree rooted at node
```

## House Robber III

At each node:

```text
rob this node
or
skip this node
```

State returned:

```text
int[0] = best if we skip this node
int[1] = best if we rob this node
```

Java:

```java
class Solution {
    public int rob(TreeNode root) {
        int[] ans = dfs(root);
        return Math.max(ans[0], ans[1]);
    }

    private int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0};

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int skip = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        int take = node.val + left[0] + right[0];

        return new int[]{skip, take};
    }
}
```

## Rerooting DP Idea

Sometimes you need the answer for every possible root.

Typical two-pass approach:

```text
1. postorder: compute subtree answers
2. preorder: pass parent-side contribution to children
```

## Recognition Clues

- binary tree maximum path
- choose nodes with parent-child restrictions
- compute answer for every node as root
- subtree sizes and distances

## Interview Problems

- House Robber III
- Binary Tree Maximum Path Sum
- Diameter of Binary Tree
- Longest Univalue Path
- Sum of Distances in Tree

