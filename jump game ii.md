Below is a **complete, interview-ready explanation** of
👉 **LeetCode: Jump Game II**
covering **ALL approaches**, **algorithms**, **dry runs**, and **Java code**.

---

# 🏃‍♂️ Jump Game II (LeetCode)

## 📘 Problem Statement (Simplified)

You are given an array `nums` where:

* `nums[i]` = maximum jump length from index `i`
* You start at index `0`
* You must reach the **last index**
* Return the **minimum number of jumps**

👉 You are **guaranteed** to reach the end.

---

## 🎯 Key Difference from Jump Game I

| Problem      | Goal                           |
| ------------ | ------------------------------ |
| Jump Game I  | Can you reach the end?         |
| Jump Game II | **Minimum jumps to reach end** |

---

# ✅ APPROACH 1: **Greedy (Optimal & Best)** ⭐⭐⭐⭐⭐

## 💡 Core Idea

Think in terms of **levels (BFS-like)**:

* Each jump covers a **range**
* When current range ends → take a jump
* Expand to the farthest reachable index

---

## 🧠 Algorithm (Greedy – Forward)

```
jumps = 0
currentEnd = 0
farthest = 0

for i from 0 to n-2:
    farthest = max(farthest, i + nums[i])

    if i == currentEnd:
        jumps++
        currentEnd = farthest

return jumps
```

⚠️ Loop only till `n-2` because no jump needed at last index.

---

## 💻 Java Code (Greedy – Best)

```java
class Solution {
    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        return jumps;
    }
}
```

---

## ⏱ Complexity

* **Time:** `O(n)`
* **Space:** `O(1)`

---

## 🧪 DRY RUN (VERY IMPORTANT)

### Example 1

```
nums = [2,3,1,1,4]
```

| i | nums[i] | farthest | currentEnd | jumps |
| - | ------- | -------- | ---------- | ----- |
| 0 | 2       | 2        | 0 → 2      | 1     |
| 1 | 3       | 4        | 2          | 1     |
| 2 | 1       | 4        | 2 → 4      | 2     |
| 3 | 1       | 4        | 4          | 2     |

✅ **Answer = 2**

---

### Example 2

```
nums = [2,3,0,1,4]
```

| i | farthest | jump   |
| - | -------- | ------ |
| 0 | 2        | jump=1 |
| 1 | 4        |        |
| 2 | 4        | jump=2 |

✅ **Answer = 2**

---

## 🧠 Intuition (Interview Gold)

* Each jump defines a **window**
* While inside window → explore maximum reach
* When window ends → must jump
* Similar to **BFS level traversal**

---

# ✅ APPROACH 2: **Dynamic Programming (Bottom-Up)**

## 💡 Idea

`dp[i]` = minimum jumps to reach index `i`

---

## 🧠 Algorithm

```
dp[0] = 0
for i from 1 to n-1:
    dp[i] = infinity
    for j from 0 to i-1:
        if j + nums[j] >= i:
            dp[i] = min(dp[i], dp[j] + 1)
```

---

## 💻 Java Code (DP)

```java
import java.util.Arrays;

class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i && dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
    }
}
```

---

## ⏱ Complexity

* **Time:** `O(n²)`
* **Space:** `O(n)`

⛔ **Not optimal for large inputs**

---

# ⚠️ APPROACH 3: **BFS (Conceptual)**

* Treat indices as nodes
* Jump ranges as edges
* BFS gives shortest path

⛔ Equivalent to greedy
⛔ Extra space & overhead

---

# ❌ APPROACH 4: **Backtracking / Recursion**

* Try all jump paths
* Exponential time

⛔ **Will TLE**

---

# 🧪 DRY RUN (DP Quick)

```
nums = [2,3,1,1,4]
dp = [0,∞,∞,∞,∞]
```

| i | dp[i] |
| - | ----- |
| 1 | 1     |
| 2 | 1     |
| 3 | 2     |
| 4 | 2     |

---

# 📊 Final Comparison

| Approach     | Time        | Space | Recommended |
| ------------ | ----------- | ----- | ----------- |
| Greedy       | O(n)        | O(1)  | ⭐⭐⭐⭐⭐       |
| DP           | O(n²)       | O(n)  | ❌           |
| BFS          | O(n)        | O(n)  | ❌           |
| Backtracking | Exponential | High  | ❌           |

---

# 🎯 Interview One-Liner

> We greedily expand the farthest reachable index within the current jump range and increment jumps when the range ends.

---

If you want next:

* 🔥 **Why greedy always gives minimum jumps**
* 📊 **Comparison: Jump Game I vs II**
* 🧠 **Visualization with levels**

Just tell me 👍
