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
    /**
     * Inserts a new value into a Binary Search Tree (BST).
     * Maintains BST property: left subtree values < root < right subtree values.
     * 
     * @param root The root node of the BST
     * @param val The value to be inserted into the BST
     * @return The root of the modified BST with the new value inserted
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // Base case: if we reach a null position, create and return a new node
        if (root == null) {
            return new TreeNode(val);
        }
      
        // If the value to insert is less than current node's value,
        // recursively insert into the left subtree
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } 
        // If the value to insert is greater than or equal to current node's value,
        // recursively insert into the right subtree
        else {
            root.right = insertIntoBST(root.right, val);
        }
      
        // Return the unchanged root node after insertion
        return root;
    }
}
