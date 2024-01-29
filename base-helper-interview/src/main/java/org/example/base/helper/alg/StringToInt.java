package org.example.base.helper.alg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author panfudong
 * @description
 * https://leetcode.cn/problems/string-to-integer-atoi/description/
 */
public class StringToInt {

    public static void main(String[] args) {
        myAtoi("words and 987");
    }

    public static int myAtoi(String s) {
        Set<Character> numSet = new HashSet<>();
        numSet.add('-');
        numSet.add('+');
        numSet.add('0');
        numSet.add('1');
        numSet.add('2');
        numSet.add('3');
        numSet.add('4');
        numSet.add('5');
        numSet.add('6');
        numSet.add('7');
        numSet.add('8');
        numSet.add('9');

        List<Character> fuhao = new ArrayList<>();
        fuhao.add('-');
        fuhao.add('+');
        char[] charArr = s.toCharArray();
        List<Character> filterResult = new ArrayList<>();
        for (char c : charArr) {
            if (numSet.contains(c)) {
                if (c == ' ') {
                    continue;
                }
                if (c == '0' && filterResult.size() == 0) {
                    continue;
                }
                if (c == '0' && filterResult.size() == 1 && fuhao.contains(c)) {
                    continue;
                }
                if (filterResult.size() > 0 && fuhao.contains(c)) {
                    continue;
                }
                filterResult.add(c);
            }
        }
        if (fuhao.contains(filterResult.get(0))) {

        }
        boolean zhengshu = true;
        if ('-' == filterResult.get(0)) {
            zhengshu = false;
        }
        int start = 0;
        if (fuhao.contains(filterResult.get(0))) {
            start = 1;
        }
        int result = 0;
        for (int i = filterResult.size() - 1; i >= start; i--) {
            double ratio = Math.pow(10, (filterResult.size() - 1 - i));
            if (ratio > 0) {
                result += ratio * (filterResult.get(i) - '0');
            } else {
                result += (filterResult.get(i) - '0');
            }
        }
        return zhengshu ? result : -result;
    }
}
