Intuition
The key insight is that we need to track unmatched parentheses. When we see a valid pair (an opening '(' followed eventually by a closing ')'), these two cancel each other out and don't require any additions.

Think of it like balancing a scale:

Every '(' adds weight to the left side
Every ')' adds weight to the right side
When we can match a ')' with a previous '(', they balance out
The challenge is that order matters - a ')' can only match with a '(' that comes before it. This naturally leads us to use a stack:

When we see '(', we're creating a "debt" that needs to be paid with a future ')'
When we see ')', we check if there's an unpaid debt (an unmatched '(' in our stack)
If yes, they match! We remove the debt (pop from stack)
If no, this ')' itself becomes unmatched and needs a '(' added somewhere before it
After processing the entire string, whatever remains in our stack represents:

Unmatched '(' characters that need ')' additions
Unmatched ')' characters that need '(' additions
The beauty of this approach is that we don't need to actually decide where to insert the parentheses - we just count how many are unmatched. Each unmatched parenthesis requires exactly one addition to balance it out, so the stack size at the end gives us our answer directly.

Learn more about Stack and Greedy patterns.


Solution Approach
The solution implements a greedy stack-based approach to count unmatched parentheses.

Algorithm Steps:

Initialize an empty stack - This will store all unmatched parentheses as we process the string.

Iterate through each character c in the string s:

If c is a closing parenthesis ')':
Check if the stack is not empty AND the top element is an opening parenthesis '('
If both conditions are true, we have found a matching pair:
Pop the '(' from the stack (successful match)
Otherwise, this ')' is unmatched:
Push ')' onto the stack
If c is an opening parenthesis '(':
Always push it onto the stack (it's waiting for a future match)
Return the stack size - After processing all characters, the stack contains only unmatched parentheses. Each unmatched parenthesis requires exactly one addition to balance it.

Why This Works:

The greedy nature of the algorithm ensures we match parentheses as soon as possible. When we see a ')', we immediately try to match it with the most recent unmatched '('. This is optimal because:

Matching early prevents accumulation of unmatched parentheses
Each match reduces the number of additions needed by 2 (one '(' and one ')' that would otherwise need partners)
Time Complexity: O(n) where n is the length of the string - we process each character once.

Space Complexity: O(n) in the worst case when all parentheses are unmatched (e.g., all '(' or all ')').

Ready to land your dream job?
Unlock your dream job with a 5-minute evaluator for a personalized learning plan!
Example Walkthrough
Let's walk through the example s = "())(" step by step to illustrate how the solution works.

Initial State:

String: "())("
Stack: [] (empty)
Position: 0
Step 1: Process '(' at index 0

Character is '(', so push it to stack
Stack: ['(']
Step 2: Process ')' at index 1

Character is ')'
Check: Is stack non-empty AND top element is '('? Yes!
We found a match! Pop '(' from stack
Stack: [] (empty again)
Step 3: Process ')' at index 2

Character is ')'
Check: Is stack non-empty AND top element is '('? No (stack is empty)
This ')' is unmatched, push it to stack
Stack: [')']
Step 4: Process '(' at index 3

Character is '(', so push it to stack
Stack: [')', '(']
Final Result:

Stack contains: [')', '(']
Stack size = 2
Answer: 2 (we need to add 2 parentheses to make the string valid)
To verify: The unmatched ')' at position 2 needs a '(' before it, and the unmatched '(' at position 3 needs a ')' after it. We could transform the string to "(())())" by adding '(' at the beginning and ')' at the end, making it valid with exactly 2 additions.

Solution Implementation
Python
Java
C++
TypeScript
class Solution {
    /**
     * Calculates the minimum number of parentheses additions needed to make a valid string.
     * Uses a stack to track unmatched parentheses.
     * 
     * @param s Input string containing '(' and ')' characters
     * @return Minimum number of parentheses to add to make the string valid
     */
    public int minAddToMakeValid(String s) {
        // Stack to store unmatched parentheses
        Deque<Character> stack = new ArrayDeque<>();
      
        // Process each character in the string
        for (char currentChar : s.toCharArray()) {
            // Check if current character can form a valid pair with top of stack
            if (currentChar == ')' && !stack.isEmpty() && stack.peek() == '(') {
                // Found a matching pair, remove the opening parenthesis
                stack.pop();
            } else {
                // No match found, add current character to stack
                stack.push(currentChar);
            }
        }
      
        // The size of stack represents unmatched parentheses
        // Each unmatched parenthesis needs a corresponding one to be added
        return stack.size();
    }
}
Time and Space Complexity
The time complexity is O(n), where n is the length of the string s. This is because the algorithm iterates through each character in the string exactly once, performing constant-time operations (checking conditions, pushing to stack, or popping from stack) for each character.

The space complexity is O(n), where n is the length of the string s. In the worst-case scenario, all characters in the string are unmatched (for example, a string of all '(' or all ')'), which means all characters would be pushed onto the stack, requiring O(n) additional space.
