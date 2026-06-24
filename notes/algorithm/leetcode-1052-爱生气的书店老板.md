# LeetCode 1052. 爱生气的书店老板

📅 2026年6月22日 | 🏷️ 滑动窗口 · 定长窗口

---

## 1. 题意拆解

> 书店老板 `customers[i]` 表示每分钟进店人数，`grumpy[i]` 表示这一分钟是否生气（1=生气，0=不生气）。
> 老板有 `minutes` 分钟可以**强制不生气**的神技，求最多能让多少顾客满意。

**大白话**：生气的分钟会赶走客人，你可以选连续 `minutes` 分钟让他强制不生气，求最大满意人数。

**边界坑点**：`minutes` 可能比数组还长（虽然题目没给，但面试官可能问）。

---

## 2. 思路递进

### 暴力（❌）
枚举每个窗口起点，计算满意人数 → O(n × minutes)，最坏 O(n²)

### 滑动窗口（✅）
1. 先算**老板不生气时**的顾客总数（`grumpy[i] == 0` 的那些分钟）
2. 再用**定长滑动窗口**，找生气分钟中能额外挽回的最大顾客数
3. `base + maxExtra` 就是答案

**时间复杂度**：O(n)，空间 O(1)

---

## 3. 完整代码

```java
public class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int base = 0; // 本来就满意的顾客数

        // 第一步：算基数——老板不生气时顾客都满意
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                base += customers[i];
            }
        }

        // 第二步：定长窗口滑一遍，找最多能额外挽回的
        int extra = 0;      // 当前窗口内挽回的顾客
        int maxExtra = 0;   // 历史最大挽回

        for (int i = 0; i < n; i++) {
            // 入窗：如果这分钟老板在生气，这些人本来会流失，现在能挽回
            if (grumpy[i] == 1) {
                extra += customers[i];
            }

            // 出窗：窗口长度超过 minutes，左边元素移出
            if (i >= minutes) {
                int left = i - minutes;
                if (grumpy[left] == 1) {
                    extra -= customers[left];  // ⚠️ 易错：左边出的也得是生气分钟才减
                }
            }

            // 窗口形成后（i >= minutes-1），更新最大值
            if (i >= minutes - 1) {
                maxExtra = Math.max(maxExtra, extra);
            }
        }

        return base + maxExtra;
    }

    // 测试
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.maxSatisfied(
            new int[]{1, 0, 1, 2, 1, 1, 7, 5},
            new int[]{0, 1, 0, 1, 0, 1, 0, 1},
            3
        )); // 期望 16
    }
}
```

---

## 4. 逐行拆解 + 易错点

```java
if (grumpy[i] == 1) { extra += customers[i]; }  // 入：挽回
if (grumpy[left] == 1) { extra -= customers[left]; } // 出：收回
```

⚠️ **易错**：出窗口时，只有 `grumpy[left] == 1` 才需要减！因为 `grumpy == 0` 的顾客从未被加到 `extra` 里。

⚠️ **窗口形成时机**：`i >= minutes - 1` 才开始更新 `maxExtra`，前面窗口还没满。

---

## 5. 测试用例

| 类型 | customers | grumpy | minutes | 期望 | 说明 |
|------|-----------|--------|---------|------|------|
| 常规 | [1,0,1,2,1,1,7,5] | [0,1,0,1,0,1,0,1] | 3 | 16 | 题目示例 |
| 边界 | [5] | [1] | 1 | 5 | 只有一个元素 |
| 极端 | [10,10,10] | [1,1,1] | 3 | 30 | 全部生气，窗口覆盖全部 |

---

## 6. 考点总结

| 考点 | 说明 |
|------|------|
| 定长滑动窗口模板 | 入窗 → 出窗（i >= k时） → 更新答案（i >= k-1时） |
| 预处理 | 先算 base，窗口只关心额外变量 |
| 同类题识别 | 看到"连续k个" + "最大/最小" → 定长滑窗 |

### 定长滑动窗口通用模板

```java
for (int i = 0; i < n; i++) {
    // 1. 入窗：处理 i 位置元素
    // 2. 出窗：if (i >= k) 处理 i - k 位置元素
    // 3. 更新答案：if (i >= k - 1) 更新结果
}
```
