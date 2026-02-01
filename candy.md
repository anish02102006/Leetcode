Alright 😄
**LeetCode – Candy (Problem 135)** ko **A to Z detail** me samjhte hain — **Brute → Better → Optimal**, **Hinglish** + **Java code** + **intuition + dry run**.
Ye question interviews ka **favorite** hai 🔥

---

## 🧠 Problem Samajh Lo (Simple Words)

Tumhare paas `n` children hain, har child ko **at least 1 candy** deni hai.

Rules:

1. Har child ko **minimum 1 candy**
2. Agar kisi child ka **rating neighbor se zyada** hai → usko **zyada candy** milni chahiye

👉 Goal: **minimum total candies** distribute karni hain.

---

### Example:

```
ratings = [1, 0, 2]
```

Possible distribution:

```
candies = [2, 1, 2]
Answer = 5
```

---

# 🟥 Brute Force Approach (Naive / Inefficient)

### 💡 Idea:

* Har child ko pehle 1 candy do
* Baar-baar loop chalao
* Jab tak rules satisfy na ho jaaye tab tak candies badhate raho

### ❌ Problem:

* Repeated iterations
* Worst case me **O(N²)** time
* LeetCode pe TLE aa sakta hai

---

### 🧪 Steps:

1. Sabko 1 candy do
2. Loop chalao:

   * Agar `rating[i] > rating[i-1]` aur `candies[i] <= candies[i-1]`
     → `candies[i]++`
   * Same for right neighbor
3. Jab tak koi change ho raha hai, loop repeat

---

### ❌ Brute Java Code

```java
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        // Step 1: sabko 1 candy
        for (int i = 0; i < n; i++) {
            candies[i] = 1;
        }

        boolean changed = true;

        // Step 2: repeat until stable
        while (changed) {
            changed = false;
            for (int i = 0; i < n; i++) {
                if (i > 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i]++;
                    changed = true;
                }
                if (i < n - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i]++;
                    changed = true;
                }
            }
        }

        int sum = 0;
        for (int c : candies) sum += c;
        return sum;
    }
}
```

⏱ **Time:** O(N²)
📦 **Space:** O(N)

---

# 🟡 Better Approach (Two Pass – Left & Right)

### 💡 Observation:

* Left neighbor rule alag
* Right neighbor rule alag
  👉 Dono ko **separately handle** kar sakte hain

---

## 🔄 Two Pass Strategy

### Pass 1 (Left → Right):

* Agar `ratings[i] > ratings[i-1]`
  → `candies[i] = candies[i-1] + 1`

### Pass 2 (Right → Left):

* Agar `ratings[i] > ratings[i+1]`
  → `candies[i] = max(candies[i], candies[i+1] + 1)`

---

### 🧪 Dry Run:

```
ratings = [1, 0, 2]

Initial candies = [1, 1, 1]

Left pass:
[1, 1, 2]

Right pass:
[2, 1, 2]
```

---

### ✅ Better Java Code

```java
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        // Step 1: sabko 1 candy
        for (int i = 0; i < n; i++) {
            candies[i] = 1;
        }

        // Step 2: left to right
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Step 3: right to left
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int sum = 0;
        for (int c : candies) sum += c;
        return sum;
    }
}
```

⏱ **Time:** O(N)
📦 **Space:** O(N)

---

# 🟢 Optimal Approach (Greedy – Single Pass, O(1) Space)

🔥 **Interview favorite**

---

## 💡 Core Idea:

* Increasing slope → candies badhao
* Decreasing slope → candies adjust karo
* Peak child ko **max** candies milni chahiye

Hum track karte hain:

* `up` → increasing sequence length
* `down` → decreasing sequence length
* `peak` → last increasing peak

---

### 📈 Example:

```
ratings = [1,2,3,2,1]
```

Candies:

```
1  2  3  2  1
```

---

### ✅ Optimal Java Code (Greedy)

```java
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int candies = 1;

        int up = 0, down = 0, peak = 0;

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                up++;
                peak = up;
                down = 0;
                candies += 1 + up;
            }
            else if (ratings[i] == ratings[i - 1]) {
                up = down = peak = 0;
                candies += 1;
            }
            else {
                down++;
                up = 0;
                candies += 1 + down;
                if (down > peak) {
                    candies += 1;
                }
            }
        }
        return candies;
    }
}
```

⏱ **Time:** O(N)
📦 **Space:** O(1)

---

# 📊 Comparison Table

| Approach | Time  | Space | Status     |
| -------- | ----- | ----- | ---------- |
| Brute    | O(N²) | O(N)  | ❌ TLE      |
| Better   | O(N)  | O(N)  | ✅ Accepted |
| Optimal  | O(N)  | O(1)  | ⭐ Best     |

---

## 🎯 Interview Tip

👉 **Pehle Better Approach explain karo**, phir bolo:

> “We can further optimize space using Greedy…”

---

Agar tum chaho:

* 🔥 **Step-by-step dry run**
* 🧠 **Visualization**
* 🎯 **Interview explanation script**
* 🧪 **More test cases**

Bas bolo 😄
