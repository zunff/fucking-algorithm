# LeetCode 刷题笔记

> 按算法分类整理，每种算法包含：概述、通用模板、核心要点、已刷题目。

---

## 一、双指针 / 滑动窗口（Two Pointers / Sliding Window）

> 适用前提：处理的是连续区间（子数组/子串/区间），并且约束/目标能在指针移动时增量维护。

### 1. 什么时候用双指针

1. **连续性问题**：题目问"某个子数组/子串/区间"的最短、最长、个数。
2. **窗口内信息可维护**：用计数表/哈希表/和/不同元素数等，能在 r++ 或 l++ 时 O(1) 更新。
3. **存在单调性/可收缩性**（常见）
   - "当窗口满足条件时，可以尝试缩小左边界，并且不会漏解"
   - 典型：最小覆盖、最多K种、最多K个不同、无重复、和/乘积不超过K（通常要求非负/正数保证单调）

### 2. 两类经典模板

**模板一：满足条件就收缩（求最短/最优）**

```java
int l = 0;
for (int r = 0; r < n; r++) {
    // 把 s[r] 加入窗口，更新窗口状态
    while (窗口满足条件) {
        // 更新答案（通常取更短）
        // 把 s[l] 移出窗口，更新窗口状态
        l++;
    }
}
```

**模板二：不满足就收缩（求最长/最多）**

```java
int l = 0;
for (int r = 0; r < n; r++) {
    // 把 s[r] 加入窗口，更新窗口状态
    while (窗口不满足条件) {
        // 把 s[l] 移出窗口，更新窗口状态
        l++;
    }
    // 更新答案（通常取更长）
}
```

### 3. 窗口状态设计

- 计数类：`cnt[ch]` / `map[ch]`（出现次数、是否超标）
- 种类数：`distinct`（窗口中不同元素个数）
- 满足度：`formed` / `required`、或"当前是否超额"
- 聚合量：窗口和 `sum`、乘积 `prod`（注意单调性条件）

### 4. 已刷题目

| # | 题目 | 难度 | 核心技巧 |
|---|------|------|----------|
| 3 | 无重复字符的最长子串 | 中等 | 模板二，HashMap 计数 |
| 76 | 最小覆盖子串 | 困难 | 模板一，need/window 双计数 |
| 209 | 长度最小的子数组 | 中等 | 模板一，窗口和维护 |

---

## 二、二分查找 / 二分答案

> 适用场景：答案有明确范围且连续，给定候选值能快速判断是否可行。常见问法为「最大值最小化」或「最小值最大化」。

### 1. 通用模板

```java
public int solve(int[] nums, int k) {
    // 1. 初始化左右边界
    long l = 最小可能值;
    long r = 最大可能值;

    // 2. 二分查找主循环
    long ans = 0;
    while (l <= r) {
        long mid = l + (r - l) / 2;
        if (can(mid, nums, k)) {
            ans = mid;       // 记录可行解
            r = mid - 1;     // 尝试找更优
        } else {
            l = mid + 1;     // 需要更大值
        }
    }
    return (int) ans;
}

// 3. 可行性判断（唯一需要根据题目改写的部分）
private boolean can(long threshold, int[] nums, int k) {
    int count = 1;
    long curSum = 0;
    for (int num : nums) {
        if (curSum + num > threshold) {
            count++;
            curSum = num;
        } else {
            curSum += num;
        }
    }
    return count <= k;
}
```

### 2. 核心要点

- **防溢出**：`mid = l + (r - l) / 2`，大数用 `long`，最后转 `int`
- **答案记录**：在满足条件时立即更新 `ans`，保证不丢解
- **循环条件**：`l <= r` 配合 `ans` 变量，比 `l < r` 更安全
- **边界初始化**：下界取单元素最大值 `max(arr)`，上界取总和 `sum(arr)`

### 3. 已刷题目

| # | 题目 | 难度 | 核心技巧 |
|---|------|------|----------|
| 69 | x 的平方根 | 简单 | 平方根区间二分 |
| 875 | 爱吃香蕉的珂珂 | 中等 | 速度最小值二分 + 可行性判断 |
| 1011 | 在 D 天内送达包裹的能力 | 中等 | 运载能力最小值二分 |
| 410 | 分割数组的最大值 | 困难 | 分割最小最大和二分 |

