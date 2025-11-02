Intuition
To find the length of the longest strictly increasing subsequence, we can use dynamic programming to keep track of the length of the increasing subsequence ending at each index.
Approach
We can initialize an array dp of the same length as the input array nums, where dp[i] represents the length of the longest increasing subsequence ending at index i. 
  We can iterate through the array and update dp based on the elements before the current index. The final result will be the maximum value in the dp array.
Complexity
Time complexity:
O(n 
2
 ) - We have a nested loop where the outer loop runs for each element, and the inner loop runs for each element before the current index.
Space complexity:
O(n) - We use an additional array dp of the same length as the input array.
Code
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLength = Arrays.stream(dp).max().orElse(0);
        return maxLength;
    }
}
