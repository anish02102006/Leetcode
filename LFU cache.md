Nice 😄
**LFU Cache** is a **top-tier interview + teaching problem** (Google, Amazon, Microsoft).
I’ll explain it **teacher-style**, **Hinglish**, with **intuition → brute → better → optimal**, and **Java code**.

---

# 🔷 What is LFU Cache? (Simple Hinglish)

LFU = **Least Frequently Used**

Tumhe ek cache banana hai jisme:

* `get(key)` → value return karo
* `put(key, value)` → insert/update karo

### Rules:

1. **Least frequently used key remove hogi**
2. Agar frequency same ho → **Least Recently Used (LRU)** remove hogi
3. Sab operations **O(1)** hone chahiye

---

## Example

```
capacity = 2

put(1,1)
put(2,2)
get(1)      → freq(1)=2
put(3,3)    → remove key 2 (freq=1)
```

---

# 🟥 Brute Force Approach (Not Allowed)

## 🧠 Intuition

* Har key ka frequency track karo
* Jab capacity full ho → sab keys scan karo
* Min frequency wali delete karo

⏱️ Time:

```
get → O(1)
put → O(n) ❌
```

❌ Interview reject

---

# 🟡 Better Approach (Priority Queue)

## 🧠 Idea

* Min-Heap based on `(frequency, time)`
* Eviction easy

⏱️ Time:

```
get / put → O(log n)
```

❌ Still not allowed (needs O(1))

---

# 🟢 Optimal Approach (O(1) — Real LFU)

## 🔥 Core Data Structures (VERY IMPORTANT)

We use **3 maps**:

### 1️⃣ `keyToNode`

```text
key → Node(value, freq)
```

### 2️⃣ `freqToDLL`

```text
frequency → Doubly Linked List of keys
```

### 3️⃣ `minFreq`

```text
Track minimum frequency in cache
```

---

## 🧠 Intuition (Teacher Explanation)

* Har key ka frequency hota hai
* Same frequency wale keys ek **DLL** me rahte hain (LRU order)
* `minFreq` batata hai kaunsi frequency sabse chhoti hai
* Eviction → `minFreq` wali list ka **least recent node**

---

# 🛠️ Operations Explained

---

## 🔹 GET(key)

1. Agar key nahi → return -1
2. Frequency increase karo
3. Node ko old freq list se nikaal ke new freq list me daalo
4. `minFreq` update

⏱️ O(1)

---

## 🔹 PUT(key, value)

### Case 1: Key already exists

* Update value
* Treat as `get()` (freq++)

### Case 2: New key

* Agar cache full:

  * `minFreq` wali list ka **LRU node delete**
* New key insert with freq = 1
* `minFreq = 1`

⏱️ O(1)

---

# ✅ Java Code (Interview Perfect)

```java
import java.util.*;

class LFUCache {

    class Node {
        int key, value, freq;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
            freq = 1;
        }
    }

    class DLL {
        Node head, tail;
        int size;

        DLL() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        void add(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast() {
            if (size > 0) {
                Node last = tail.prev;
                remove(last);
                return last;
            }
            return null;
        }
    }

    int capacity, minFreq;
    Map<Integer, Node> keyToNode;
    Map<Integer, DLL> freqToDLL;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyToNode = new HashMap<>();
        freqToDLL = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToNode.containsKey(key)) return -1;

        Node node = keyToNode.get(key);
        updateFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.value = value;
            updateFreq(node);
            return;
        }

        if (keyToNode.size() == capacity) {
            DLL minList = freqToDLL.get(minFreq);
            Node removed = minList.removeLast();
            keyToNode.remove(removed.key);
        }

        Node newNode = new Node(key, value);
        minFreq = 1;

        freqToDLL.computeIfAbsent(1, k -> new DLL()).add(newNode);
        keyToNode.put(key, newNode);
    }

    private void updateFreq(Node node) {
        int freq = node.freq;
        DLL oldList = freqToDLL.get(freq);
        oldList.remove(node);

        if (freq == minFreq && oldList.size == 0) {
            minFreq++;
        }

        node.freq++;
        freqToDLL.computeIfAbsent(node.freq, k -> new DLL()).add(node);
    }
}
```

---

# 🧑‍🏫 Whiteboard Teaching Flow (Highly Recommended)

### Step 1:

Explain LFU vs LRU difference

### Step 2:

Draw:

* key → node
* freq → list

### Step 3:

Explain `minFreq`

### Step 4:

Simulate:

```
put(1)
put(2)
get(1)
put(3)
```

---

# 🧠 Interview One-Line Explanation

> LFU Cache is implemented using a HashMap for keys, another HashMap mapping frequency to doubly linked lists, and a minFreq variable to ensure O(1) get and put operations.

---

# 🔚 Summary Table

| Operation | Time |
| --------- | ---- |
| get       | O(1) |
| put       | O(1) |

---

If you want next:

* ✅ **Dry run animation**
* ✅ **Simplified version for beginners**
* ✅ **LRU vs LFU comparison**
* ✅ **Teaching slides / board notes**

Just say 🔥
