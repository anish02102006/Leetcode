Intuition
The problem asks us to find the n-th digit in the infinite sequence formed by concatenating all positive integers:
1 2 3 4 5 6 7 8 9 10 11 12 13 14 ...

At first, this looks like a simple string problem — but we obviously cannot build such a large sequence for big n (up to billions).
So, we need a way to mathematically locate which number and which digit corresponds to position n.

To do that, we observe a pattern in how digits are distributed:

1-digit numbers: 1 to 9 → 9 numbers → 9 digits total

2-digit numbers: 10 to 99 → 90 numbers → 180 digits total

3-digit numbers: 100 to 999 → 900 numbers → 2700 digits total

and so on...

Each group contributes a predictable number of digits, which helps us skip large chunks of the sequence without actually generating them.

Approach
Group the numbers by their digit length

All 1-digit numbers contribute 9 × 1 = 9 digits.
All 2-digit numbers contribute 90 × 2 = 180 digits.
All 3-digit numbers contribute 900 × 3 = 2700 digits.
Continue until we find the group that contains the n-th digit.
Skip complete groups
Subtract the total number of digits in each group from n until n falls inside one of these groups.
The group where n becomes less than or equal to the total digits of that group is the one that contains our target digit.

Find the exact number within that group
Once we know the group (say the 3-digit group), we can calculate which number within that group contains our n-th digit.

For example, if n is the 250th digit, we can divide (n - 1) by the number of digits per number in that group to find the offset.
Add this offset to the starting number of that group (like 100 for 3-digit numbers).
Find the exact digit within that number
The remainder from that division tells us which digit within that number we need.

For example, if the number is 120 and we need its first digit, that’s '1';
if it’s the second digit, it’s '2'; and so on.
Return that digit
After locating both the number and the correct digit inside it, we can directly return the result.

Complexity
Time complexity: O(log₁₀(n)) + O(1) = O(logn)
​
Space complexity: O(1)
Code
class Solution {
    public int findNthDigit(int n){

        long digitlength = 1;
        int start = 1;
        int end = 9;

        // digitlength*end = digits between the particular range
        // 1-9 , 10-99 , 100-999
        while(n > digitlength*end){
            n -= digitlength*end;
            digitlength++;
            start*=10;
            end*=10;
        }

        start += (n-1)/digitlength;
        String number = String.valueOf(start); // Exact number

        int index = (int) ((n - 1) % digitlength); // Exact digit 
        return number.charAt(index) - '0';
    
    }
}
