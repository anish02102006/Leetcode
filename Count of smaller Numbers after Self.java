Approach 🚀
Process from right to left ⬅️: We start from the end of the array and move leftwards. 
  This allows us to maintain a sorted list of numbers we've already processed (which are to the right of the current number in the original array).

Binary search insertion ✂️: For each number, we use binary search to find the correct position where it would be inserted in the sorted list.
The index where it would be inserted gives us the count of numbers smaller than it in the processed portion (which corresponds to numbers to its right in the original array).

Build result in reverse 🔄: Since we're processing from right to left, we add each count to a list and then reverse it at the end to match the original array's order.

Complexity ⏱️
Time Complexity: O(n log n) ⏳ - For each of the n elements, we perform a binary search (O(log n)) and an insertion (O(n) in worst case for ArrayList).
However, with careful implementation, the average case can be O(n log n).

Space Complexity: O(n) 💾 - We maintain a sorted list and a result list, both of size n.

This approach efficiently solves the problem by leveraging the properties of binary search and maintaining a dynamically sorted list of processed elements.

💻 Solution Code
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> li = new ArrayList<>();
        List<Integer> sorted = new ArrayList<>();

        for(int i=nums.length-1;i>=0;i--){
            int ind = insert(sorted,nums[i]);
            li.add(ind);
            sorted.add(ind, nums[i]);
        }

        Collections.reverse(li);
        return li;
    }
    private int insert(List<Integer> arr, int num){
        int l =0;
        int r = arr.size()-1;

        while(l<=r){
            int mid = l+(r-l)/2;
            if(arr.get(mid)< num){
                l = mid+1;
            }else r = mid-1;
        }

        return l;
    }
}
