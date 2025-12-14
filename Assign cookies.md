The problem **“Assign Cookies”** on LeetCode asks:

> You have `n` children and `m` cookies. Each child `i` has a greed factor `g[i]` (minimum cookie size to be satisfied). Each cookie `j` has a size `s[j]`. Assign cookies to maximize the number of satisfied children. Each child can get at most one cookie.

We can solve this using **greedy algorithms**. Let’s go through all approaches with **Java code**.

---

## **1. Greedy Approach – Sort + Two Pointers**

### **Idea**

* Sort both `g` (children greed) and `s` (cookie sizes)
* Assign the **smallest available cookie** that can satisfy each child
* Use **two pointers** to traverse `g` and `s`

### **Steps**

1. Sort `g` and `s`
2. Initialize `i = 0` (child pointer), `j = 0` (cookie pointer)
3. While `i < g.length && j < s.length`:

   * If `s[j] >= g[i]` → assign cookie, `i++`, `j++`
   * Else → `j++` (try next bigger cookie)
4. Return `i` → number of satisfied children

### **Code (Java)**

```java
import java.util.Arrays;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                i++; // child satisfied
            }
            j++; // move to next cookie
        }
        
        return i;
    }
}
```

**Time Complexity:** O(n log n + m log m) (sorting)
**Space Complexity:** O(1) (excluding input)

---

## **2. Greedy with Priority Queue (Optional)**

* Insert cookies into a **priority queue** (min-heap)
* For each child (sorted by greed), poll smallest cookie ≥ greed
* More complex, less efficient than two-pointer approach

### **Code (Java)**

```java
import java.util.*;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int cookie : s) pq.offer(cookie);
        
        int count = 0;
        for (int greed : g) {
            while (!pq.isEmpty() && pq.peek() < greed) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                pq.poll();
                count++;
            }
        }
        
        return count;
    }
}
```

**Time Complexity:** O(n log n + m log m)
**Space Complexity:** O(m)

---

## **3. Brute Force (Inefficient)**

* Try all assignments of cookies to children
* Check all combinations → **exponential time**
* Not feasible for large inputs
* Typically used for understanding, not coding interviews

---

## **4. Summary**

| Approach            | Time Complexity      | Space Complexity | Notes                     |
| ------------------- | -------------------- | ---------------- | ------------------------- |
| Greedy Two Pointers | O(n log n + m log m) | O(1)             | Most efficient and common |
| Priority Queue      | O(n log n + m log m) | O(m)             | Works but less optimal    |
| Brute Force         | O(m^n)               | O(n)             | Not practical             |

---

✅ **Key Concept:**

* Sort children and cookies
* Always satisfy the **least greedy child with the smallest sufficient cookie**
* Greedy works because satisfying smaller greed first **does not hurt larger greed later**

---

I can also provide a **step-by-step example with cookies and children showing how the greedy algorithm works**.

Do you want me to do that?
Sure! Let’s go **step by step** using an example to show how the **greedy two-pointer algorithm** works for the **Assign Cookies** problem.

---

## **Example**

### **Input**

```text
Children greed: g = [1, 2, 3]
Cookie sizes: s = [1, 1, 2, 3]
```

---

## **Step 1: Sort Arrays**

```text
g (greed) = [1, 2, 3]
s (cookie sizes) = [1, 1, 2, 3]
```

* Both arrays are already sorted in this example

---

## **Step 2: Initialize Pointers**

* `i = 0` → points to first child
* `j = 0` → points to first cookie
* `count = 0` → number of satisfied children

---

## **Step 3: Traverse Arrays**

### **Iteration 1**

* `g[0] = 1`, `s[0] = 1`
* `s[j] >= g[i]` → `1 >= 1` ✅
* Assign cookie → `i++ = 1`, `j++ = 1`, `count = 1`

---

### **Iteration 2**

* `g[1] = 2`, `s[1] = 1`
* `s[j] < g[i]` → `1 < 2` ❌
* Move to next cookie → `j++ = 2`

---

### **Iteration 3**

* `g[1] = 2`, `s[2] = 2`
* `s[j] >= g[i]` → `2 >= 2` ✅
* Assign cookie → `i++ = 2`, `j++ = 3`, `count = 2`

---

### **Iteration 4**

* `g[2] = 3`, `s[3] = 3`
* `s[j] >= g[i]` → `3 >= 3` ✅
* Assign cookie → `i++ = 3`, `j++ = 4`, `count = 3`

---

## **Step 4: Terminate**

* `i = g.length` → all children considered
* Maximum satisfied children = `count = 3`

✅ **All children are satisfied**.

---

## **Step 5: Visualization**

| Child greed | Cookie considered | Assign? | i (child) | j (cookie) | count |
| ----------- | ----------------- | ------- | --------- | ---------- | ----- |
| 1           | 1                 | Yes     | 1         | 1          | 1     |
| 2           | 1                 | No      | 1         | 2          | 1     |
| 2           | 2                 | Yes     | 2         | 3          | 2     |
| 3           | 3                 | Yes     | 3         | 4          | 3     |

---

### **Key Takeaways**

1. Always **satisfy the least greedy child** with the **smallest sufficient cookie**
2. Sorting ensures the algorithm is **optimal**
3. Two-pointer traversal guarantees **O(n log n + m log m) time**

---

If you want, I can also create a **diagram showing children and cookies with arrows for assignments**, which makes this example **super easy to visualize**.

Do you want me to do that?
