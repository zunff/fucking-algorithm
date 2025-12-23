# 每日固定流程（建议 90–120 分钟）
A. 新题 1 题（45–70 min） 
1. 先独立想 20 min：写出 dp 定义/转移/初始化/遍历顺序（哪怕不完整也要写） 
2. 20 min 内没成型：只看“思路/状态定义”，不看代码 
3. 写出 AC 代码 + 口述 5 句话总结（dp 含义、转移、初始化、顺序、复杂度）

B. 重写昨天题（15–25 min）
1. 不看任何资料，计时重写到 AC 
2. 写完对照你昨天的总结：是否状态定义更简洁？边界是否更稳？

C. 重写上周同一天题（15–25 min）
1. 同样闭卷计时 
2. 如果 25 min 仍写不出：允许看你自己的“dp 定义一句话”，仍不看代码

D. 产出（5 min）
1. 给每题打标签：序列二维/背包0-1/完全/网格/区间/状态机/前缀计数 
2. 记录用时：新题用时、两次重写用时 
3. 节奏的关键是：新题不要贪多，把“重写”当成主训练。
4. 边做边总结这类题的规律和模板

# 题目安排原则（保证循序渐进）
1. 前 2 周：把“二维序列DP + 背包”打穿（最常考、最模板化） 
2. 第 3 周：网格DP（边界/空间压缩） 
3. 第 4 周：股票状态机（模板化强） 
4. 第 5 周：区间DP（中等里最容易卡的） 
5. 第 6 周：综合周（混合复习 + 2–3 道压轴）
已经做过：300/1143/72/1035/718/152/918
这些会自然进入“重写池”，不用再当新题。

# 6 周详细日程（按天给新题）
默认你一周刷 6 天，第 7 天做“轻复盘/补漏”（也可以照常做三件套但不加新题）。

## Week 1：二维序列DP（把 dp[i][j] 写到不出错）
- Day1：718 Maximum Length of Repeated Subarray
- Day2：1035 Max Uncrossed Lines
- Day3：516 Longest Palindromic Subsequence 
- Day4：97 Interleaving String 
- Day5：115 Distinct Subsequences 
- Day6：583 Delete Operation for Two Strings
- Day7：不加新题；把本周最卡的 2 题各重写一次（可分两段）
- 目标：看到“两字符串/两序列”能 5 分钟写出 dp 定义与转移框架。

## Week 2：背包（0/1 + 完全 + 计数）
- Day8：416 Partition Equal Subset Sum（0/1 可行性）
- Day9：494 Target Sum（转化为子集和）
- Day10：1049 Last Stone Weight II（0/1 最接近容量）
- Day11：474 Ones and Zeroes（二维费用 0/1）
- Day12：322 Coin Change（完全背包：最少）
- Day13：518 Coin Change II（完全背包：方案数）
- Day14：不加新题；背包三类各挑 1 题闭卷重写（416/322/518） 
- 目标：你能不靠背答案说清楚：为什么 0/1 倒序、完全正序；计数 dp[0]=1 的含义。

## Week 3：网格DP + 边界/空间优化
- Day15：64 Minimum Path Sum
- Day16：120 Triangle（滚动数组）
- Day17：221 Maximal Square
- Day18：931 Minimum Falling Path Sum
- Day19：62 Unique Paths（偏简单但练边界与滚动；如果你觉得太简单可换 63）
- Day20：174 Dungeon Game（本周毕业题）
- Day21：不加新题；重写 174 + 221（这两题最值）
- 目标：二维 dp 初始化不再靠试错；能把 2D 压到 1D。

## Week 4：状态机DP（股票模板）
- Day22：309 Stock with Cooldown
- Day23：714 Stock with Transaction Fee
- Day24：123 Stock III（最多两次）
- Day25：188 Stock IV（最多 k 次，偏难但模板化强）
- Day26：198 House Robber（热身/对比“选或不选”状态机）
- Day27：337 House Robber III（树形DP入门）
- Day28：不加新题；重写 188（或 123）+ 337 
- 目标：你能用统一的 hold/cash 或 dp[k][0/1] 写出股票全家桶。

## Week 5：区间DP（按长度枚举 + 枚举分割点/最后一步）
- Day29：486 Predict the Winner（博弈区间）
- Day30：312 Burst Balloons（区间DP代表作）
- Day31：1039 Minimum Score Triangulation of Polygon（同构巩固）
- Day32：1312 Minimum Insertion Steps to Make a String Palindrome
- Day33：1547 Minimum Cost to Cut a Stick（区间DP进阶）
- Day34：132 Palindrome Partitioning II（回文预处理 + DP，偏综合）
- Day35：不加新题；重写 312 + 132（区间最常卡）
- 目标：你能自然写出 for len、for l、r=l+len-1、for k 的三层结构。

## Week 6：综合周（混合抽题 + 面试计时）
这一周不再“按模块”，而是模拟面试：每天新题从不同模块抽一题，训练切换能力。

- Day36：279 Perfect Squares（完全背包复习）
- Day37：1567 Max Length of Subarray With Positive Product（152 同源）
- Day38：1155 Number of Dice Rolls With Target Sum（计数DP/分组）
- Day39：124 Binary Tree Maximum Path Sum（树形DP进阶）
- Day40：10 Regular Expression Matching（DP压轴，强烈建议做）
- Day41：44 Wildcard Matching（和 10 对照）
- Day42：不加新题；做一次“2 题连做”模拟：从你最弱模块挑 2 题，各 35 分钟
- 目标：中等题 20–30 分钟能稳定写出；偏难题能在 45–60 分钟写出主干。