---

## 三、并查集（Union-Find / DSU）

> 解决的问题：动态连通性，在不断"加边/合并集合"的过程中，快速回答：两点是否在同一集合？

> 典型适用：无向图连通性、无向图判环、最小生成树（Kruskal）、连通块数量、账号合并。

### 1. 通用模板

```java
static class DSU {
    private int[] parent;
    private int[] size;
    public int count; // 连通块数量

    public DSU(int n) {
        parent = new int[n];
        size = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // 路径压缩
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 按size合并
    public void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) return;
        if (size[ra] < size[rb]) {
            parent[ra] = rb;
            size[rb] += size[ra];
        } else {
            parent[rb] = ra;
            size[ra] += size[rb];
        }
        count--;
    }

    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }
}
```

### 2. 核心要点

- **路径压缩**：`find` 时递归把沿途节点直接挂到根上
- **按size合并**：小树挂大树，避免退化成链
- **均摊复杂度**：O(α(n))，实际可视为常数
- **2D网格映射**：`index = i * cols + j`，将2D坐标映射到1D
- **判环技巧**：union 返回 false 表示已在同一集合 = 形成环
- **不适合**：有向图判环（用 Kahn/DFS 三色）、需要删除边/回滚的场景

### 3. 已刷题目

| # | 题目 | 难度 | 核心技巧 |
|---|------|------|----------|
| 547 | 省份数量 | 中等 | 连通块计数 |
| 721 | 账户合并 | 中等 | 邮箱映射 + 等价关系归类 |
| 200 | 岛屿数量 | 中等 | 2D网格映射1D + 连通块计数 |
| 684 | 冗余连接 | 中等 | union判环 |
| 128 | 最长连续序列 | 中等 | HashMap映射 + 连续数字连接 |

---

## 四、动态规划（Dynamic Programming）

> 核心思想：将大问题拆成子问题，通过状态定义和状态转移方程递推求解。

### 通用解题步骤

1. **状态定义**：明确 `dp[i]` 或 `dp[i][j]` 代表什么
2. **状态转移方程**：从已知子问题推导当前状态
3. **初始化**：确定 base case
4. **遍历顺序**：根据依赖关系决定（依赖 `i-1` 则 `i++`，依赖 `i+1` 则 `i--`）
5. **返回值**：根据题目要求返回 `dp[n]` 或 `max(dp[])`

### dp 数组的定义技巧

- 不要习惯性 `dp = new int[n+1][m+1]`，要看状态转移方程
- 如果依赖 `i-1` 和 `j-1`：用 `new int[n+1][m+1]`
- 如果依赖 `i+1` 和 `j-1`：推荐 `new int[n+2][m+2]`

### 两字符串/数组的公共子序列 vs 公共子数组

**公共子序列（不要求连续）**

```text
dp[i][j] = nums1前i个元素 和 nums2前j个元素 的最大公共子序列长度
当 nums1[i] == nums2[j]：dp[i][j] = dp[i-1][j-1] + 1
当 nums1[i] != nums2[j]：dp[i][j] = max(dp[i-1][j], dp[i][j-1])
答案：dp[n][m]
```

**公共子数组（要求连续）**

```text
dp[i][j] = nums1以元素i结尾 和 nums2以元素j结尾 的最长公共子数组长度
当 nums1[i] == nums2[j]：dp[i][j] = dp[i-1][j-1] + 1
当 nums1[i] != nums2[j]：dp[i][j] = 0
答案：max(dp[i][j])
```

---

### A. 网格 DP

> 在二维网格上求路径数/最值，状态从上方/左方转移。

```java
// 模板：最小路径和
public int minPathSum(int[][] grid) {
    int n = grid.length, m = grid[0].length;
    int[] dp = new int[m];
    dp[0] = grid[0][0];
    for (int j = 1; j < m; j++) dp[j] = dp[j - 1] + grid[0][j];
    for (int i = 1; i < n; i++) {
        dp[0] += grid[i][0];
        for (int j = 1; j < m; j++) {
            dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
        }
    }
    return dp[m - 1];
}
```

