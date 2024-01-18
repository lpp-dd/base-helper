package org.example.base.helper.alg;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author panfudong
 * @description
 * 回溯算法解决问题时，主要分为如下几步
 * 1、声明入参  原始入参集合，递归中间变量(可以是索引，也可以是元素)，resultItemList，resultList
 * 2、确定递归终止条件 resultItemList中元素长度满足或者其他的一些规则
 * 3、遍历入参集合，判断条件，增加元素，递归，回溯
 */
public class Backtracking {

    /**
     * 题目
     *
     * 给定一个字符串，代表一个IPv4地址，例如："192.168.0.1"。请编写一个函数，将该字符串分割成所有有效的IPv4地址组合，并返回一个包含这些地址的列表。
     *
     * 输入："192.168.0.1"
     * 输出：["192.168.0.1"]
     *
     * 输入："25525511135"
     * 输出：["255.255.11.135", "255.255.111.35"]
     */
    public static void main(String[] args) {
        /*List<String> left = new ArrayList<>();
        left.add("(");
        left.add("(");
        left.add("(");

        List<String> right = new ArrayList<>();
        right.add(")");
        right.add(")");
        right.add(")");

        List<String> result = new ArrayList<>();
        bt(3, left, right, 0, 0, new ArrayList<>(), result);
        System.out.println(result);

        List<String> queenResult = new ArrayList<>();
        btQueen(4, 0, new ArrayList<>(), queenResult);
        System.out.println(queenResult);

        List<Integer> numberList = new ArrayList<>();
        numberList.add(1);
        numberList.add(2);
        numberList.add(3);
        List<String> sumResult = new ArrayList<>();
        btSum(numberList, 3, new ArrayList<>(), sumResult);
        System.out.println(sumResult);


        List<String> itemList = new ArrayList<>();
        itemList.add("a");
        itemList.add("b");
        itemList.add("c");
        List<String> fpResult = new ArrayList<>();
        btFP(itemList, new ArrayList<>(), fpResult);
        System.out.println(fpResult);*/


        List<Integer> sourceList = new ArrayList<>();
        sourceList.add(3);
        sourceList.add(4);
        sourceList.add(1);
        sourceList.add(5);
        sourceList.add(6);
        sourceList.add(7);
        sourceList.add(8);
        sourceList.add(9);
        List<String> resultMaxSubString = new ArrayList<>();
        btMaxSubString(sourceList, 0, new ArrayList<>(), resultMaxSubString);
        System.out.println(resultMaxSubString);

    }

    public static List<String> restoreIp(String ipAddress) {
        if (StringUtils.isBlank(ipAddress) || ipAddress.length() < 4 || ipAddress.length() > 12) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        List<String> ipSegmentList = new ArrayList<>();
        backtracking(ipAddress, 0, ipSegmentList, result);
        return result;
    }

    public static void backtracking(String ipAddress, int start, List<String> ipSegmentList, List<String> result) {
        if (ipSegmentList.size() == 4 && start == ipAddress.length()) {
            result.add(String.join(".", ipSegmentList));
            return;
        }
        if (ipSegmentList.size() > 4) {
            return;
        }
        for (int end = start + 1; end <= Math.min(start + 3, ipAddress.length()); end++) {
            String ipSegment = ipAddress.substring(start, end);
            int ipSegmentNumber = Integer.parseInt(ipSegment);
            if (ipSegmentNumber <= 255) {
                ipSegmentList.add(ipSegment);
                backtracking(ipAddress, end, ipSegmentList, result);
                ipSegmentList.remove(ipSegmentList.size() - 1);
            }
        }
    }

