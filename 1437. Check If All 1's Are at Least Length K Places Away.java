Intuition
The key insight is that we only need to track consecutive 1s in the array and check the distance between them. We don't need to store all positions of 1s or look ahead - we can solve this in a single pass.

Think of it this way: as we traverse the array from left to right, whenever we encounter a 1, we need to know where the previous 1 was located. The distance between these two 1s tells us how many elements are between them. Since we want at least k zeros between any two 1s, the actual distance should be at least k + 1 (accounting for the positions of the 1s themselves).

For example, if we have [1, 0, 0, 1], the indices of 1s are 0 and 3. The distance is 3 - 0 = 3, but the number of zeros between them is 3 - 0 - 1 = 2.

This leads to a simple approach:

Keep track of the index of the last seen 1
When we find a new 1, calculate how many positions are between it and the previous 1
If the number of zeros (distance - 1) is less than k, we immediately know the condition is violated
Update our "last seen 1" position and continue
We initialize the "last seen 1" position to negative infinity (-inf) to handle the edge case where the first element is a 1. This ensures that the first 1 we encounter won't falsely trigger a violation since i - (-inf) - 1 will always be greater than any finite k.


Solution Approach
The implementation uses a single-pass traversal with a tracking variable to monitor the position of the last encountered 1.

Variables used:

j: Stores the index of the most recently seen 1, initialized to -inf to handle the first 1 gracefully
i: Current index while iterating through the array
x: Current element value at index i
Algorithm steps:

Initialize the tracker: Set j = -inf to represent that no 1 has been seen yet. Using negative infinity ensures that when we find the first 1, the distance calculation i - j - 1 will always be large enough to pass the check.

Iterate through the array: Use enumerate(nums) to get both the index i and value x of each element.

Check each 1: When we encounter a 1 (when x is truthy):

Calculate the number of zeros between current position i and previous 1 at position j using the formula: i - j - 1
If this distance is less than k, immediately return False as the constraint is violated
Update j = i to mark this as the new "last seen 1" position
Return result: If we complete the iteration without finding any violation, return True.

Example walkthrough: For nums = [1,0,0,0,1,0,0,1] and k = 2:

Start with j = -inf
Index 0: Found 1, distance = 0 - (-inf) - 1 > 2 ✓, update j = 0
Index 4: Found 1, distance = 4 - 0 - 1 = 3 ≥ 2 ✓, update j = 4
Index 7: Found 1, distance = 7 - 4 - 1 = 2 ≥ 2 ✓, update j = 7
Return True
The time complexity is O(n) where n is the length of the array, as we traverse it once. The space complexity is O(1) as we only use a constant amount of extra space.

Ready to land your dream job?
Unlock your dream job with a 5-minute evaluator for a personalized learning plan!
Example Walkthrough
Let's walk through the solution with nums = [1,0,0,1,0,1] and k = 2:

Initial State:

j = -inf (no previous 1 seen yet)
We need at least 2 zeros between any two 1s
Step-by-step traversal:

Index 0: nums[0] = 1

This is a 1, so we check the distance from the previous 1
Distance calculation: 0 - (-inf) - 1 = very large number
This is definitely ≥ 2, so we're good
Update: j = 0 (mark this 1's position)
Index 1: nums[1] = 0

This is a 0, so we skip it
Index 2: nums[2] = 0

This is a 0, so we skip it
Index 3: nums[3] = 1

This is a 1, so we check the distance from the previous 1 at index 0
Number of zeros between them: 3 - 0 - 1 = 2
This equals our requirement k = 2, so we're good
Update: j = 3 (mark this new 1's position)
Index 4: nums[4] = 0

This is a 0, so we skip it
Index 5: nums[5] = 1

This is a 1, so we check the distance from the previous 1 at index 3
Number of zeros between them: 5 - 3 - 1 = 1
This is less than our requirement k = 2 (we need at least 2 zeros but only have 1)
Return False immediately
The algorithm correctly identifies that the last two 1s (at indices 3 and 5) only have 1 zero between them, violating the requirement of having at least 2 zeros between consecutive 1s.

Solution Implementation
Python
Java
C++
TypeScript
class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        // Initialize the position of the previous 1
        // Set to -(k+1) to handle the case when the first element is 1
        // This ensures the distance check passes for the first 1
        int previousOneIndex = -(k + 1);
      
        // Iterate through the array to find all 1s
        for (int currentIndex = 0; currentIndex < nums.length; currentIndex++) {
            // Check if current element is 1
            if (nums[currentIndex] == 1) {
                // Calculate distance between current 1 and previous 1
                // Distance = currentIndex - previousOneIndex - 1 (excluding both endpoints)
                if (currentIndex - previousOneIndex - 1 < k) {
                    // Distance is less than k, so return false
                    return false;
                }
                // Update the position of the previous 1
                previousOneIndex = currentIndex;
            }
        }
      
        // All 1s are at least k distance apart
        return true;
    }
}
Time and Space Complexity
Time Complexity: O(n), where n is the length of the input array nums. The algorithm iterates through the array exactly once using a single for loop with enumerate(). Each operation inside the loop (checking if x is 1, calculating the distance i - j - 1, comparison with k, and updating j) takes constant time O(1). Therefore, the overall time complexity is linear.

Space Complexity: O(1). The algorithm uses only a constant amount of extra space regardless of the input size. The variables used are:

j: stores the index of the previous 1 (initialized to negative infinity)
i: the current index from enumerate
x: the current value from enumerate
These variables don't grow with the input size, making the space complexity constant.
