Intuition
slow pointer moves once and fast pointer move twice.

Approach
Algorithm Overview:

Initialize two pointers, slow and fast, to the first element of the input list nums.
Iterate through the list using the Floyd's Tortoise and Hare algorithm(See below) to find a cycle.
Once a cycle is found, reset one of the pointers to the beginning of the list and continue iterating until both pointers meet again.
Detailed Explanation:

Initialize slow and fast pointers to the first element of the input list nums.
Enter a loop to detect a cycle:
a. Update slow to the next element using nums[slow].
b. Update fast to the next element after nums[fast], effectively moving two steps.
c. Check if slow is equal to fast. If they are equal, a cycle has been found, and exit the loop.
After finding the cycle, reset one of the pointers (slow2) to the beginning of the list.
Enter a loop to find the duplicate element:
a. Update slow using nums[slow].
b. Update slow2 using nums[slow2].
c. Continue this process until slow is equal to slow2, which represents the duplicate element.
Return the duplicate element found (slow).
Floyd's Tortoise and Hare algorithm
Floyd's Tortoise and Hare algorithm, also known as Floyd's Cycle Detection algorithm, is an algorithm used to detect loops (cycles) in data structures like linked lists or arrays. It has been proven to reliably detect cycles under specific circumstances and can be applied to finding duplicate elements.

This algorithm employs two pointers, referred to as the "tortoise" and the "hare," to traverse the list.

Tortoise: A pointer that advances one step at a time through the list.
Hare: A pointer that advances two steps at a time through the list.
Using these pointers, you progress until the hare catches up with the tortoise or a cycle is detected.

Here's an overview of the algorithm:

Phase 1 (Cycle Detection):
Move the tortoise and hare, advancing the hare twice as fast as the tortoise, until the hare catches up with the tortoise or a cycle is detected. If a cycle is detected, reset the tortoise and move the hare back to its position before the reset.

Phase 2 (Cycle Start Detection):
Move the tortoise and hare one step at a time until they match again. The position where they match again is the starting point of the cycle, corresponding to the duplicate element.

Let's explain why this works for the problem at hand:

Properties of Floyd's Tortoise and Hare Algorithm:
The algorithm ensures that the tortoise and hare will match again at some position in the list. Exploiting this property, if a cycle exists, the tortoise and hare will certainly match at some position within the cycle.

Relation between Duplicate Element and Cycle:
In the presence of a duplicate element, the duplicate corresponds to the starting point of the cycle. Starting from the first element as the tortoise, and moving through the duplicates until reaching a duplicate (cycle start), the hare will join, and the tortoise and hare will match again inside the cycle.

Therefore, the Floyd's Tortoise and Hare Algorithm provides an efficient and reliable way to find duplicate elements.

We can apply the same algorithm to Linked List cycle Ⅱ. I calculate movement distance of the two pointers with easy mathematics knowledge. I hope you can understand it easily and deeply.



How it works
Input: nums = [1,3,4,2,2]
Initialze slow and fast pointers with nums[0]

slow = 1 (nums[0])
fast = 1 (nums[0])
Start iteration until the both pointers are the same value.

Iteration 1:
slow: 3 (nums[1])
fast: 2 (nums[nums[1]] = nums[3])

Iteration 2:
slow: 2 (nums[3])
fast: 2 (nums[nums[2]] = nums[4])

Now the both pointers are equal, so break the loop.
Set nums[0] to slow2 pointer. Start Itetation again until slow and slow2 are equal.

Iteration 1:
slow: 4 (nums[2])
slow2: 3 (nums[1])

Iteration 2:
slow: 2 (nums[4])
slow2: 2 (nums[3])

Now the both pointers are equal, so break the loop.
Input: 2
Visualization of Floyd's Tortoise and Hare algorithm
IMG_6561.JPG

At first, Let's think with Linked List and try to find starting node of cycle which is y.

Now, slow and fast pointers are at node x

slow pointer moves once which is at node y
fast pointer moves twice which is at node z

they don't meet each other, so let's continue

slow pointer moves once which is at node z
fast pointer moves twice which is at node z

now they meet each other, so let's initialize fast pointer with node x

slow pointer moves once which is at node y
fast pointer moves once which is at node y

That's why we can find starting node of cycle node y.

Let's calculate movement distance of slow and fast pointers

slow pointer moves A + B
fast pointer moves A + B + C + B

Since slow pointer moves once and fast pointer moves twice, the below formula is vaild.

2(A + B) = A + B + C + B

Let's simplify the formula

2A + 2B = 2B + A + C

Delete 2B at both sides
2A = A + C

Delete A at both sides
A = C

so, it turns out distance A and distance C is actually the same. When two pointers meet at node z then fast pointer is initialized with node x again and each pointer moves through path A or C one by one, so that we can find starting node of cycle which is node y.

⭐️Let's apply this idea to this question. Look at the below list in the picture.
now slow and fast pointers are at 1

slow pointer moves once which is at 3
fast pointer moves twice which is at 2

they are not the same. Let's continue.

slow pointer moves once which is at 2
fast pointer moves twice which is at 2

they meet each other at value 2.

Let's initialize slow2 with 1. And distance between 2 and 4 cycle is similar to C distance of the above list.

slow pointer moves once which is at 4
slow2 pointer moves once which is at 3

they are not the same. Let's continue.

slow pointer moves once which is at 2
slow2 pointer moves once which is at 2

Finally, they are the same value.

Output: 2
Wait! For just in case, is there anyone who think why we need the second loop with slow and slow2? Because we found 2 in the first loop.

The second loop with slow and slow2 is necessary to find the starting point of the cycle, which corresponds to the duplicate element in the array. Let me explain why this is needed:

In the first loop (cycle detection phase) using the Floyd's Tortoise and Hare algorithm, we detect that there is a cycle in the array by finding a position where the slow and fast pointers meet. However, this position is not necessarily the starting point of the cycle or the duplicate element. It's just a position within the cycle.

The second loop (cycle start detection phase) helps us find the actual starting point of the cycle, which is the duplicate element we are looking for. The starting point of the cycle is the position where the slow and slow2 pointers meet. This second loop is crucial to pinpoint the exact duplicate element in the array.

Without this second loop, we would have detected the presence of a cycle but would not have determined the duplicate element. The second loop is essential for identifying the duplicate element within the cycle.

Try to run the code below with input [2,5,9,6,9,3,8,9,7,1]. We should return 9 and the first loop should stop at 7.

Complexity
Time complexity: O(n)

Space complexity: O(1)

class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];

            if (slow == fast) {
                break;
            }
        }

        int slow2 = nums[0];

        while (slow != slow2) {
            slow = nums[slow];
            slow2 = nums[slow2];
        }

        return slow;        
    }
}
