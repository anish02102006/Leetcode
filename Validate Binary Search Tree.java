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
    // Instance variable to track the previously visited node during in-order traversal
    private TreeNode previousNode;

    /**
     * Validates whether the binary tree is a valid Binary Search Tree (BST).
     * A valid BST requires that for every node, all values in its left subtree are less than the node's value,
     * and all values in its right subtree are greater than the node's value.
     * 
     * @param root The root node of the binary tree
     * @return true if the tree is a valid BST, false otherwise
     */
    public boolean isValidBST(TreeNode root) {
        return inOrderTraversal(root);
    }

    /**
     * Performs an in-order traversal to validate BST properties.
     * In-order traversal of a valid BST should visit nodes in ascending order.
     * 
     * @param currentNode The current node being processed
     * @return true if the subtree rooted at currentNode is a valid BST, false otherwise
     */
    private boolean inOrderTraversal(TreeNode currentNode) {
        // Base case: an empty tree is a valid BST
        if (currentNode == null) {
            return true;
        }
      
        // Recursively validate the left subtree
        if (!inOrderTraversal(currentNode.left)) {
            return false;
        }
      
        // Check if the current node violates BST property
        // The current node's value must be greater than the previous node's value
        if (previousNode != null && previousNode.val >= currentNode.val) {
            return false;
        }
      
        // Update the previous node to the current node for the next comparison
        previousNode = currentNode;
      
        // Recursively validate the right subtree
        return inOrderTraversal(currentNode.right);
    }
}