| # | 题目 | 核心技巧 |
|---|------|----------|
| 62 | 不同路径 | dp[i][j] = dp[i-1][j] + dp[i][j-1] |
| 63 | 不同路径 II | 有障碍时 dp[i][j] = 0 |
| 64 | 最小路径和 | dp[i][j] = min(上,左) + grid[i][j] |
| 120 | 三角形最小路径和 | 自底向上递推 |
| 221 | 最大正方形 | dp[i][j] = min(左,上,左上) + 1 |
| 931 | 下降路径最小和 | 从上方三个方向取最小 |
| 174 | 地下城游戏 | 从右下往左上递推（依赖 i+1, j+1） |

---

### B. 背包 DP

> 从一组物品中选择若干个，使其满足约束的最优解。01背包内层倒序，完全背包内层正序。

```java
// 01背包：能否凑出目标和
public boolean canPartition(int[] nums) {
    int sum = 0;
    for (int num : nums) sum += num;
    if (sum % 2 != 0) return false;
    int target = sum / 2;
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;
    for (int num : nums) {
        for (int j = target; j >= num; j--) { // 倒序
            dp[j] = dp[j] || dp[j - num];
        }
    }
    return dp[target];
}

// 完全背包：最少硬币数
public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;
    for (int coin : coins) {
        for (int j = coin; j <= amount; j++) { // 正序
            dp[j] = Math.min(dp[j], dp[j - coin] + 1);
        }
    }
    return dp[amount] > amount ? -1 : dp[amount];
}
```

| # | 题目 | 核心技巧 |
|---|------|----------|
| 416 | 分割等和子集 | 01背包判定 |
| 322 | 零钱兑换 | 完全背包最值 |
| 518 | 零钱兑换 II | 完全背包组合数 |
| 494 | 目标和 | 01背包组合数（正负分组） |
| 474 | 一和零 | 二维01背包 |
| 1049 | 最后一块石头的重量 II | 01背包（转化为分割等和） |

---

### C. 二维序列 DP

> 两个序列的配对/匹配问题，`dp[i][j]` 表示两序列前缀的关系。

```java
// 模板：最长公共子序列
public int longestCommonSubsequence(String text1, String text2) {
    int n = text1.length(), m = text2.length();
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
    return dp[n][m];
}
```

| # | 题目 | 核心技巧 |
|---|------|----------|
| 583 | 两个字符串的删除操作 | 删除代价 = len(s) + len(t) - 2*LCS |
| 115 | 不同的子序列 | dp[i][j] = s选/不选 |
| 718 | 最长重复子数组 | 公共子数组（连续），不等时 dp=0 |
| 516 | 最长回文子序列 | 区间[i,j]内 LPS |
| 1035 | 不相交的线 | 等价于 LCS |
| 97 | 交错字符串 | dp[i][j] = 两种来源取或 |

---

### D. 状态 DP

> 用若干离散状态描述系统，典型如股票买卖（hold/sold/wait）、打家劫舍（rob/skip）。

```java
// 股票买卖通用模板（含冷冻期）
public int maxProfit(int[] prices) {
    int hold = -prices[0], sold = 0, wait = 0;
    for (int i = 1; i < prices.length; i++) {
        int lastHold = hold, lastSold = sold;
        hold = Math.max(lastHold, wait - prices[i]);
        sold = lastHold + prices[i];
        wait = Math.max(lastSold, wait);
    }
    return Math.max(sold, wait);
}

// 打家劫舍
public int rob(int[] nums) {
    int rob = nums[0], skip = 0;
    for (int i = 1; i < nums.length; i++) {
        int lastRob = rob;
        rob = skip + nums[i];
        skip = Math.max(skip, lastRob);
    }
    return Math.max(rob, skip);
}
```

