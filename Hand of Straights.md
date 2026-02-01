Chalo 😄
**LeetCode 846 – Hand of Straights** ko **complete depth** me samajhte hain
👉 **Brute → Better → Optimal**
👉 **Hinglish explanation + Java code + intuition + edge cases**

---

## 🧠 Problem Samajh Lo (Simple)

Tumhe ek array `hand` diya hai (cards ke numbers)
Aur ek integer `groupSize`

👉 Check karna hai:
**kya poora array ko groups me divide kiya ja sakta hai**
jahan:

* Har group ka size = `groupSize`
* Har group **consecutive numbers** ka ho
  (jaise: 3,4,5 ya 7,8,9)

---

### Example 1:

```
hand = [1,2,3,6,2,3,4,7,8]
groupSize = 3
```

Possible groups:

```
[1,2,3] [2,3,4] [6,7,8]
```

👉 ✅ TRUE

---

### Example 2:

```
hand = [1,2,3,4,5]
groupSize = 4
```

👉 ❌ FALSE (5 cards ko 4-4 ke groups me nahi baant sakte)

---

## ⚠️ Important Observation

👉 Agar

```
hand.length % groupSize != 0
```

toh **direct FALSE**
(kyunki equal groups possible hi nahi)

---

# 🟥 Brute Force Approach (Sorting + Remove)

## 💡 Idea:

* Array sort karo
* Har baar smallest element se group banane ki koshish
* Consecutive elements remove karte jao

❌ Inefficient (remove operation costly)

---

### ⏱ Complexity:

* Time: **O(N²)**
* Space: O(N)

👉 Interview ke liye **recommended nahi**

---

# 🟡 Better Approach (TreeMap / Frequency Map)

🔥 **Most popular & clean solution**

---

## 💡 Core Idea:

* Har card ki **frequency count**
* Hamesha **smallest available card** se group start karo
* Uske next `groupSize-1` consecutive cards chahiye

---

## 🧠 Steps:

1. Frequency map banao (sorted order chahiye → `TreeMap`)
2. Jab tak map empty na ho:

   * smallest key uthao
   * `start, start+1, ..., start+groupSize-1`
   * agar koi missing → ❌ false
   * warna frequency reduce karo

---

### 🧪 Dry Run:

```
hand = [1,2,2,3,3,4]
groupSize = 3

Map:
1:1, 2:2, 3:2, 4:1

Start = 1
Check: 1,2,3 ✔
Update:
2:1, 3:1

Next Start = 2
Check: 2,3,4 ✔
Map empty ✔
```

---

### ✅ Java Code (TreeMap)

```java
import java.util.*;

class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        TreeMap<Integer, Integer> map = new TreeMap<>();

        // Frequency count
        for (int card : hand) {
            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        // Process groups
        while (!map.isEmpty()) {
            int start = map.firstKey();

            for (int i = 0; i < groupSize; i++) {
                int curr = start + i;
                if (!map.containsKey(curr)) {
                    return false;
                }

                map.put(curr, map.get(curr) - 1);
                if (map.get(curr) == 0) {
                    map.remove(curr);
                }
            }
        }
        return true;
    }
}
```

---

### ⏱ Complexity:

* **Time:** O(N log N)
* **Space:** O(N)

✅ Interview safe
✅ Easy to explain

---

# 🟢 Optimal Approach (HashMap + Sorting)

Same logic, bas thoda optimized & common sense use

---

## 💡 Idea:

* Array sort karo
* Frequency HashMap banao
* Har card se group start karo **sirf tab** jab uski frequency > 0

---

### ✅ Java Code

```java
import java.util.*;

class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        Arrays.sort(hand);
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int card : hand) {
            freq.put(card, freq.getOrDefault(card, 0) + 1);
        }

        for (int card : hand) {
            if (freq.get(card) == 0) continue;

            for (int i = 0; i < groupSize; i++) {
                int curr = card + i;
                if (freq.getOrDefault(curr, 0) == 0) {
                    return false;
                }
                freq.put(curr, freq.get(curr) - 1);
            }
        }
        return true;
    }
}
```

---

### ⏱ Complexity:

* **Time:** O(N log N)
* **Space:** O(N)

---

# 📊 Comparison Table

| Approach       | Time       | Space | Verdict |
| -------------- | ---------- | ----- | ------- |
| Brute          | O(N²)      | O(N)  | ❌       |
| TreeMap        | O(N log N) | O(N)  | ⭐ Best  |
| Sort + HashMap | O(N log N) | O(N)  | ✅ Good  |

---

## 🎯 Interview Tip

👉 Always start with this line:

> “First, if hand size is not divisible by groupSize, return false.”

Fir TreeMap approach explain karo — interviewer happy 😄

---

Agar chaho:

* 🧠 **Visual explanation**
* 🔍 **Edge cases**
* 🎤 **Interview answer script**
* 🧪 **More examples**

Bas bol do 👍
