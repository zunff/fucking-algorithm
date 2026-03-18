package 双指针;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串 困难
 *
 * 给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
 * 测试用例保证答案唯一。
 *
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 *
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 *
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 * 提示：
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s 和 t 由英文字母组成
 *
 * 进阶：你能设计一个在 O(m + n) 时间内解决此问题的算法吗？
 */
public class MinimumWindowSubstring76 {
    public static void main(String[] args) {
        System.out.println(new MinimumWindowSubstring76().minWindowThird("ADOBECODEBANC", "ABC"));

    }


    public String minWindowFourth(String s, String t) {
        return null;
    }
























































    public String minWindowThird(String s, String t) {
        int n = s.length();
        int m = t.length();
        Map<Character, Integer> needs = new HashMap<>();
        for (int i = 0; i < m; i++) {
            char c = t.charAt(i);
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        int[] windows = new int[128];
        int formed = 0;
        int required = needs.size();

        int bestL = 0;
        int bestLen = Integer.MAX_VALUE;

        for (int i = 0, j = 0; j < n; j++) {
            char rc = s.charAt(j);
            windows[rc]++;
            if (needs.containsKey(rc) && windows[rc] == needs.get(rc)) {
                formed++;
            }

            while (formed == required) {
                int len = j - i + 1;
                if (len < bestLen) {
                    bestL = i;
                    bestLen = len;
                }
                char lc = s.charAt(i);
                if (needs.containsKey(lc) && --windows[lc] < needs.get(lc)) {
                    formed--;
                }
                i++;
            }
        }

        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestL, bestL + bestLen);
    }


























































    public String minWindowSecond(String s, String t) {
        int formed = 0;
        int require = 0;
        int[] needs = new int[128];
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (needs[c] == 0) {
                require++;
            }
            needs[c]++;
        }
        int[] windows = new int[128];

        int bestL = 0;
        int bestLen = Integer.MAX_VALUE;

        for (int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            windows[c]++;
            if (windows[c] == needs[c]) {
                formed++;
            }
            while (formed == require) {
                int len = j - i + 1;
                if (bestLen > len) {
                    bestL = i;
                    bestLen = len;
                }
                char lc = s.charAt(i);
                if (--windows[lc] < needs[lc]) {
                    formed--;
                }
                i++;
            }
        }

        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestL, bestL + bestLen);
    }


























































    public String minWindow(String s, String t) {
        Map<Character, Integer> needs = new HashMap<>();
        int m = t.length();
        for (int i = 0; i < m; i++) {
            char c = t.charAt(i);
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> windows = new HashMap<>();

        int required = needs.size();   // 需要满足的“字符种类数”
        int formed = 0;                // 当前窗口中已满足的“字符种类数”

        int bestL = 0;
        int bestLen = Integer.MAX_VALUE;

        for (int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            windows.put(c, windows.getOrDefault(c, 0) + 1);
            if (windows.get(c).equals(needs.get(c))) {
                formed++;
            }
            while (formed == required) {
                int len = j - i + 1;
                if (len < bestLen) {
                    bestL = i;
                    bestLen = Math.min(bestLen, j - i + 1);
                }
                char lc = s.charAt(i);
                if (needs.containsKey(lc) && windows.get(lc) - 1 < needs.get(lc)) {
                    formed--;
                }
                windows.put(lc, windows.get(lc) - 1);
                i++;
            }
        }
        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestL, bestL + bestLen);
    }
}
