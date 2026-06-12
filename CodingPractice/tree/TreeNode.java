import java.util.*;
 /**
 * Definition for a binary tree node.
 * 
 */
public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}

public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        LinkedList<String> paths = new LinkedList();
        recurse(root, "", paths);
        return paths;
    }

    private void recurse(TreeNode root, String path, LinkedList<String> paths) {
        if (root == null) {
            return;
        }

        path += Integer.toString(root.val);
        if ((root.left == null) && (root.right == null)) {
            paths.add(path);
        } else {
            path += "->";
            recurse(root.left, path, paths);
            recurse(root.right, path, paths);
        }
    }
} 
