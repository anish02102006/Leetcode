// answer 1 
class Solution {
    public String reverseByType(String s) {

      StringBuilder a = new StringBuilder();
      StringBuilder b = new StringBuilder();
      char[] t = s.toCharArray();
      for (char c : t) {
        if (Character.isLetter(c)) {
          a.append(c);
        } else {
          b.append(c);
        }
      }

      int j = a .length(),  k = b.length();

      for (int i = 0; i < t.length; i++) {
        if (Character.isLetter(t[i])) {
          t[i] = a.charAt(--j);
        } else {
          t[i] = b.charAt(--k);
        }
      }
      return new String(t);
    }
}



// answer 2 :
class Solution {
    public int minimumK(int[] nums) {
        int l = 1, r = 100000;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(nums, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int[] nums, int k) {
        long t = 0;
        for (int x : nums) {
            t += (x + k - 1) / k;
        }
        return t <= 1L * k * k;
    }
}

// answer 3 :
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int longestSubsequence(int[] nums) {
        int maxLen = 0;

        // Iterate through each bit position (0 to 30)
        // If a subsequence has non-zero AND, they must all share at least one set bit.
        for (int bit = 0; bit <= 30; bit++) {
            List<Integer> filtered = new ArrayList<>();
            
            // 1. Filter numbers that have this specific bit set
            for (int num : nums) {
                if (((num >> bit) & 1) == 1) {
                    filtered.add(num);
                }
            }
            
            // 2. If valid numbers exist for this bit, find their LIS
            if (!filtered.isEmpty()) {
                maxLen = Math.max(maxLen, getLIS(filtered));
            }
        }
        
        return maxLen;
    }

    // Standard O(N log N) LIS function using binary search
    private int getLIS(List<Integer> list) {
        if (list.isEmpty()) return 0;
        
        List<Integer> tails = new ArrayList<>();
        
        for (int num : list) {
            // Use binary search to find the first element in tails >= num
            int idx = Collections.binarySearch(tails, num);
            
            // If num is not found, binarySearch returns (-(insertion point) - 1)
            if (idx < 0) {
                idx = -(idx + 1);
            }
            
            // If idx is at the end, extend the LIS
            if (idx == tails.size()) {
                tails.add(num);
            } else {
                // Otherwise, replace the existing value to maintain smallest tail property
                // strictly increasing condition: only update if tails.get(idx) > num?
                // Actually for strict increase: if we find exact match, we don't extend.
                // Binary search finds the first element >= num.
                // If found (idx >= 0), it means 'num' is already in tails.
                // For strictly increasing, we generally replace 'tails[idx]' with 'num'
                // BUT strictly increasing means duplicate values don't extend length.
                // Since this standard LIS algorithm replaces tails[idx] with num, 
                // and here tails[idx] would be equal to num, it effectively does nothing, which is correct.
                tails.set(idx, num);
            }
        }
        return tails.size();
    }
}

// answer 4 :
class Solution {
    long[][] dp;    // dp[i][j]: min score for i subarrays, first j elements
    long[] pre;     // prefix sums for O(1) range sum queries
    
    // The divide and conquer optimization function
    // i = current number of subarrays
    // l, r = range of j values we're computing (inclusive)
    // ol, or = range where optimal t can be for this (l,r) range
    private void solve(int i, int l, int r, int ol, int or) {
        if (l > r) return;  // base case: empty range
        
        // Compute for the middle j value
        int mid = l + (r - l) / 2;
        long ans = Long.MAX_VALUE;
        int idx = -1;  // Will store the optimal t for dp[i][mid]
        
        // Search for optimal t in restricted range [ol, min(mid-1, or)]
        // mid-1 because t must be < mid (we need at least 1 element in last subarray)
        for (int j = ol; j <= Math.min(mid - 1, or); j++) {
            // Sum of elements from j+1 to mid
            long sum = pre[mid] - pre[j];
            
            // Score for subarray nums[j+1..mid]
            // Formula: sum * (sum + 1) / 2
            long cost = sum * (sum + 1) / 2;
            
            // Total score if we cut at j
            long val = dp[i - 1][j] + cost;
            
            // Track minimum
            if (val < ans) {
                ans = val;
                idx = j;  // This j is currently the best cut point
            }
        }
        
        // Store the computed value
        dp[i][mid] = ans;
        
        // Recursively solve left half: j values < mid
        // For these, optimal t cannot be > idx (monotonicity)
        solve(i, l, mid - 1, ol, idx);
        
        // Recursively solve right half: j values > mid  
        // For these, optimal t cannot be < idx
        solve(i, mid + 1, r, idx, or);
    }
    
    public long minPartitionScore(int[] nums, int k) {
        int n = nums.length;
        
        // Precompute prefix sums (1-indexed for easier indexing)
        // pre[i] = sum of nums[0..i-1]
        pre = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        
        // Initialize DP table with large values
        dp = new long[k + 1][n + 1];
        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }
        dp[0][0] = 0;  // Base: 0 subarrays, 0 elements = 0 score
        
        // Compute DP for increasing number of subarrays
        for (int i = 1; i <= k; i++) {
            // Compute all dp[i][j] for j = 1..n using divide & conquer
            // Initial search range for t: [0, n-1]
            solve(i, 1, n, 0, n - 1);
        }
        
        return dp[k][n];
    }
}


