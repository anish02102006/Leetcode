Chalo 🔥
**LeetCode 1004 – Max Consecutive Ones III** ko **proper interview style** me samajhte hain
👉 **Brute → Better → Optimal (Sliding Window)**
👉 **Hinglish explanation + Java code + dry run + intuition**

---

## 🧠 Problem Samajh Lo

Tumhe ek **binary array** diya hai (`0` aur `1`)
Aur ek integer `k` diya hai

👉 Tum **maximum length** ka subarray find karo jisme:

* **At most `k` zeros** ko `1` me flip kar sakte ho

---

### Example:

```
nums = [1,1,1,0,0,0,1,1,1,1,0]
k = 2
```

👉 Answer:

```
6
```

(0,0 ko flip karke: `[1,1,1,0,0,1,1,1,1]`)

---

## 🔑 Key Observation

* Subarray chahiye (continuous)
* Condition: `zeros <= k`
  👉 Ye clearly **Sliding Window** problem hai

---

# 🟥 Brute Force Approach

## 💡 Idea:

* Har subarray check karo
* Count zeros
* Agar zeros ≤ k → length update

---

### ❌ Java Code (Brute)

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int zeros = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] == 0) zeros++;
                if (zeros > k) break;
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }
}
```

⏱ **Time:** O(N²)
📦 **Space:** O(1)
❌ TLE for large inputs

---

# 🟡 Better Approach (Prefix Sum)

## 💡 Idea:

* Prefix array banao of zero counts
* Har subarray ka zero count O(1) me mile

⏱ Still **O(N²)**, bas thoda optimized

👉 Interview me rarely use hota

---

# 🟢 Optimal Approach (Sliding Window – Two Pointer)

🔥 **Best & expected solution**

---

## 💡 Core Idea:

* Window ke andar **at most k zeros**
* Right pointer badhao
* Agar zeros > k → left pointer badhao

---

## 🧠 Steps:

1. `left = 0`
2. `zeros = 0`
3. `right` ko aage badhao
4. Agar `nums[right] == 0` → zeros++
5. Jab zeros > k:

   * Agar `nums[left] == 0` → zeros--
   * left++
6. Har step pe window size update

---

### 🧪 Dry Run (Short)

```
nums = [1,0,1,1,0]
k = 1

Window moves maintaining <=1 zero
Max window = [1,0,1,1] → length 4
```

---

### ✅ Java Code (Sliding Window)

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeros = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeros++;
            }

            while (zeros > k) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
```

---

### ⏱ Complexity:

* **Time:** O(N)
* **Space:** O(1)

⭐ Interview favorite
⭐ Very clean logic

---

# 📊 Comparison Table

| Approach       | Time  | Space | Verdict |
| -------------- | ----- | ----- | ------- |
| Brute          | O(N²) | O(1)  | ❌       |
| Prefix         | O(N²) | O(N)  | ❌       |
| Sliding Window | O(N)  | O(1)  | ⭐ BEST  |

---

## 🎯 Interview One-Liner

> “This is a classic sliding window problem where we maintain a window with at most k zeros.”

---

## ⚠️ Edge Cases

* `k = 0` → longest subarray of only 1s
* All 1s → full array
* All 0s & `k >= length` → full array

---

Agar chaho:

* 🔍 **Visual window movement**
* 🧠 **Why sliding window works**
* 🧪 **More test cases**
* 🔗 **Related problems list**

Bas bol do 😄
