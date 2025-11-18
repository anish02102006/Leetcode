Intuition
The key insight comes from understanding the fundamental property of a binary search tree: values are organized in a sorted manner. Just like how we'd search for a word in a dictionary - we don't read every single word from the beginning. Instead, we open to the middle, check if our word comes before or after the current page, and then narrow down our search to the relevant half.

When we stand at any node in a BST, we can make an intelligent decision about where to look next. If the value we're searching for is smaller than the current node's value, we know with certainty that it can only exist in the left subtree (if it exists at all). Similarly, if our target value is larger, it must be in the right subtree.

This leads us to a natural recursive strategy:

First, check if we've found what we're looking for (current node's value equals val) or if we've hit a dead end (root is None)
If not, use the BST property to decide which direction to go - left if val is smaller, right if val is larger
The beauty of this approach is that once we find the node with the matching value, we don't need to do any additional work to "build" the subtree - the node itself already represents the root of the entire subtree we need to return. This is because in tree data structures, each node inherently contains references to its entire subtree through its left and right pointers.

By leveraging the BST's sorted structure, we avoid examining unnecessary nodes, effectively cutting our search space in half at each step - similar to binary search in a sorted array.

Learn more about Tree, Binary Search Tree and Binary Tree patterns.


Solution Approach
The solution implements a recursive search algorithm that takes advantage of the BST's ordered structure. Let's walk through the implementation step by step:

Base Cases: The recursion has two base cases combined into a single condition:

root is None: We've reached a leaf node's child (null), meaning the value doesn't exist in the tree
root.val == val: We've found the target node
Both cases return root directly - either None or the found node with its entire subtree.

Recursive Case: When the base cases don't apply, we need to decide which subtree to search:

return (
    self.searchBST(root.left, val)
    if root.val > val
    else self.searchBST(root.right, val)
)
This ternary expression elegantly captures the BST navigation logic:

If root.val > val: The target must be in the left subtree (smaller values)
If root.val < val: The target must be in the right subtree (larger values)
Why This Works:

Correctness: The BST property guarantees that if a value exists, it can only be in one specific path from root to leaf. We never miss the target by choosing the wrong direction.

Efficiency: At each recursive call, we eliminate roughly half of the remaining nodes from consideration (in a balanced tree), similar to binary search.

Simplicity: The recursive structure naturally handles the tree traversal without needing explicit stack management or iteration variables.

Time and Space Complexity:

Time: O(h) where h is the height of the tree. In a balanced BST, this is O(log n), but in the worst case (skewed tree), it becomes O(n).
Space: O(h) for the recursion call stack, following the same pattern as time complexity.
The solution demonstrates how recursion naturally fits tree problems, where each recursive call handles a subtree, making the code both intuitive and concise.

Ready to land your dream job?
Unlock your dream job with a 5-minute evaluator for a personalized learning plan!
Example Walkthrough
Let's search for value 2 in the following BST:

        4
       / \
      2   7
     / \
    1   3
Step 1: Start at root (node 4)

Current value: 4
Target value: 2
Since 4 > 2, we know our target must be in the left subtree
Recursively call searchBST(node_2, 2)
Step 2: Now at node 2

Current value: 2
Target value: 2
We found a match! (2 == 2)
Return this node along with its entire subtree
Result: The function returns the subtree rooted at node 2:

      2
     / \
    1   3
Let's trace through another example where we search for value 5 in the same tree:

Step 1: Start at root (node 4)

Current value: 4
Target value: 5
Since 4 < 5, search in the right subtree
Recursively call searchBST(node_7, 5)
Step 2: Now at node 7

Current value: 7
Target value: 5
Since 7 > 5, search in the left subtree
Recursively call searchBST(None, 5) (node 7 has no left child)
Step 3: Reached None

Hit our base case with root is None
Return None
Result: The function returns None since value 5 doesn't exist in the tree.

This walkthrough demonstrates how the algorithm efficiently navigates the tree using BST properties, only visiting nodes along a single path from root to the target (or to a null node if the target doesn't exist).

Solution Implementation
Python
Java
C++
TypeScript
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
     * Searches for a node with the given value in a Binary Search Tree.
     * 
     * @param root The root node of the BST
     * @param val The target value to search for
     * @return The subtree rooted at the node with the target value, or null if not found
     */
    public TreeNode searchBST(TreeNode root, int val) {
        // Base case: if root is null or we found the target value
        if (root == null || root.val == val) {
            return root;
        }
      
        // BST property: if target value is less than current node value, search left subtree
        // Otherwise, search right subtree
        return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
    }
}
Time and Space Complexity
Time Complexity: O(h) in the average case for a balanced BST, where h is the height of the tree. In the worst case (skewed tree), this becomes O(n) where n is the number of nodes in the tree.

The algorithm performs a binary search through the BST. At each step, it eliminates one subtree based on the comparison with the current node's value. In a balanced BST with height log(n), the search visits at most log(n) nodes. However, if the tree is completely unbalanced (essentially a linked list), the algorithm might need to traverse all n nodes, resulting in O(n) time complexity.

Space Complexity: O(h) in the average case, which becomes O(n) in the worst case.

The space complexity is determined by the recursive call stack. Since this is a recursive implementation, each recursive call adds a frame to the call stack. The maximum depth of recursion equals the height of the tree. In a balanced BST, this would be O(log(n)), but in the worst case of a skewed tree, the recursion depth could be O(n).

The reference answer states O(n) for both complexities, which represents the worst-case scenario when the BST degenerates into a linear structure.
