Below is a **complete, exam-ready + interview-ready explanation** of
👉 **LeetCode: Lemonade Change**
with **all approaches, algorithm steps, Java code, and dry run**.

---

# 🍋 Lemonade Change (LeetCode)

## **Problem Statement (Simplified)**

* Lemonade costs **$5**
* Customers pay with **$5, $10, or $20**
* You must give **correct change**
* Initially, you have **no money**
* Return `true` if you can serve all customers, else `false`

---

## ✅ Key Observation

You only need to track **how many $5 and $10 bills you have**
(No need to track $20 bills, since they are never used for change)

---

# ✅ APPROACH 1: **Greedy (Optimal & Recommended)**

### 🔹 Core Idea

Always give **change using the largest bills possible** to save smaller bills for future customers.

---

## 💡 Rules for Each Customer

### Case 1: Customer pays **$5**

* No change needed
* Increase `$5` count

### Case 2: Customer pays **$10**

* Need **$5** as change
* If no `$5`, return `false`

### Case 3: Customer pays **$20**

* Need **$15** change
* Prefer:

  1. `$10 + $5` (BEST)
  2. `3 × $5` (only if above not possible)
* Else → return `false`

---

## 🧠 Algorithm (Step-by-Step)

```
Initialize five = 0, ten = 0

For each bill in bills:
    if bill == 5:
        five++
    else if bill == 10:
        if five == 0: return false
        five--
        ten++
    else (bill == 20):
        if ten > 0 and five > 0:
            ten--
            five--
        else if five >= 3:
            five -= 3
        else:
            return false

return true
```

---

## 💻 Java Code (Greedy)

```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;

        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } 
            else if (bill == 10) {
                if (five == 0) return false;
                five--;
                ten++;
            } 
            else { // bill == 20
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
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

# 🧪 DRY RUN (Very Important)

### Example 1

```
bills = [5, 5, 5, 10, 20]
```

| Customer | Bill | five | ten | Action        |
| -------- | ---- | ---- | --- | ------------- |
| 1        | 5    | 1    | 0   | Take $5       |
| 2        | 5    | 2    | 0   | Take $5       |
| 3        | 5    | 3    | 0   | Take $5       |
| 4        | 10   | 2    | 1   | Give $5       |
| 5        | 20   | 1    | 0   | Give $10 + $5 |

✅ **Return true**

---

### Example 2

```
bills = [5, 5, 10, 10, 20]
```

| Customer | Bill | five | ten | Result      |
| -------- | ---- | ---- | --- | ----------- |
| 1        | 5    | 1    | 0   | OK          |
| 2        | 5    | 2    | 0   | OK          |
| 3        | 10   | 1    | 1   | OK          |
| 4        | 10   | 0    | 2   | OK          |
| 5        | 20   | 0    | 2   | ❌ No change |

❌ **Return false**

---

# ❌ APPROACH 2: Brute Force (Not Recommended)

### Idea

* Try all possible combinations of bills for change

### Problems

* Complex
* Inefficient
* Not scalable

⛔ **Never used in interviews**

---

# ⚠️ APPROACH 3: Using HashMap (Unnecessary)

```java
Map<Integer, Integer> map = new HashMap<>();
```

* Works logically
* Extra space
* Slower than greedy

⛔ **Avoid unless asked**

---

# 🎯 Why Greedy Works?

* Future customers may need **small bills**
* Using larger bills first preserves flexibility
* Local optimal choice → global optimal solution

---

# 📌 Final Summary

| Aspect           | Value               |
| ---------------- | ------------------- |
| Best Approach    | Greedy              |
| Time Complexity  | O(n)                |
| Space Complexity | O(1)                |
| Interview Level  | Easy–Medium         |
| Key Concept      | Greedy + Simulation |

---

If you want, next I can:

* 🔥 Convert this into **interview answer format**
* 🧠 Explain **why greedy never fails here**
* 📊 Compare with **similar greedy problems**

Just tell me 👍
