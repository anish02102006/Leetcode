# Intuition
The problem requires to convert a given absolute path to a simplified canonical path. The simplified canonical path should have the following format:

The path starts with a single slash '/'.

Any two directories are separated by a single slash '/'.

The path does not end with a trailing '/'.

The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..').

# Approach
The problem can be solved using a stack to keep track of the directories in the path.
We split the input path by slash '/', iterate over the directories, and perform the following operations:

Ignore the current directory '.' and empty directories.
Go one level up for double period '..' by popping the top element from the stack if it is not empty.
For any other directory, push it to the stack.
Finally, we join the directories in the stack with slash '/' and add a slash at the beginning to form the simplified canonical path.
3 Complexity
Time complexity:
The time complexity of the algorithm is O(n), where n is the length of the input path. This is because we iterate over each directory in the path only once.

Space complexity:
The space complexity of the algorithm is O(n) where n is the length of

Please Upvote👍👍
Thanks for visiting my solution.😊 Keep Learning
Please give my solution an upvote! 👍
It's a simple way to show your appreciation and
keep me motivated. Thank you! 😊
``````Code
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>(); // create a stack to keep track of directories
        String[] directories = path.split("/"); // split the path by slash '/'
        for (String dir : directories) { // iterate over the directories
            if (dir.equals(".") || dir.isEmpty()) { // ignore the current directory '.' and empty directories
                continue;
            } else if (dir.equals("..")) { // go one level up for double period '..'
                if (!stack.isEmpty()) { // if stack is not empty, pop the top element
                    stack.pop();
                }
            } else { // for any other directory, push it to the stack
                stack.push(dir);
            }
        }
        return "/" + String.join("/", stack); // join the directories in the stack with slash '/' and add a slash at the beginning
    }
}