| # | 题目 | 核心技巧 |
|---|------|----------|
| 198 | 打家劫舍 | rob/skip 两状态 |
| 337 | 打家劫舍 III | 树形DP，rob/notRob 两个状态 |
| 123 | 买卖股票 III | 至多2次交易，4个状态 |
| 188 | 买卖股票 IV | 至多k次交易，2k个状态 |
| 309 | 买卖股票含冷冻期 | hold/sold/wait 三状态 |
| 714 | 买卖股票含手续费 | 卖出时扣手续费 |

---

### E. 区间 DP

> 在区间 `[i,j]` 上求最优解，枚举分割点 k：`dp[i][j] = opt(dp[i][k] + dp[k+1][j] + cost)`。先枚举区间长度，再枚举左端点。

```java
// 模板：戳气球
public int maxCoins(int[] nums) {
    int n = nums.length;
    int[] val = new int[n + 2];
    val[0] = val[n + 1] = 1;
    for (int i = 1; i <= n; i++) val[i] = nums[i - 1];

    int[][] dp = new int[n + 2][n + 2];
    for (int len = 1; len <= n; len++) {
        for (int i = 1; i + len - 1 <= n; i++) {
            int j = i + len - 1;
            for (int k = i; k <= j; k++) {
                dp[i][j] = Math.max(dp[i][j],
                    dp[i][k - 1] + val[i - 1] * val[k] * val[j + 1] + dp[k + 1][j]);
            }
        }
    }
    return dp[1][n];
}
```

| # | 题目 | 核心技巧 |
|---|------|----------|
| 312 | 戳气球 | 枚举最后戳破的气球，左右两端加哨兵1 |
| 1547 | 切棍子的最小成本 | 枚举切割点 |
| 1039 | 多边形三角剖分最低分 | 枚举三角形顶点 |
| 1312 | 让字符串成为回文的最少插入 | dp[i][j] = 两端相同/不同 |
| 132 | 分割回文串 II | 预处理回文 + dp最值 |
| 486 | 预测赢家 | dp[i][j] = 先手最大差值 |

---

### F. 综合题目

| # | 题目 | 核心技巧 |
|---|------|----------|
| 124 | 二叉树中的最大路径和 | 树形DP |
| 1567 | 乘积为正数的最长子数组 | 正负分开 dp |
| 1155 | 掷骰子的N种方法 | 分组背包组合数 |
| 279 | 完全平方数 | 完全背包最值 |
| 10 | 正则表达式匹配 | 二维DP + `.` 和 `*` 处理 |
| 44 | 通配符匹配 | 二维DP + `?` 和 `*` 处理 |

---

## 五、BFS 和 DFS

> BFS 逐层扩展，适合最短路径/层序遍历；DFS 沿一条路走到底再回溯，适合连通分量/判环。

### 1. 网格 BFS 模板（连通块计数）

```java
int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
Queue<int[]> queue = new ArrayDeque<>();

// 发现起点，入队并标记已访问
queue.offer(new int[]{i, j});
grid[i][j] = '0';

while (!queue.isEmpty()) {
    int[] cur = queue.poll();
    for (int[] d : dirs) {
        int x = cur[0] + d[0], y = cur[1] + d[1];
        if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == '1') {
            grid[x][y] = '0';
            queue.offer(new int[]{x, y});
        }
    }
}
```

### 2. 多层 BFS 模板（求层数/最短时间）

```java
// 多起点初始化：所有腐烂橘子入队
for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
        if (grid[i][j] == 2) queue.offer(new int[]{i, j});
        else if (grid[i][j] == 1) fresh++;
    }
}

while (!queue.isEmpty()) {
    int size = queue.size();
    boolean success = false;
    for (int k = 0; k < size; k++) { // 每次处理一整层
        int[] cur = queue.poll();
        for (int[] d : dirs) {
            // 扩散逻辑...
            success = true;
        }
    }
    if (success) ans++; // 有新扩散才计时
}
```

### 3. 网格 DFS 模板（递归）

```java
public void dfs(char[][] grid, int n, int m, int i, int j) {
    if (i < 0 || j < 0 || i == n || j == m || grid[i][j] == '0') return;
    grid[i][j] = '0'; // 标记已访问
    dfs(grid, n, m, i - 1, j);
    dfs(grid, n, m, i + 1, j);
    dfs(grid, n, m, i, j - 1);
    dfs(grid, n, m, i, j + 1);
}
```

