Chalo bhai 😄
**LeetCode 75 – Sort Colors** (aka **Dutch National Flag problem**) ko **Brute → Better → Optimal** style me **Java + Hinglish** me bilkul crystal clear kar dete hain 🔥

---

## 🔴 Problem Samajh Lo

Tumhe ek array diya hai **nums** jisme sirf **0, 1, 2** hote hain.

👉 Tumhe array ko **in-place sort** karna hai
👉 **Library sort allowed nahi**
👉 **One-pass preferred**

---

### Example

```
nums = [2,0,2,1,1,0]
Output = [0,0,1,1,2,2]
```

---

# 🟠 Approach 1: Brute Force (Using Sort) ❌

### 🧠 Idea

* `Arrays.sort(nums)` use kar do

### ❌ Problem

* One-pass nahi
* Question constraint violate karta hai

---

### ❌ Interview me mat bolna

---

# 🟡 Approach 2: Counting Sort (Better)

### 🧠 Idea

* Count karo:

  * kitne 0
  * kitne 1
  * kitne 2
* Fir array overwrite karo

---

### ✅ Java Code (Better)

```java
class Solution {
    public void sortColors(int[] nums) {
        int c0 = 0, c1 = 0, c2 = 0;

        for (int n : nums) {
            if (n == 0) c0++;
            else if (n == 1) c1++;
            else c2++;
        }

        int idx = 0;
        while (c0-- > 0) nums[idx++] = 0;
        while (c1-- > 0) nums[idx++] = 1;
        while (c2-- > 0) nums[idx++] = 2;
    }
}
```

---

### ⏱ Time: `O(n)`

### 📦 Space: `O(1)`

⚠️ **Two-pass solution**

---

# 🟢 Approach 3: Dutch National Flag Algorithm (Optimal 🔥)

### 🧠 Idea

3 pointers use karo:

```
low   → next 0 position
mid   → current element
high  → next 2 position
```

---

### Rules

```
nums[mid] == 0 → swap(low, mid), low++, mid++
nums[mid] == 1 → mid++
nums[mid] == 2 → swap(mid, high), high--
```

---

### ✅ Java Code (Optimal)

```java
class Solution {
    public void sortColors(int[] nums) {

        int low = 0, mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            }
            else if (nums[mid] == 1) {
                mid++;
            }
            else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
```

---

### ⏱ Time: `O(n)` (One pass)

### 📦 Space: `O(1)`

🔥 **Best & Interview Favorite**

---

## 🧠 Dry Run

```
[2,0,2,1,1,0]
low=0 mid=0 high=5

mid=2 → swap(mid,high) → [0,0,2,1,1,2]
mid=0 → swap(low,mid) → low++,mid++
...
Result → [0,0,1,1,2,2]
```

---

## 🧠 Comparison Table

| Approach | Passes | Extra Space | Interview |
| -------- | ------ | ----------- | --------- |
| Sort     | many   | O(1)        | ❌         |
| Counting | 2      | O(1)        | ✅         |
| DNF Algo | 1      | O(1)        | 🔥 BEST   |

---

## 🎯 Interview One-Liner

> “Sir, ye Dutch National Flag problem hai — three pointers se one-pass me solve hota hai.”

---

## 🧩 Related Questions

* Move Zeroes
* Partition Array
* Sort Array by Parity
* 3-Way Partitioning

---

Agar chaaho to mai:

* **Visual animation**
* **Why mid not increment in case of 2**
* **Tricky edge cases**
* **Similar problems list**

sab karwa dunga 😎
Bas bolo bhai 👊

