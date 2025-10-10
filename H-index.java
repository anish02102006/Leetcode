Intuition
The problem asks us to find the H-Index, which represents the largest number h such that a researcher has at least h papers with h or more citations.
  The array is sorted in non-decreasing order, and we can efficiently find the H-Index using binary search by comparing the number of citations with the
  remaining papers.

Approach
We use binary search with two pointers: lo and hi. At each iteration, we compute the midpoint mid.
  The key observation is that for a given mid, if the number of citations at mid equals citations.length - mid, we have found the H-Index.
  If citations[mid] is smaller, the H-Index lies to the right, so we move lo = mid + 1.
  If citations[mid] is greater, the H-Index is to the left, so we update hi = mid - 1. If no exact match is found, the H-Index will be citations.length - lo.

Complexity
Time complexity:
O(logn)

Space complexity:
O(1)


#java code
  class Solution {
    public int hIndex(int[] citations) {
        int lo=0;
        int hi=citations.length-1;
        while(lo<=hi){
            int mid=lo+(hi-lo)/2;
            if(citations[mid]==citations.length-mid){
                return citations[mid];
            }
            else if(citations[mid]<citations.length-mid){
                lo=mid+1;
            }
            else{
                hi=mid-1;
            }
        }
        return citations.length-lo;
    }
}
