Great 🔥
**LeetCode 18 – 4Sum** is a **classic two-pointer + sorting** problem.
Perfect for teaching **brute → optimized thinking**.

I’ll explain it **teacher-style, Hinglish**, with
👉 **Intuition**
👉 **Brute force**
👉 **Better approach**
👉 **Optimal approach**
👉 **Java code**
👉 **Board explanation flow**

---

# 🧠 Problem Samajh Lo (Simple Hinglish)

Tumhe ek integer array `nums` aur ek integer `target` diya hai.

👉 Tumhe **all unique quadruplets** find karne hain:

```
nums[a] + nums[b] + nums[c] + nums[d] == target
```

### Conditions:

* Indices alag hon
* Answer me **duplicate quadruplets nahi hone chahiye**

---

### Example

```
nums = [1,0,-1,0,-2,2]
target = 0

Output:
[
 [-2,-1,1,2],
 [-2,0,0,2],
 [-1,0,0,1]
]
```

---

# 🟥 Brute Force Approach

## 🧠 Intuition

Sab possible 4 elements try karo.

```text
4 nested loops
```

## ⏱️ Time Complexity

```
O(n⁴) ❌
```

❌ Very slow → TLE

---

# 🟡 Better Approach (HashSet)

## 🧠 Idea

* Fix 3 numbers
* 4th number = `target - sum(3)`
* Use HashSet

## ⏱️ Time

```
O(n³)
```

Still slow for large input

---

# 🟢 Optimal Approach (Sorting + Two Pointers)

## 🔥 Core Intuition (MOST IMPORTANT)

Ye problem **2Sum ka extension** hai:

```
2Sum → 2 pointers
3Sum → 1 loop + 2 pointers
4Sum → 2 loops + 2 pointers
```

---

## 🛠️ Steps

### Step 1: Sort the array

Sorting helps in:

* Duplicate removal
* Two-pointer usage

---

### Step 2: Fix first two elements

```text
i from 0 → n-4
j from i+1 → n-3
```

Skip duplicates for `i` and `j`.

---

### Step 3: Two pointers for remaining two

```text
left = j + 1
right = n - 1
```

* Agar sum == target → store answer
* Agar sum < target → left++
* Agar sum > target → right--

---

## ⏱️ Time Complexity

```
O(n³) ✅
```

✔ Accepted
✔ Interview standard

---

# ✅ Java Code (Optimal Solution)

```java
import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;

        if (n < 4) return res;

        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {

                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {

                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        left++;
                        right--;

                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;

                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}
```

---

# 🧑‍🏫 Whiteboard Teaching Flow

### Step 1:

Write sorted array

### Step 2:

Explain fixing `i` and `j`

### Step 3:

Explain two-pointer movement

### Step 4:

Show duplicate skipping logic

---

# 🧠 Interview Explanation (Short)

> We sort the array and fix two indices. For the remaining two, we apply the two-pointer technique to find valid quadruplets while skipping duplicates.

---

# 🔚 Summary Table

| Approach | Time  | Space |
| -------- | ----- | ----- |
| Brute    | O(n⁴) | O(1)  |
| Better   | O(n³) | O(n)  |
| Optimal  | O(n³) | O(1)  |

---

# 🟣 Teaching Tip (Very Useful)

While teaching:

* First explain **2Sum**
* Then **3Sum**
* Then extend to **4Sum**

Students connect instantly 💡

---

If you want next:

* ✅ **Dry run animation**
* ✅ **General k-Sum template**
* ✅ **Edge cases explanation**
* ✅ **Python version**

Just say 🚀
