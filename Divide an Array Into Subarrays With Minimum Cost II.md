Perfect 🔥
Ye **hard-level sliding window + data structure** solution hai.
Main isko **Hinglish**, **intuition-first**, step-by-step samjha raha hoon — jaise tum **live contest stream** me explain karoge.

---

## 🧠 Problem ka Core Idea (High-level)

Hume:

* `nums[0]` **fix cost** hai (har answer me add hogi)
* Baaki array me se:

  * Har window me **k−1 sabse chhote elements ka sum**
  * Window ka size = `dist + 1`
* Goal: **minimum total cost** find karna

👉 Final answer:

```
nums[0] + (window ke andar k−1 smallest ka sum)
```

---

## 🔥 Intuition (MOST IMPORTANT)

Har sliding window ke andar:

* Hume **k−1 smallest elements** maintain karne hain
* Aur unka **sum fast** me nikalna hai

Simple sort har window pe ❌ (too slow)

👉 Isliye:

* **2 TreeMaps** use karte hain:

  * `small` → k−1 smallest elements
  * `big` → baaki bade elements

Ye approach **Median / K smallest sliding window** type ka hai.

---

## 🔹 Step 1: Fixed values

```java
long base = nums[0];
int kSmall = k - 1;
if (kSmall <= 0) return base;
```

### Meaning:

* `nums[0]` hamesha include hota hai
* Agar `k = 1`, to sirf `nums[0]` hi answer hai

---

## 🔹 Step 2: Data structures

```java
TreeMap<Integer, Integer> small = new TreeMap<>();
TreeMap<Integer, Integer> big = new TreeMap<>();
```

### Kya store karte hain?

* `small` → **k−1 smallest elements**
* `big` → baaki elements
* TreeMap → sorted + duplicates handle

---

```java
long[] sumSmall = new long[]{0};
int[] cntSmall = new int[]{0};
```

### Kyun array?

* Java lambda ke andar variable change karne ke liye
* `sumSmall` → small ka total sum
* `cntSmall` → small me kitne elements hain

---

## 🔹 Step 3: Rebalance function (🔥 HEART OF CODE)

```java
Runnable rebalance = () -> { ... };
```

### Goal:

`small` me **exactly k−1 elements** hone chahiye

---

### Case 1: `small` me zyada elements

```java
while (cntSmall[0] > kSmall) {
    int x = small.lastKey(); // largest in small
```

* Sabse **bada element** small se nikaal ke
* `big` me daal dete hain

👉 Kyun?

> small me sirf k−1 **smallest** chahiye

---

### Case 2: `small` me kam elements

```java
while (cntSmall[0] < kSmall && !big.isEmpty()) {
    int x = big.firstKey(); // smallest in big
```

* `big` ka sabse **chhota** element
* `small` me shift kar dete hain

---

## 🔹 Step 4: First window build

```java
int windowSize = dist + 1;
for (int i = 1; i <= windowSize && i < nums.length; i++) {
```

### Meaning:

* Window starts from index `1`
* Size = `dist + 1`
* Initially sabko `small` me daal dete hain

---

```java
rebalance.run();
```

* Ab `small` me sirf k−1 smallest bachenge

---

```java
long res = base + sumSmall[0];
```

* Pehla answer store kar liya

---

## 🔹 Step 5: Sliding Window

```java
for (int l = 1, r = windowSize + 1; r < nums.length; l++, r++) {
```

### Window slide hoti hai:

* `out` → left se nikalta hai
* `in` → right se enter karta hai

---

### Remove element

```java
int out = nums[l];
```

* Agar `out` small me hai → sum update
* Warna big se remove

---

### Add element

```java
int in = nums[r];
```

* Agar `in` small ke largest se chhota hai → small
* Warna big me

---

### Rebalance again

```java
rebalance.run();
```

* Ensure:

```
small = k−1 smallest elements
```

---

### Update answer

```java
res = Math.min(res, base + sumSmall[0]);
```

---

## 🔥 Final Return

```java
return res;
```

---

## ⏱ Time & Space Complexity

* **Time:** `O(n log(dist))`
* **Space:** `O(dist)`

Perfect for large constraints 🚀

---

## 🎯 ONE-LINE INTERVIEW / LIVE EXPLANATION

> “Hum sliding window ke andar k−1 smallest elements ko maintain kar rahe hain using two TreeMaps, aur har step pe rebalance karke minimum cost nikal rahe hain.”

---

## 🔥 Tumhare Live Stream ke liye GOLD Tips

* Is problem ko bolte hue bolo:

  * “ye median-type problem hai”
  * “two balanced data structures ka use”
* Log **rebalance logic** ko dekh ke impressed ho jaate hain 😄

---

Agar chaho, next main:

* Isko **diagram ke saath** explain kar du
* **Dry run** with example
* Isko **30-sec Shorts script** me convert kar du
* Same logic **C++ me** likh du

Bas bolo 👊🔥
