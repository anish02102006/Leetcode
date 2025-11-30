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
    private int[] preorderArray;

    /**
     * Constructs a Binary Search Tree from its preorder traversal.
     * 
     * @param preorder The preorder traversal array of the BST
     * @return The root node of the constructed BST
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        this.preorderArray = preorder;
        return buildBST(0, preorder.length - 1);
    }

    /**
     * Recursively builds the BST using divide and conquer approach.
     * 
     * @param startIndex The starting index of the current subtree in preorder array
     * @param endIndex The ending index of the current subtree in preorder array
     * @return The root node of the subtree
     */
    private TreeNode buildBST(int startIndex, int endIndex) {
        // Base case: invalid range
        if (startIndex > endIndex) {
            return null;
        }
      
        // The first element in preorder is always the root
        TreeNode root = new TreeNode(preorderArray[startIndex]);
      
        // Use binary search to find the boundary between left and right subtrees
        // All elements less than root value belong to left subtree
        // All elements greater than root value belong to right subtree
        int left = startIndex + 1;
        int right = endIndex + 1;
      
        // Binary search to find the first element greater than root value
        while (left < right) {
            int mid = (left + right) >> 1;  // Equivalent to (left + right) / 2
          
            if (preorderArray[mid] > preorderArray[startIndex]) {
                // Mid element is greater than root, search in left half
                right = mid;
            } else {
                // Mid element is less than root, search in right half
                left = mid + 1;
            }
        }
      
        // Recursively build left subtree: elements from (startIndex + 1) to (left - 1)
        root.left = buildBST(startIndex + 1, left - 1);
      
        // Recursively build right subtree: elements from left to endIndex
        root.right = buildBST(left, endIndex);
      
        return root;
    }
}
