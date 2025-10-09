Approach: Dynamic Programming
Intuition
The requirement is that each potion must be brewed by every wizard, and each wizard can brew only one potion at a time. Once the brewing of a potion begins, it cannot be paused midway and must be completed continuously by all wizards.

First, the brewing of the first potion begins at time 0, so the time each wizard takes to brew it is fixed and easy to calculate. However, the starting time for subsequent potions is not immediately clear.

We may allow the brewing process of the j-th potion to be non-continuous (while the first j−1 potions still require continuity) and let the time for the i-th wizard to finish brewing the j-th potion be times[i][j]. Then we have:

times[i][j]=max(times[i−1][j],times[i][j−1])+skill[i]×mana[j]

After one round of recursion, we obtain the completion time of the j-th potion, times[n−1][j]. At this point, we traverse backwards to update the completion times of the j-th potion for the previous wizards, eliminating the gaps introduced by the earlier allowance of discontinuous brewing.

During implementation, times can be optimized to a one-dimensional array.
code: 
class Solution {

    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;
        long[] times = new long[n];

        for (int j = 0; j < m; j++) {
            long curTime = 0;
            for (int i = 0; i < n; i++) {
                curTime =
                    Math.max(curTime, times[i]) + (long) skill[i] * mana[j];
            }
            times[n - 1] = curTime;
            for (int i = n - 2; i >= 0; i--) {
                times[i] = times[i + 1] - (long) skill[i + 1] * mana[j];
            }
        }
        return times[n - 1];
    }
}

Complexity Analysis
Let n be the length of skill and m be the length of mana.

Time complexity: O(nm).

The dynamic programming approach requires O(nm) time.

Space complexity: O(n).

We use a one-dimensional array to store intermediate results.
