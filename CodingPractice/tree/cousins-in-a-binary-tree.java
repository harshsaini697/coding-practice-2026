/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    TreeNode parentX;
    TreeNode parentY;
    int levelX;
    int levelY;
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        dfs(root, x, y, null, 0);

        return levelX == levelY && parentX != parentY;
    }

    private void dfs (TreeNode root, int x, int y, TreeNode parent, int level) {
        if (root == null) return;

        if (root.val == x) {
            levelX = level;
            parentX = parent;
        }

        if (root.val == y) {
            levelY = level;
            parentY = parent;
        }

        if (parentX != null && parentY != null) return;

        dfs (root.left, x, y, root, level + 1);
        dfs (root.right, x, y, root, level + 1);
    }
}