### 4. Kahn 拓扑排序模板（判环）

```java
// 邻接表 + 入度
List<List<Integer>> adj = new ArrayList<>();
int[] inDegree = new int[n];
for (int[] p : prerequisites) { adj.get(p[1]).add(p[0]); inDegree[p[0]]++; }

// 入度为0的入队
Queue<Integer> queue = new ArrayDeque<>();
for (int i = 0; i < n; i++) if (inDegree[i] == 0) queue.offer(i);

int taken = 0;
while (!queue.isEmpty()) {
    int cur = queue.poll();
    taken++;
    for (int next : adj.get(cur)) {
        if (--inDegree[next] == 0) queue.offer(next);
    }
}
return taken == n; // 等于n说明无环
```

### 5. DFS 三色判环模板

```java
// status: 0=未访问, 1=访问中(在递归栈中), 2=已完成
public boolean hasCycle(List<List<Integer>> adj, int[] status, int cur) {
    status[cur] = 1;
    for (int next : adj.get(cur)) {
        if (status[next] == 1) return true;  // 遇到访问中的节点 = 有环
        if (status[next] == 0 && hasCycle(adj, status, next)) return true;
    }
    status[cur] = 2;
    return false;
}
```

### 6. 核心要点

- **BFS 用队列，DFS 用递归/栈**
- **网格方向数组**：`{{0,1},{0,-1},{1,0},{-1,0}}`
- **多层 BFS**：用 `size` 控制每层边界，`success` 标志避免空层计数
- **访问标记**：入队时立即标记，不要等出队时再标记（否则会重复入队）
- **拓扑排序**：Kahn 适合 BFS 判环，DFS 三色适合递归判环

### 7. 已刷题目

| # | 题目 | 难度 | 核心技巧 |
|---|------|------|----------|
| 200 | 岛屿数量 | 中等 | BFS/DFS 连通块计数 |
| 994 | 腐烂的橘子 | 中等 | 多层BFS求最短时间 |
| 207 | 课程表 | 中等 | Kahn拓扑排序 + DFS三色判环 |

---

## 六、回溯（Backtracking）

> 核心思想：通过「选择 - 递归 - 撤销」系统地搜索所有可能的解，不满足条件时回退（剪枝）。

### 1. 通用模板

```java
List<List<Integer>> result = new ArrayList<>();
List<Integer> cur = new ArrayList<>();

public List<List<Integer>> solve(int[] nums) {
    backTracking(nums, 0);
    return result;
}

private void backTracking(int[] nums, int begin) {
    // 收集结果（子集模式：每次都收；排列模式：满足终止条件才收）
    result.add(new ArrayList<>(cur));

    // 终止条件（排列模式）
    if (cur.size() == nums.length) {
        result.add(new ArrayList<>(cur));
        return;
    }

    for (int i = begin; i < nums.length; i++) {
        // 剪枝（如需要）
        if (剪枝条件) continue;

        // 做选择
        cur.add(nums[i]);

        // 递归
        backTracking(nums, i + 1);  // 子集：i+1 避免重复
        // backTracking(nums, begin); // 排列：从0开始 + used数组

        // 撤销选择
        cur.remove(cur.size() - 1);
    }
}
```

### 2. 核心要点

- **三要素**：选择（当前可选什么）、约束（剪枝条件）、目标（终止条件）
- **子集/组合模式**：用 `begin` 参数控制起点，避免重复
- **排列模式**：用 `boolean[] used` 标记已选元素，每次从 0 遍历
- **棋盘模式**：用多个布尔数组标记列/对角线（如 N 皇后：`col[]`, `diag1[]`, `diag2[]`）
- **保存副本**：`result.add(new ArrayList<>(cur))`，不能直接存引用
- **对角线技巧**：左斜 `d1 = col - row + n - 1`，右斜 `d2 = col + row`

### 3. 已刷题目

| # | 题目 | 难度 | 核心技巧 |
|---|------|------|----------|
| 78 | 子集 | 中等 | begin参数避免重复，每个节点都收集 |
| 46 | 全排列 | 中等 | used数组标记已选元素 |
| 51 | N皇后 | 困难 | 三数组剪枝（列 + 两对角线） |

