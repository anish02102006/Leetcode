Intuition
Since each term in the sequence is built from the previous term, we need to simulate the process iteratively. Starting from "1", we generate each subsequent term by "reading" the current term.

The key insight is that we need to group consecutive identical digits together. When we scan through a string like "111221", we need to identify groups: "111", "22", and "1". For each group, we record two pieces of information: the count of digits and the digit itself.

To implement this grouping, we can use two pointers:

One pointer i marks the start of a group
Another pointer j moves forward to find where the group ends (when we encounter a different digit)
For each group found, we append the count (j - i) followed by the digit at position i to our result. This naturally builds the run-length encoding.

After processing all groups in the current term, we have our next term. We repeat this process n - 1 times to get from the first term to the n-th term.

The beauty of this approach is its simplicity - we're essentially implementing exactly what the problem describes: counting consecutive digits and saying what we see. No complex data structures or algorithms needed, just careful tracking of where each group of identical digits begins and ends.


Solution Approach
The solution uses a simulation approach with two-pointer technique to implement the run-length encoding for each iteration.

Algorithm Steps:

Initialize: Start with s = '1' as the first term of the sequence.

Iterate n - 1 times: Since we already have the first term, we need n - 1 iterations to reach the n-th term.

For each iteration, process the current string s:

Initialize an empty list t to build the next term
Use pointer i to track the current position in string s
Group consecutive identical digits:

While i < len(s):
Set j = i as a second pointer
Move j forward while s[j] == s[i] (same digit)
The count of consecutive digits is j - i
Append the count and the digit to list t:
t.append(str(j - i)) - add the count
t.append(str(s[i])) - add the digit itself
Move i to j to process the next group
Update the string: Join all elements in t to form the new string: s = ''.join(t)

Return: After n - 1 iterations, s contains the n-th term.

Example Walkthrough for n = 4:

Start: s = "1"
Iteration 1: "1" → one 1 → "11"
Iteration 2: "11" → two 1s → "21"
Iteration 3: "21" → one 2, one 1 → "1211"
Return: "1211"
Time Complexity: O(n × m) where n is the input parameter and m is the maximum length of any string in the sequence. Each iteration processes all characters in the current string.

Space Complexity: O(m) for storing the temporary list t and the string s.

Ready to land your dream job?
Unlock your dream job with a 5-minute evaluator for a personalized learning plan!
Example Walkthrough
Let's trace through the algorithm with n = 5 to find the 5th term of the count-and-say sequence.

Initial State: s = "1"

Iteration 1 (to get 2nd term):

Current string: "1"
Set i = 0, create empty list t = []
Group starting at i = 0:
j = 0, advance j while s[j] == s[0] (both are '1')
j stops at 1 (end of string)
Count = j - i = 1 - 0 = 1
Append: t = ["1", "1"] (one '1')
Update s = "11"
Iteration 2 (to get 3rd term):

Current string: "11"
Set i = 0, create empty list t = []
Group starting at i = 0:
j = 0, advance j while s[j] == s[0] (both are '1')
j stops at 2 (end of string)
Count = j - i = 2 - 0 = 2
Append: t = ["2", "1"] (two '1's)
Update s = "21"
Iteration 3 (to get 4th term):

Current string: "21"
Set i = 0, create empty list t = []
Group 1 starting at i = 0:
j = 0, advance j while s[j] == s[0] ('2')
j stops at 1 (different digit found)
Count = 1 - 0 = 1
Append: t = ["1", "2"] (one '2')
Move i to 1
Group 2 starting at i = 1:
j = 1, advance j while s[j] == s[1] ('1')
j stops at 2 (end of string)
Count = 2 - 1 = 1
Append: t = ["1", "2", "1", "1"] (one '1')
Update s = "1211"
Iteration 4 (to get 5th term):

Current string: "1211"
Set i = 0, create empty list t = []
Group 1 at i = 0: '1' appears once → append ["1", "1"]
Group 2 at i = 1: '2' appears once → append ["1", "2"]
Group 3 at i = 2: '1' appears twice → append ["2", "1"]
Final: t = ["1", "1", "1", "2", "2", "1"]
Update s = "111221"
Result: The 5th term is "111221"

The two-pointer technique efficiently identifies consecutive groups: pointer i marks the group start, pointer j finds the group end, and j - i gives us the count. 
This process naturally builds the run-length encoding required for each term.
code
class Solution {
    /**
     * Generates the nth term of the Count-and-Say sequence.
     * The sequence starts with "1" and each subsequent term describes the previous term.
     * 
     * @param n The position in the sequence to generate (1-indexed)
     * @return The nth term of the Count-and-Say sequence
     */
    public String countAndSay(int n) {
        // Initialize with the first term of the sequence
        String currentSequence = "1";
      
        // Generate each subsequent term until we reach the nth term
        while (--n > 0) {
            StringBuilder nextSequence = new StringBuilder();
          
            // Process the current sequence to generate the next one
            for (int currentIndex = 0; currentIndex < currentSequence.length();) {
                // Find the end position of consecutive identical digits
                int endIndex = currentIndex;
                while (endIndex < currentSequence.length() && 
                       currentSequence.charAt(endIndex) == currentSequence.charAt(currentIndex)) {
                    endIndex++;
                }
              
                // Append the count of consecutive digits
                int count = endIndex - currentIndex;
                nextSequence.append(count);
              
                // Append the digit itself
                nextSequence.append(currentSequence.charAt(currentIndex));
              
                // Move to the next group of digits
                currentIndex = endIndex;
            }
          
            // Update the current sequence for the next iteration
            currentSequence = nextSequence.toString();
        }
      
        return currentSequence;
    }
}
Time and Space Complexity
Time Complexity: O(n * m) where n is the input parameter and m is the maximum length of the string generated during the process.

The algorithm performs n-1 iterations. In each iteration, it processes every character of the current string s. The length of the string grows with each iteration, but the growth rate is bounded. In the worst case, the string length can grow by a factor of 2 (when all digits are different), but typically the growth is less dramatic due to run-length encoding compression. The processing of each character involves constant-time operations (comparisons and appends). Therefore, the overall time complexity is O(n * m) where m represents the length of the longest intermediate string generated.

Space Complexity: O(m) where m is the maximum length of the string generated.

The algorithm uses additional space for:

The string s which stores the current result: O(m)
The list t which temporarily stores the components of the next iteration's string: O(m)
Variables i and j for iteration: O(1)
Since we're building a new string in each iteration and the old string is replaced, we only need to consider the space for the current and next string at any point. The maximum space used is proportional to the length of the longest string generated during the process, which gives us O(m) space complexity.
