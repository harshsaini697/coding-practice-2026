import java.util.*;
/**
 * Definition for a binary tree node.
 */

class Solution {
    List<List<Integer>> result;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        result = new ArrayList();
        if (root == null) {
            return result;
        }

        helper(root, targetSum, 0, new ArrayList());

        return result;
    }

    private void helper (TreeNode node, int targetSum, int currentSum, List<Integer> nodes) {
        if (node == null) return;

        currentSum += node.val;
        nodes.add(node.val);
        if (node.left == null && node.right == null && currentSum == targetSum) {
            result.add(new ArrayList<>(nodes));
        }

        helper (node.left, targetSum, currentSum, nodes);
        helper (node.right, targetSum, currentSum, nodes);
        nodes.remove(nodes.size() - 1);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}