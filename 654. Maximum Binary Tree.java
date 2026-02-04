// stack approach

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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        
        Stack<TreeNode> stack = new Stack<>();
        for(int num: nums) {

            TreeNode current = new TreeNode(num);
            while(!stack.isEmpty() && stack.peek().val < num) {
                current.left = stack.pop();
            }
            if(!stack.isEmpty()) {
                stack.peek().right = current;
            }
            stack.push(current);
        }
        while(stack.size() > 1) {
            stack.pop();
        }
        return stack.peek();
    }
}
