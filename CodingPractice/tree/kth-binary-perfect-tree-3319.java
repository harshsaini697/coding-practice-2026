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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class Solution {
    private List<Integer> perfectSizes;
    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }

        perfectSizes = new ArrayList();
        dfs(root);

        if (perfectSizes.size() < k) {
            return -1;
        }

        Collections.sort(perfectSizes, Collections.reverseOrder());

        return perfectSizes.get(k - 1);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSize = dfs(root.left);
        int rightSize = dfs(root.right);

        if (leftSize == -1 || rightSize == -1 || leftSize != rightSize) {
            return -1;
        }

        int currentSize = 1 + leftSize + rightSize;
        perfectSizes.add(currentSize);
        return currentSize;
    }
}

// A perfect binary tree where root has both children and all leaves are same on the same level.
// The ideal way to check if a subtree is perfect is to check if the left and right subtrees are perfect and have the same size. 
// If they do, then the current subtree is also perfect. We can use a depth-first search (DFS) approach to traverse the tree 
// and calculate the sizes of the perfect subtrees.
//  We store these sizes in a list and sort it in descending order to find the k-th largest perfect subtree size. 
// If there are fewer than k perfect subtrees, we return -1.

// Why are we using postorder traversal?
// We are using postorder traversal because we need to calculate the sizes of the left and right subtrees before we can determine 
// if the current subtree is perfect. In postorder traversal, we visit the left subtree, then the right subtree, and finally the current node. 
// This allows us to gather the necessary information from the child nodes before making a decision about the parent node.