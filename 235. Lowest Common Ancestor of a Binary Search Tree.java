/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    /**
     * Finds the lowest common ancestor (LCA) of two nodes in a Binary Search Tree.
     * Utilizes the BST property where left subtree values < root value < right subtree values.
     * 
     * @param root The root node of the BST
     * @param p First target node
     * @param q Second target node
     * @return The lowest common ancestor node of p and q
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Find the minimum and maximum values between p and q for comparison
        int minValue = Math.min(p.val, q.val);
        int maxValue = Math.max(p.val, q.val);
      
        // Traverse the tree iteratively
        while (true) {
            // If current node value is less than both p and q,
            // LCA must be in the right subtree
            if (root.val < minValue) {
                root = root.right;
            } 
            // If current node value is greater than both p and q,
            // LCA must be in the left subtree
            else if (root.val > maxValue) {
                root = root.left;
            } 
            // Current node is between p and q (inclusive),
            // so it must be the LCA
            else {
                return root;
            }
        }
    }
}
