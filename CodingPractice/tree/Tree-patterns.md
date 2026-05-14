# LeetCode Tree Patterns - Java Boilerplate

## 1. TreeNode Definition

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}
```

## 2. Recursive DFS

Use for most binary tree problems.

```java
private void dfs(TreeNode root) {
    if (root == null) {
        return;
    }

    dfs(root.left);
    dfs(root.right);
}
```

## 3. Preorder Traversal

Root, left, right.

```java
private void preorder(TreeNode root, List<Integer> res) {
    if (root == null) {
        return;
    }

    res.add(root.val);
    preorder(root.left, res);
    preorder(root.right, res);
}
```

## 4. Inorder Traversal

Left, root, right. For a BST, this gives sorted order.

```java
private void inorder(TreeNode root, List<Integer> res) {
    if (root == null) {
        return;
    }

    inorder(root.left, res);
    res.add(root.val);
    inorder(root.right, res);
}
```

## 5. Postorder Traversal

Left, right, root. Useful when child results are needed before parent.

```java
private int postorder(TreeNode root) {
    if (root == null) {
        return 0;
    }

    int left = postorder(root.left);
    int right = postorder(root.right);

    return 1 + Math.max(left, right);
}
```

## 6. Level Order BFS

Use for level-by-level processing.

```java
Queue<TreeNode> q = new LinkedList<>();
q.offer(root);

while (!q.isEmpty()) {
    int size = q.size();

    for (int i = 0; i < size; i++) {
        TreeNode node = q.poll();

        if (node.left != null) {
            q.offer(node.left);
        }

        if (node.right != null) {
            q.offer(node.right);
        }
    }
}
```

## 7. Depth / Height

```java
private int height(TreeNode root) {
    if (root == null) {
        return 0;
    }

    return 1 + Math.max(height(root.left), height(root.right));
}
```

## 8. Balanced Tree

Return `-1` when a subtree is already unbalanced.

```java
private int check(TreeNode root) {
    if (root == null) {
        return 0;
    }

    int left = check(root.left);
    if (left == -1) {
        return -1;
    }

    int right = check(root.right);
    if (right == -1) {
        return -1;
    }

    if (Math.abs(left - right) > 1) {
        return -1;
    }

    return 1 + Math.max(left, right);
}
```

## 9. Diameter / Path Through Node

Use global answer when each node can contribute a path.

```java
int ans = 0;

private int depth(TreeNode root) {
    if (root == null) {
        return 0;
    }

    int left = depth(root.left);
    int right = depth(root.right);

    ans = Math.max(ans, left + right);

    return 1 + Math.max(left, right);
}
```

## 10. Path Sum

Carry a running value down the tree.

```java
private boolean hasPathSum(TreeNode root, int target) {
    if (root == null) {
        return false;
    }

    if (root.left == null && root.right == null) {
        return target == root.val;
    }

    return hasPathSum(root.left, target - root.val) ||
           hasPathSum(root.right, target - root.val);
}
```

## 11. Lowest Common Ancestor

For a regular binary tree:

```java
private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) {
        return root;
    }

    TreeNode left = lca(root.left, p, q);
    TreeNode right = lca(root.right, p, q);

    if (left != null && right != null) {
        return root;
    }

    return left != null ? left : right;
}
```

## 12. BST Search

```java
while (root != null) {
    if (root.val == target) {
        return root;
    } else if (target < root.val) {
        root = root.left;
    } else {
        root = root.right;
    }
}

return null;
```

## 13. Validate BST

Use lower and upper bounds.

```java
private boolean isValid(TreeNode root, long low, long high) {
    if (root == null) {
        return true;
    }

    if (root.val <= low || root.val >= high) {
        return false;
    }

    return isValid(root.left, low, root.val) &&
           isValid(root.right, root.val, high);
}
```

## 14. Build Tree From Traversals

Preorder first value is root. Inorder splits left and right subtrees.

```java
Map<Integer, Integer> index = new HashMap<>();

for (int i = 0; i < inorder.length; i++) {
    index.put(inorder[i], i);
}
```

## 15. Serialize / Deserialize

Use preorder with null markers.

```java
private void serialize(TreeNode root, StringBuilder sb) {
    if (root == null) {
        sb.append("#,");
        return;
    }

    sb.append(root.val).append(",");
    serialize(root.left, sb);
    serialize(root.right, sb);
}
```

## 16. Tree DP

Use when each node has choices like take / skip.

```java
private int[] dfs(TreeNode root) {
    if (root == null) {
        return new int[] {0, 0};
    }

    int[] left = dfs(root.left);
    int[] right = dfs(root.right);

    int take = root.val + left[1] + right[1];
    int skip = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

    return new int[] {take, skip};
}
```

# LeetCode Practice Problems By Pattern

| # | Pattern | Practice Problem |
|---|---|---|
| 1 | TreeNode Definition | [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) |
| 2 | Recursive DFS | [226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/) |
| 3 | Preorder Traversal | [144. Binary Tree Preorder Traversal](https://leetcode.com/problems/binary-tree-preorder-traversal/) |
| 4 | Inorder Traversal | [94. Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/) |
| 5 | Postorder Traversal | [145. Binary Tree Postorder Traversal](https://leetcode.com/problems/binary-tree-postorder-traversal/) |
| 6 | Level Order BFS | [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/) |
| 7 | Depth / Height | [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) |
| 8 | Balanced Tree | [110. Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/) |
| 9 | Diameter | [543. Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/) |
| 10 | Path Sum | [112. Path Sum](https://leetcode.com/problems/path-sum/) |
| 11 | Lowest Common Ancestor | [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) |
| 12 | BST Search | [700. Search in a Binary Search Tree](https://leetcode.com/problems/search-in-a-binary-search-tree/) |
| 13 | Validate BST | [98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/) |
| 14 | Build Tree From Traversals | [105. Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) |
| 15 | Serialize / Deserialize | [297. Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) |
| 16 | Tree DP | [337. House Robber III](https://leetcode.com/problems/house-robber-iii/) |

# Recommended Study Order

| Order | Pattern | Practice Problem |
|---|---|---|
| 2 | [Recursive DFS](#2-recursive-dfs) | [226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/) |
| 7 | [Depth / Height](#7-depth--height) | [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) |
| 6 | [Level Order BFS](#6-level-order-bfs) | [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/) |
| 9 | [Diameter](#9-diameter--path-through-node) | [543. Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/) |
| 10 | [Path Sum](#10-path-sum) | [112. Path Sum](https://leetcode.com/problems/path-sum/) |
| 11 | [Lowest Common Ancestor](#11-lowest-common-ancestor) | [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) |
| 13 | [Validate BST](#13-validate-bst) | [98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/) |
| 14 | [Build Tree From Traversals](#14-build-tree-from-traversals) | [105. Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) |
| 16 | [Tree DP](#16-tree-dp) | [337. House Robber III](https://leetcode.com/problems/house-robber-iii/) |
