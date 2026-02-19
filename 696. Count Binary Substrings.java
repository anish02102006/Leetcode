class Solution {
    public int countBinarySubstrings(String s) {
        int res = 0, prev = 0, strk = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) strk++;
            else {
                prev = strk;
                strk = 1;
            }
            if (strk <= prev) res++;
        }
        return res;
    }
}
/*  Intuition
The valid substrings form at every 0→1 or 1→0 transition.

From this boundary, we expand outward one pair at a time, matching symmetric pairs of contiguous block of 0s and 1s.

When we hit another boundary/transition as we expand, the expansion stops.

Linear Solution
image.png

As we observed, the number of balanced symmetric substrings is limited by each group's minimum contiguous (0 or 1) block length.

Therefore, the final count of valid substrings is the total sum of each group's minimum.

Algorithm
We can reduce the problem into counting consecutive elements.

A valid substring exists as long as the current streak can be paired with characters from the previous streak.*

image.png

for (int i = 1; i < s.length(); i++) {
    if (s.charAt(i) == s.charAt(i - 1)) {
        strk++;              // still in the same group
                             // extend current streak
    } else {
        prev = strk;         // group changed, store length
        strk = 1;            // reset
    }

    // *
    if (strk <= prev)
        res++;
}

return res;
Time Complexity: O(n)
Space Complexity: O(1)  */