---

## 七、单调栈 & 单调队列

> 核心思想：维护一个单调的栈/队列，在 O(n) 时间内解决「下一个更大/更小元素」和「滑动窗口最值」问题。

### 1. 单调栈模板（求下一个更大元素 — 单调递减栈）

```java
public int[] nextGreaterElement(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];
    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
        // 当前元素破坏单调性时，弹出并计算
        while (!stack.isEmpty() && nums[i] >= nums[stack.peek()]) {
            Integer index = stack.pop();
            ans[index] = i - index; // 或 nums[i]
        }
        stack.push(i);
    }
    // 栈中剩余元素无更大元素，ans保持默认值0/-1
    return ans;
}
```

### 2. 单调递增栈模板（求最大矩形面积）

```java
public int largestRectangleArea(int[] heights) {
    int ans = 0;
    Deque<Integer> stack = new ArrayDeque<>();
    int n = heights.length;

    for (int i = 0; i <= n; i++) {
        int h = (i == n) ? 0 : heights[i];
        while (!stack.isEmpty() && h <= heights[stack.peek()]) {
            int height = heights[stack.pop()];
            int left = stack.isEmpty() ? -1 : stack.peek();
            int width = i - left - 1;
            ans = Math.max(ans, height * width);
        }
        stack.push(i);
    }
    return ans;
}
```

### 3. 单调队列模板（滑动窗口最大值 — 单调递减队列）

```java
public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    int[] ans = new int[n - k + 1];
    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
        // 1. 移除超出窗口的索引
        while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
            deque.removeFirst();
        }
        // 2. 维护单调递减
        while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
            deque.removeLast();
        }
        deque.addLast(i);
        // 3. 窗口形成后取队头
        if (i >= k - 1) {
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
    }
    return ans;
}
```

### 4. 核心要点

- **栈/队列存下标**，不存值，方便计算距离/宽度/判断越界
- **单调性选择**：求更大元素 → 递减栈；求更小元素 → 递增栈；求窗口最大 → 递减队列
- **弹出的时机**：当前元素破坏单调性时弹出，弹出时立即计算答案
- **边界处理**：栈空时左边界为 -1，数组末尾用哨兵清空栈
- **时间复杂度**：每个元素最多进出一次，O(n)

### 5. 已刷题目

| # | 题目 | 难度 | 核心技巧 |
|---|------|------|----------|
| 739 | 每日温度 | 中等 | 单调递减栈，栈顶弹出时计算天数差 |
| 84 | 柱状图中最大矩形 | 困难 | 单调递增栈，弹出时计算宽度 |
| 239 | 滑动窗口最大值 | 困难 | 单调递减队列，三步操作 |

---

## 八、堆（Heap / Priority Queue）

> 核心思想：利用堆的堆顶特性（最值），高效解决 Top K、多路归并、动态中位数等问题。Java 中用 `PriorityQueue` 实现。

### 1. Top K 模板（小根堆求第 K 大）

```java
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>(); // 默认小根堆
    for (int num : nums) {
        if (heap.size() < k) {
            heap.offer(num);
        } else if (heap.peek() < num) {
            heap.poll();
            heap.offer(num);
        }
    }
    return heap.peek();
}
```

### 2. 多路归并模板（合并 K 个有序链表）

```java
public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> heap = new PriorityQueue<>(
        Comparator.comparingInt(node -> node.val)
    );
    for (ListNode list : lists) {
        if (list != null) heap.offer(list);
    }
    ListNode dummy = new ListNode();
    ListNode cur = dummy;
    while (!heap.isEmpty()) {
        ListNode node = heap.poll();
        cur.next = node;
        cur = cur.next;
        if (node.next != null) heap.offer(node.next);
    }
    return dummy.next;
}
```

### 3. 双堆中位数模板

