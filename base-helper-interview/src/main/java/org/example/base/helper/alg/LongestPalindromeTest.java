package org.example.base.helper.alg;

/**
 * @author panfudong
 * @description 最长回文子串  todo 有问题
 * https://leetcode.cn/problems/longest-palindromic-substring/submissions/497759019/
 */
public class LongestPalindromeTest {

    public static void main(String[] args) {
        longestPalindrome("aaaa");
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        boolean[][] dp = new boolean[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i][i] = true;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] != arr[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1] [j - 1];
                    }
                }

            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (dp[i][j] && Math.abs(j - i) > Math.abs(end - start)) {
                    end = Math.max(j, i);
                    start = Math.min(i, j);
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
