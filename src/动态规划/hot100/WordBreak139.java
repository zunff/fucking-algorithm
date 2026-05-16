package 动态规划.hot100;

import java.util.*;

/**
 * 139. 单词拆分 中等
 *
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * 提示：
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅由小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 */
public class WordBreak139 {
    public static void main(String[] args) {
        String s = "dogs";
        List<String> wordDict = Arrays.asList("dog","s","gs");
        System.out.println(new WordBreak139().wordBreak_official(s, wordDict));
    }

    /**
     * dp[i] 表示前 i 个字符能不能由字典中的单词组成
     * 初始化：dp[0] = true
     * {
     *     dp[i] = dp[i] || dp[j] && workDict.contains(s[j...i - 1])     0 =< j < i
     * }
     */
    public boolean wordBreak_official(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String substring = s.substring(j, i);
                dp[i] = dp[i] || dp[j] && wordDictSet.contains(substring);
            }
        }
        return dp[n];
    }



    /**
     * dp[i] 表示 s 中前 i + 1 个字符能不能由字典中的单词组成
     * ps 可以优化为 1 ... base 然后dp[0] = true，然后就不用这样特殊初始化了
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        Map<Character, List<String>> endCharToWordDict = new HashMap<>();
        boolean conatainsHead = false;
        for (String word : wordDict) {
            char endChar = word.charAt(word.length() - 1);
            List<String> list = endCharToWordDict.getOrDefault(endChar, new ArrayList<>());
            list.add(word);
            endCharToWordDict.put(endChar, list);
            // 判断是否存在与s开头一致的word
            if (s.startsWith(word)) {
                conatainsHead = true;
                dp[word.length() - 1] = true;
            }
        }
        if (!conatainsHead) {
            return false;
        }
        for (int i = 1; i < n; i++) {
            if (dp[i]) {
                continue;
            }
            char c = s.charAt(i);
            List<String> list = endCharToWordDict.getOrDefault(c, new ArrayList<>());
            for (String word : list) {
                if (word.length() > i + 1) {
                    continue;
                }
                if (!s.substring(0, i + 1).endsWith(word)) {
                    continue;
                }
                dp[i] = dp[i - word.length()] || dp[i];
            }
        }
        return dp[n - 1];
    }
}
