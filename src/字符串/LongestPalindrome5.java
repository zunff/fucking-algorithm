package 字符串;

/**
 * 5. 最长回文子串 中等
 *
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 */
public class LongestPalindrome5 {
    public static void main(String[] args) {
        System.out.println(new LongestPalindrome5().longestPalindrome("babad"));
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int len1 = expend(chars, i, i);
            int len2 = expend(chars, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    public int expend(char[] chars, int i, int j) {
        while (i >= 0 && j < chars.length && chars[i] == chars[j]) {
            i--;
            j++;
        }
        return j - i - 1;
    }
}










