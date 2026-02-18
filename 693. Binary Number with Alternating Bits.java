class Solution {
    public boolean hasAlternatingBits(int n) {
        int x = n ^ (n >> 1);
        return (x & (x + 1)) == 0;
    }
}
/* Intuition
If a number has alternating bits (like 101010...), then shifting it right by 1 bit will create an opposite pattern.

When we XOR the number with its right-shifted version:

x = n ^ (n >> 1)
If the bits were perfectly alternating, the result will be a sequence of all 1s (e.g., 11111...).

A number that is all 1s has a special property:

x & (x + 1) == 0
This works because adding 1 to a sequence of all 1s produces a power of two, and ANDing them gives 0.

Approach
Compute x = n ^ (n >> 1)
Check whether x is of the form 111...111 using:
(x & (x + 1)) == 0
If true → n has alternating bits.
Otherwise → it does not.
This avoids looping and works in constant time.

Dry Run
Example 1: n = 5
Binary representation:

n      = 101
n >> 1 = 010
XOR operation:

101 ^ 010
111  (x = 7)
Now check:

x + 1 = 111 + 1 = 1000

111 & 1000
0000  → equals 0 ✅
Result: true

Example 3: n = 11
Binary representation:

n      = 1011
n >> 1 = 0101
XOR operation:

1011 ^ 0101
1110  (x = 14)
Now check:

x + 1 = 1110 + 1 = 1111

1110 & 1111
1110  → not 0 ❌
Result: false

Complexity
Time complexity: O(1)
Space complexity: O(1)
*/
