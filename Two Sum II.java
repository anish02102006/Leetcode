Intuition
Using two pointers

Approach
If you know Two Sum question that is the first question of LeetCode, you can solve this question with HashMap. But HashMap solution should O(n) space. The description says "Your solution must use only constant extra space", so you need to change your strategy.

Let me explain my strategy with this example.

Input: numbers = [2,7,11,15], target = 18
My strategy is to use two pointers. Let's say left and right.

[2,7,11,15]
 L      R
left pointer starts from index 0 and right pointer starts from the last index.

Every time we calculate addition with left number + right number.

[2,7,11,15]
 L      R

2 + 15 = 17 
target = 18
Now current total (= 17) is smaller than the target, so current combination is not answer.

After that, we have to move left or right to find the target number.

But how can we decide to move the one of them?

It's simple. Current total is smaller than the target, that's why we should move left pointer to the next, because input array is sorted, so right number is definitely greater than left number. If we move left pointer to the next, we will get bigger total next time.

[2,7,11,15]
   L    R

7 + 15 = 22
target = 18
We got bigger total this time. In that case, we need to move right pointer to the next, because of the same reason above. Input array is sorted, so if we move right pointer to the next, we will get smaller total next time.

[2,7,11,15]
   L R

7 + 11 = 18
target = 18
Now, current total is equal to the target. The description says "Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2".

return [2, 3]
Easy! 😄
Let's see a real algorithm!

⭐️ Related Question



Complexity
Time complexity: O(n)
Space complexity: O(1)
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int total = numbers[left] + numbers[right];

            if (total == target) {
                return new int[]{left + 1, right + 1};
            } else if (total > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{-1, -1}; // If no solution is found        
    }
}
Step by Step Algorithm
Initialize Pointers:

Set left pointer to the start of the array, i.e., index 0.

left = 0
Set right pointer to the end of the array, i.e., index len(numbers) - 1.

right = len(numbers) - 1
Loop Until Pointers Meet:

Enter a while loop that continues until the left pointer is less than the right pointer.

while left < right:
Calculate Sum:

Calculate the sum of the elements at indices left and right in the numbers array and store it in the variable total.

total = numbers[left] + numbers[right]
Check Sum Against Target:

Check if the total sum equals the target value.

if total == target:
If it does, return an array containing the indices left + 1 and right + 1. These indices are incremented by 1 to convert them to 1-indexed format as mentioned in the problem statement.

return [left + 1, right + 1]
If not, proceed to the next steps.

Adjust Pointers:

If the total sum is greater than the target, decrement the right pointer. This indicates that we need to decrease the sum, so we move towards smaller values by decreasing the index of the right pointer.

elif total > target:
    right -= 1
If the total sum is less than the target, increment the left pointer. This indicates that we need to increase the sum, so we move towards larger values by increasing the index of the left pointer.

else:
    left += 1
Repeat:

Repeat steps 3-5 until either the condition left < right becomes false, or a valid pair summing up to the target is found.
Return Default Value:

If no valid pair summing up to the target is found within the array, return an array containing -1, -1 as there is no solution.

return [-1, -1]
This algorithm efficiently finds a pair of indices in the given sorted array numbers whose elements sum up to the given target. It utilizes a two-pointer approach to navigate through the array and converge towards the target sum.