    /**
     * 当给定 n 对括号时，任务是生成所有合法的括号组合。合法的括号组合是这样的，每个左括号 "(" 必须有相应的右括号 ")"，且不得存在嵌套的情况。例如，对于 n = 3，合法的括号组合可以是：
     * [  "((()))",  "(()())",  "(())()",  "()(())",  "()()()"]
     * @param n
     * @param left
     * @param right
     * @param leftIndex
     * @param rightIndex
     * @param resultItem
     * @param result
     */
    public static void bt(int n, List<String> left, List<String> right, int leftIndex, int rightIndex, List<String> resultItem, List<String> result) {
        if (resultItem.size() == 2*n && leftIndex == n && rightIndex == n) {
            result.add(String.join( "", resultItem));
            return;
        }
        if (leftIndex < n) {
            resultItem.add(left.get(leftIndex));
            bt(n, left, right, leftIndex + 1, rightIndex, resultItem, result);
            resultItem.remove(resultItem.size() - 1);
        }
        if (rightIndex < leftIndex) {
            resultItem.add(right.get(rightIndex));
            bt(n, left, right, leftIndex, rightIndex + 1, resultItem, result);
            resultItem.remove(resultItem.size() - 1);
        }
    }

    /**
     * 在一个 N x N 的棋盘上放置 N 个皇后，使得它们不能互相攻击，即任意两个皇后不能在同一行、同一列或同一对角线上。
     * 给定一个整数 N，返回所有不同的 N 皇后问题的解决方案。
     * @param n
     * @param i
     * @param queenList
     * @param result
     */
    public static void btQueen(int n, int i, List<Integer> queenList, List<String> result) {
        if (queenList.size() == n) {
            result.add(queenList.stream().map(String::valueOf).collect(Collectors.joining("-")));
            return;
        }
        while (i < n) {
            if (queenList.size() == 0 || canAdd(queenList, i)) {
                queenList.add(i);
                btQueen(n, 0, queenList, result);
                queenList.remove(queenList.size() - 1);
            }
            i++;
        }
    }

    private static boolean canAdd(List<Integer> queenList, int currentI) {
        for (int i = 0; i < queenList.size(); i++) {
            int rowInterval = Math.abs(queenList.size() - i);
            int colInterval = Math.abs(queenList.get(i) - currentI);
            if (rowInterval == colInterval || colInterval == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 组合求和
     * @param numberList
     * @param target
     * @param resultItem
     * @param result
     */
    public static void btSum(List<Integer> numberList, Integer target, List<Integer> resultItem, List<String> result) {
        int sum = resultItem.stream().mapToInt(i -> i).sum();
        if (sum == target) {
            result.add(resultItem.stream().map(String::valueOf).collect(Collectors.joining("-")));
            return;
        }
        if (sum > target) {
            return;
        }
        for (Integer number : numberList) {
            resultItem.add(number);
            btSum(numberList, target, resultItem, result);
            resultItem.remove(resultItem.size() - 1);
        }
    }

    /**
     * 全排列
     * @param itemList
     * @param resultItem
     * @param result
     */
    public static void btFP(List<String> itemList, List<String> resultItem, List<String> result) {
        if (resultItem.size() == itemList.size()) {
            result.add(String.join("-", resultItem));
            return;
        }
        for (int i = 0; i < itemList.size(); i++) {
            if (resultItem.contains(itemList.get(i))) {
                continue;
            }
            resultItem.add(itemList.get(i));
            btFP(itemList, resultItem, result);
            resultItem.remove(resultItem.size() - 1);
        }
    }

    /**
     * 最长自增子序列，可以不连续
     */
    public static void btMaxSubString(List<Integer> sourceList, int currentIndex, List<Integer> resultItem, List<String> result) {
        if (currentIndex >= sourceList.size() || (resultItem.size() > 0 && sourceList.get(currentIndex) < resultItem.get(resultItem.size() - 1))) {
            result.add(resultItem.stream().map(Object::toString).collect(Collectors.joining("-")));
            return;
        }
        for (int i = currentIndex; i < sourceList.size() - 1; i++) {
            resultItem.add(sourceList.get(i));
            btMaxSubString(sourceList, i + 1, resultItem, result);
            resultItem.remove(resultItem.size() - 1);
        }
    }

}
