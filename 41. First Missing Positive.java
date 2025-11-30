Intuition
My first thought was that the smallest missing positive integer must be somewhere between 1 and n+1 (where n is the array length).
To efficiently check if numbers exist, I can convert the array to a set for O(1) lookups, then simply check consecutive positive integers starting from 1.

Approach
The solution follows these steps:

Initialize i to 1, representing the first positive integer to check
Convert the nums list to a set for fast membership testing
Use a while loop to keep checking if i exists in the set
As long as i is found in the set, increment it to check the next positive integer
When we find an i that's not in the set, that's our answer - the first missing positive
Return i
For example, with nums = [3,4,-1,1]:

Convert to set: {3, 4, -1, 1}
Check i=1: found, increment to 2
Check i=2: not found
Return 2
Another example with nums = [1,2,0]:

Convert to set: {1, 2, 0}
Check i=1: found, increment to 2
Check i=2: found, increment to 3
Check i=3: not found
Return 3
Complexity
Time complexity: O(n)

Converting the list to a set takes O(n) time. In the worst case, the array contains [1,2,3,...,n], so we check n numbers, each in O(1) time. Total: O(n) + O(n) = O(n).

Space complexity: O(n)

We create a set from the input array, which stores up to n elements. This requires O(n)

class Solution {
    public int firstMissingPositive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);
        int i = 1;
        while (set.contains(i)) i++;
        return i;
    }
}