```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a); // 左半边（大根堆）
PriorityQueue<Integer> minHeap = new PriorityQueue<>();                // 右半边（小根堆）

public void addNum(int num) {
    maxHeap.add(num);
    minHeap.add(maxHeap.poll());      // 保证右半边 >= 左半边
    if (minHeap.size() > maxHeap.size()) {
        maxHeap.add(minHeap.poll());   // 维持左半边数量 >= 右半边
    }
}

public double findMedian() {
    if (maxHeap.size() > minHeap.size()) return maxHeap.peek();
    return (maxHeap.peek() + minHeap.peek()) / 2.0;
}
```

### 4. 核心要点

- **堆的选择**：求第 K 大用小根堆（堆顶是最小值，不够大的自动淘汰）
- **双堆平衡**：大根堆存较小一半，小根堆存较大一半，保持 `maxHeap.size() >= minHeap.size()`
- **PriorityQueue 默认小根堆**，大根堆需传入 `(a, b) -> b - a`
- **时间复杂度**：堆操作 O(log n)，Top K 问题 O(n log k)

### 5. 已刷题目

| # | 题目 | 难度 | 核心技巧 |
|---|------|------|----------|
| 215 | 数组中的第K个最大元素 | 中等 | 小根堆维护 Top K |
| 23 | 合并K个升序链表 | 困难 | 小根堆多路归并 |
| 295 | 数据流的中位数 | 困难 | 大根堆 + 小根堆双堆平衡 |

---

## 九、前缀和（Prefix Sum）

> 核心思想：用 `prefixSum[j] - prefixSum[i] = k` 快速求区间和，配合哈希表将 O(n²) 优化到 O(n)。

### 1. 通用模板

```java
public int subarraySum(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1); // 前缀和为0出现1次

    int sum = 0, ans = 0;
    for (int num : nums) {
        sum += num;
        // 查找是否存在前缀和等于 sum - k
        if (map.containsKey(sum - k)) {
            ans += map.get(sum - k);
        }
        map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    return ans;
}
```

### 2. 核心要点

- **初始化**：`map.put(0, 1)` 处理从起点开始的子数组
- **时间复杂度**：O(n)，空间 O(n)
- **适用场景**：和为特定值的子数组个数、区间和查询

### 3. 已刷题目

| # | 题目 | 难度 | 核心技巧 |
|---|------|------|----------|
| 560 | 和为 K 的子数组 | 中等 | 前缀和 + HashMap 计数 |

---

## 十、贪心（Greedy）

> 核心思想：每步选择当前最优，期望通过局部最优达到全局最优。关键在于证明贪心策略的正确性。

### 1. 两遍遍历贪心模板（分发糖果类问题）

```java
public int candy(int[] ratings) {
    int n = ratings.length;
    int[] candies = new int[n];

    // 第一遍：从左到右，保证右边评分高的孩子糖果更多
    for (int i = 1; i < n; i++) {
        if (ratings[i] > ratings[i - 1]) {
            candies[i] = candies[i - 1] + 1;
        } else {
            candies[i] = 1;
        }
    }

    // 第二遍：从右到左，保证左边评分高的孩子糖果更多
    for (int i = n - 2; i >= 0; i--) {
        if (ratings[i] > ratings[i + 1]) {
            candies[i] = Math.max(candies[i], candies[i + 1] + 1);
        }
    }

    int sum = 0;
    for (int candy : candies) sum += candy;
    return sum;
}
```

### 2. 核心要点

- **两遍遍历**：分别保证两个方向的约束，第二遍取 `max` 确保同时满足
- **贪心证明**：关键是找到正确的贪心策略并证明其正确性

### 3. 已刷题目

| # | 题目 | 难度 | 核心技巧 |
|---|------|------|----------|
| 135 | 分发糖果 | 困难 | 两遍遍历贪心，左右方向分别处理 |

---

## 十一、排序

### 已刷题目

| 题目 | 核心技巧 |
|------|----------|
| 冒泡排序 | 基础排序，O(n²)，稳定 |

---

## 十二、华为笔试

| 编号 | 题目 | 核心技巧 |
|------|------|----------|
| HW161 | 依赖检查 | Kahn 拓扑排序检测有向图环 |
| HW162 | 网格路径最小转向 | DP + 方向状态设计（down/right 两数组） |
