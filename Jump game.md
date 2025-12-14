Below is a **complete, structured, interview-ready explanation** of
👉 **LeetCode: Jump Game**
with **ALL approaches**, **algorithms**, **dry runs**, and **Java code**.

---

# 🏃 Jump Game (LeetCode)

## 📘 Problem Statement (Simplified)

You are given an integer array `nums`.

* Each element represents the **maximum jump length** from that index
* You start at index `0`
* Determine if you can **reach the last index**

---

## 🎯 Key Insight

You don’t need to know *how* you jump, only whether you **can reach or cross the last index**.

---

# ✅ APPROACH 1: **Greedy (Best & Optimal)** ⭐⭐⭐⭐⭐

## 💡 Core Idea

Maintain the **farthest index** you can reach so far.

* If at any index `i`, `i > farthestReach`, you are stuck → return `false`
* If `farthestReach >= lastIndex`, return `true`

---

## 🧠 Algorithm (Greedy)

```
farthest = 0

for i from 0 to n-1:
    if i > farthest:
        return false
    farthest = max(farthest, i + nums[i])

    if farthest >= n-1:
        return true

return true
```

---

## 💻 Java Code (Greedy)

```java
class Solution {
    public boolean canJump(int[] nums) {
        int farthest = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > farthest) return false;
            farthest = Math.max(farthest, i + nums[i]);
        }
        return true;
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
nums = [2, 3, 1, 1, 4]
```

| Index | nums[i] | farthest before | farthest after |
| ----- | ------- | --------------- | -------------- |
| 0     | 2       | 0               | max(0, 0+2)=2  |
| 1     | 3       | 2               | max(2, 1+3)=4  |
| 2     | 1       | 4               | max(4, 2+1)=4  |
| 3     | 1       | 4               | max(4, 3+1)=4  |
| 4     | 4       | 4               | reached end    |

✅ **Return true**

---

### Example 2

```
nums = [3, 2, 1, 0, 4]
```

| Index | nums[i] | farthest       |
| ----- | ------- | -------------- |
| 0     | 3       | 3              |
| 1     | 2       | 3              |
| 2     | 1       | 3              |
| 3     | 0       | 3              |
| 4     | 4       | ❌ i > farthest |

❌ **Return false**

---

# ✅ APPROACH 2: **Dynamic Programming (Bottom-Up)**

## 💡 Idea

Mark whether each index is reachable.

---

## 🧠 Algorithm

```
dp[0] = true

for i from 0 to n-1:
    if dp[i]:
        for j from 1 to nums[i]:
            dp[i+j] = true

return dp[n-1]
```

---

## 💻 Java Code (DP)

```java
class Solution {
    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;

        for (int i = 0; i < nums.length; i++) {
            if (!dp[i]) continue;
            for (int j = 1; j <= nums[i] && i + j < nums.length; j++) {
                dp[i + j] = true;
            }
        }
        return dp[nums.length - 1];
    }
}
```

---

## ⏱ Complexity

* **Time:** `O(n²)`
* **Space:** `O(n)`

⛔ **Not optimal for large inputs**

---

# ⚠️ APPROACH 3: **Backtracking / Recursion**

## 💡 Idea

Try all possible jumps recursively.

```java
boolean dfs(int i):
    if i >= lastIndex return true
    for each jump:
        if dfs(next) return true
```

⛔ **Time:** Exponential
⛔ **Will TLE**

---

# ⚠️ APPROACH 4: **Greedy (Backward)**

## 💡 Idea

Start from the end and move backwards.

---

## 🧠 Algorithm

```
goal = lastIndex

for i from n-1 to 0:
    if i + nums[i] >= goal:
        goal = i

return goal == 0
```

---

## 💻 Java Code (Backward Greedy)

```java
class Solution {
    public boolean canJump(int[] nums) {
        int goal = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }
        return goal == 0;
    }
}
```

---

## ⏱ Complexity

* **Time:** `O(n)`
* **Space:** `O(1)`

---

# 🧪 Dry Run (Backward Greedy)

```
nums = [2,3,1,1,4]
goal = 4
```

| i | nums[i] | i + nums[i] | goal |
| - | ------- | ----------- | ---- |
| 4 | 4       | 8           | 4    |
| 3 | 1       | 4           | 3    |
| 2 | 1       | 3           | 2    |
| 1 | 3       | 4           | 1    |
| 0 | 2       | 2           | 0    |

✅ **goal == 0**

---

# 📌 Final Comparison

| Approach          | Time        | Space | Recommended |
| ----------------- | ----------- | ----- | ----------- |
| Greedy (Forward)  | O(n)        | O(1)  | ⭐⭐⭐⭐⭐       |
| Greedy (Backward) | O(n)        | O(1)  | ⭐⭐⭐⭐⭐       |
| DP                | O(n²)       | O(n)  | ❌           |
| Backtracking      | Exponential | High  | ❌           |

---

# 🎯 Interview One-Line Answer

> We track the farthest index reachable at each step. If we ever reach an index beyond that range, the jump is impossible.

---

If you want next:

* 🔥 **Jump Game II (Minimum jumps)**
* 🧠 **Why greedy is always correct**
* 📊 **Similar greedy problems**

Just tell me 👍
