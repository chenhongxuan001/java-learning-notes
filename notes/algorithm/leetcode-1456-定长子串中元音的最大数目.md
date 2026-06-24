# LeetCode 1456. 定长子串中元音的最大数目

📅 2026年6月24日 | 🏷️ 滑动窗口 · 定长窗口 | 灵神题单 ① | 难度分 1263

---

## 1. 题意拆解

> 给字符串 `s` 和整数 `k`，找长度为 `k` 的子串中，哪个元音字母（a/e/i/o/u）最多。

**大白话**：用一把长度为 k 的尺子从字符串左边滑到右边，看尺子盖住的字符里哪次元音最多。

```
s = "abciiidef", k = 3

abc → 1个元音(a)       bci → 1个(i)
cii → 2个(i,i)         iii → 3个(i,i,i)  ← 最多！
iid → 2个(i,i)         ide → 2个(i,e)
def → 1个(e)

答案：3
```

---

## 2. 思路递进

### 暴力（❌）
枚举每个子串，逐个数元音 → O(n × k)

### 滑动窗口（✅）— 灵神模板：入 → 更新 → 出
窗口滑一格，进一个新字符、出一个旧字符，只更新计数就好 → O(n)，空间 O(1)

---

## 3. 完整代码

```java
class Solution {
    public int maxVowels(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int vowelCount = 0;  // 当前窗口内元音个数
        int maxCount = 0;    // 历史最大

        for (int i = 0; i < n; i++) {
            // ===== ① 入：新字符进窗口 =====
            if (isVowel(arr[i])) {
                vowelCount++;
            }

            // ===== ② 更新：窗口形成后更新答案 =====
            if (i >= k - 1) {
                maxCount = Math.max(maxCount, vowelCount);
            }

            // ===== ③ 出：左边字符离开窗口 =====
            if (i >= k - 1) {
                int left = i - k + 1;  // 左边界 = 当前 - 窗口长 + 1
                if (isVowel(arr[left])) {
                    vowelCount--;
                }
            }
        }
        return maxCount;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
```

---

## 4. 逐行拆解 + 易错点

```java
// 入：只有元音才计数
if (isVowel(arr[i])) { vowelCount++; }
```

```java
int left = i - k + 1;
// ⚠️ 易错：窗口左边界公式
// i=2, k=3 → left=0，窗口是 [0,1,2] ✓
// i=5, k=3 → left=3，窗口是 [3,4,5] ✓
```

```java
// 出：只有元音才减！非元音从来没加过，减了就错了
if (isVowel(arr[left])) { vowelCount--; }
```

---

## 5. 手跑测试

`s = "abciiidef"`, `k = 3`

| i | 入 | count | 窗口 | 出 | count' | max |
|---|----|-------|------|----|--------|-----|
| 0 | a | 1 | — | — | 1 | — |
| 1 | b | 1 | — | — | 1 | — |
| 2 | c | 1 | [0-2] abc | a | 0 | 1 |
| 3 | i | 1 | [1-3] bci | b | 1 | 1 |
| 4 | i | 2 | [2-4] cii | c | 2 | 2 |
| 5 | i | 3 | [3-5] iii | i | 2 | 3 |
| 6 | d | 2 | [4-6] iid | i | 1 | 3 |
| 7 | e | 2 | [5-7] ide | i | 1 | 3 |
| 8 | f | 1 | [6-8] def | d | 1 | 3 |

返回 **3** ✅

---

## 6. 考点总结

| 考点 | 说明 |
|------|------|
| 定长滑窗模板 | 入 → 更新 → 出（灵神三步） |
| 左边界计算 | `left = i - k + 1`，别写成 `i - k` |
| 出窗条件 | 只减加过的（元音才减），和 1052/643 同一个套路 |
| 同类题 | 643（改成求和）、1052（加 base 预处理） |

### 定长滑动窗口通用模板

```java
for (int i = 0; i < n; i++) {
    // ① 入窗
    // ② 更新答案（i >= k - 1 时）
    // ③ 出窗（left = i - k + 1）
}
```

---

*灵神题单 §1.1 定长滑动窗口 · 第 1 题*
