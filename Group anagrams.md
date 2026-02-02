Chalo bhai 😄
**LeetCode 49 – Group Anagrams** ko **Brute → Better → Optimal** style me **Java + Hinglish** me complete clear kar dete hain 🔥

---

## 🔴 Problem Samajh Lo

Tumhe ek array diya hai **strings[]**
Tumhe **anagrams ko group** karna hai.

### Anagram kya hota hai?

* Same characters
* Same frequency
* Order matter nahi karta

### Example

```
["eat","tea","tan","ate","nat","bat"]
```

Output:

```
[
  ["eat","tea","ate"],
  ["tan","nat"],
  ["bat"]
]
```

---

# 🟠 Approach 1: Brute Force (Pairwise Compare) ❌

### 🧠 Idea

* Har string ko baaki sab se compare karo
* Check karo anagram hai ya nahi
* Groups banao

### ❌ Problem

* Anagram check = O(k)
* Total = O(n² * k)
* TLE 💀

---

### ❌ (Mostly interview me skip hota hai)

---

# 🟡 Approach 2: Sorting Each String (Most Common)

### 🔥 Idea

* Har string ko **sort** kar do
* Sorted string ko **key** bana do
* Same key → same group

---

### Example

```
eat → aet
tea → aet
ate → aet
```

---

### ✅ Java Code (Better)

```java
import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }
}
```

---

### ⏱ Time

* Sorting each string → `O(k log k)`
* Total → `O(n * k log k)`

### 📦 Space: `O(nk)`

---

# 🟢 Approach 3: Optimal (Character Frequency Key) 🔥🔥

### 🧠 Best Idea

* Sorting avoid karo
* Har string ka **26 length frequency array**
* Usko string key me convert karo

---

### Example

```
eat → [1,0,0,...,1,0,...,1]
```

Key ban jaata hai:

```
"1#0#0#...#1#..."
```

---

### ✅ Java Code (Optimal)

```java
import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            int[] freq = new int[26];

            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            StringBuilder key = new StringBuilder();
            for (int f : freq) {
                key.append(f).append('#');
            }

            map.putIfAbsent(key.toString(), new ArrayList<>());
            map.get(key.toString()).add(s);
        }

        return new ArrayList<>(map.values());
    }
}
```

---

### ⏱ Time: `O(n * k)`

### 📦 Space: `O(n * k)`

🔥 **Most Efficient**

---

## 🧠 Comparison Table

| Approach      | Time        | Interview |
| ------------- | ----------- | --------- |
| Brute         | O(n²k)      | ❌         |
| Sorting       | O(nk log k) | ✅         |
| Frequency Map | O(nk)       | 🔥 BEST   |

---

## 🎯 Interview One-Liner

> “Sir, har string ka character frequency array ko key bana ke anagrams group kar sakte hain.”

---

## 🧩 Related Questions

* Valid Anagram
* Find All Anagrams in a String
* Group Shifted Strings

---

Agar chaaho to mai:

* **Dry run**
* **Why # separator important**
* **Edge cases**
* **Python / JS version**

sab karwa dunga 😎
Bas bolo bhai 👊
