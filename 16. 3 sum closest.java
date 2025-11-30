 Intuition
We want the sum of three numbers closest to the target. 
Sorting the array allows us to use the two-pointer technique for efficient scanning, similar to the 3Sum problem, while always tracking the closest sum found so far.

🛠️ Approach
Sort the array to simplify two-pointer usage.
Fix the first element using index i, and apply two pointers:
left = i + 1
right = n - 1
At each iteration, calculate sum = nums[i] + nums[left] + nums[right].
If it's closer to the target than the current best result, update the result.
Return immediately if exact target is found.
⏱️ Complexity
Time complexity: O(n 
2
 )
Space complexity: O(1)
code:
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int result = nums[0] + nums[1] + nums[2]; // Initial best guess

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (Math.abs(target - sum) < Math.abs(target - result)) {
                    result = sum;
                }

                if (sum == target) return target;
                else if (sum < target) left++;
                else right--;
            }
        }

        return result;
    }
}
