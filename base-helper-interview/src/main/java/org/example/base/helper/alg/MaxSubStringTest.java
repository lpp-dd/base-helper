package org.example.base.helper.alg;

import java.util.HashMap;

/**
 * @author panfudong
 * @description
 */
public class MaxSubStringTest {

    public static void main(String[] args) {
        int maxLength = lengthOfLongestSubstring("abba");
        System.out.println(maxLength);
    }

    /**
     * 无重复字符串的最长子串
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] charArr = s.toCharArray();
        HashMap<Character, Integer> subStr = new HashMap<>();
        int i = 0;
        int j = 1;
        int maxLength = 1;
        subStr.put(charArr[0], 0);
        while (j < charArr.length) {
            if (subStr.containsKey(charArr[j])) {
                i = Math.max(subStr.get(charArr[j]) + 1, i);
            }
            subStr.put(charArr[j], j);
            maxLength = Math.max(maxLength, ++j - i);
        }
        return maxLength;
    }
}
