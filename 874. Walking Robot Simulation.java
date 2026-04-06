Idea
Start from (0, 0) facing North
Process each command one by one
Track direction using an index:
0 = North, 1 = East, 2 = South, 3 = West
Use a set to store obstacles for fast lookup
Move one step at a time to correctly handle obstacles
At every step, we calculate the squared distance from origin and keep track of the maximum.

Approach
Store Obstacles

Insert all obstacle coordinates into a set<pair<int,int>> for O(log n) lookup.
Directions

Use a direction array:
North → (0, 1)
East  → (1, 0)
South → (0, -1)
West  → (-1, 0)
Turning:
Right → (dir + 1) % 4
Left → (dir + 3) % 4
Process Commands

If command is:
-1 → turn right
-2 → turn left
k > 0 → move forward k steps
Step-by-Step

Move one unit at a time
Before moving, check if next position is an obstacle
If blocked → stop current command
Track Maximum Distance

After every successful move:
dist = x*x + y*y
Update maximum distance
Simulation
Example:

commands = [4, -1, 3]
obstacles = []

Steps:

Start at (0,0), facing North
Move 4 → (0,4)
Turn right → facing East
Move 3 → (3,4)
Max distance:
3^2 + 4^2 = 9 + 16 = 25

Code
import java.util.*;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Store obstacles
        Set<String> blocked = new HashSet<>();
        for (int[] o : obstacles) {
            blocked.add(o[0] + "," + o[1]);
        }

        // Directions: North, East, South, West
        int[][] directions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        int x = 0, y = 0;
        int dir = 0; // initially facing North
        int maxDist = 0;

        for (int cmd : commands) {
            if (cmd == -1) {
                dir = (dir + 1) % 4; // turn right
            } 
            else if (cmd == -2) {
                dir = (dir + 3) % 4; // turn left
            } 
            else {
                while (cmd-- > 0) {
                    int nx = x + directions[dir][0];
                    int ny = y + directions[dir][1];

                    // check obstacle
                    if (blocked.contains(nx + "," + ny)) break;

                    x = nx;
                    y = ny;

                    maxDist = Math.max(maxDist, x * x + y * y);
                }
            }
        }

        return maxDist;
    }
}
