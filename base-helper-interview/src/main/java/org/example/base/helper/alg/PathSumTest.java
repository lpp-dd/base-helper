package org.example.base.helper.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * @author panfudong
 * @description
 * https://leetcode.cn/problems/path-sum-ii/
 *
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 */
public class PathSumTest {

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(1, left, right);

        pathSum(root, 4);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 路径之和 回溯
     * @param root
     * @param targetSum
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            bk(root, targetSum, new ArrayList<>(), result);
        }
        return result;
    }

    private static void bk(TreeNode node, int target, List<Integer> resultItem, List<List<Integer>> result) {
        resultItem.add(node.val);
        target -= node.val;
        if (node.left == null && node.right == null) {
            if (0 == target) {
                result.add(new ArrayList<>(resultItem));
            }
            return;
        }
        if (node.left != null) {
            bk(node.left, target, resultItem, result);
            resultItem.remove(resultItem.size() - 1);
        }
        if (node.right != null) {
            bk(node.right, target, resultItem, result);
            resultItem.remove(resultItem.size() - 1);
        }
    }


    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        bt(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void bt(String s, int start, List<String> resultItem, List<String> result) {
        if (resultItem.size() == 4 && ipLength(resultItem) == s.length()) {
            result.add(String.join(".", resultItem));
            return;
        }
        if (resultItem.size() > 4) {
            return;
        }
        for (int i = start + 1; i <= Math.min(s.length(), start + 3); i++) {
            String ipStr = s.substring(start, i);
            if (ipStr.length() > 1 && ipStr.startsWith("0")) {
                continue;
            }
            int ipSegment;
            try {
                ipSegment = Integer.parseInt(ipStr);
            } catch (Exception e) {
                continue;
            }
            if (ipSegment > 255) {
                continue;
            }
            resultItem.add(s.substring(start, i));
            bt(s, i, resultItem, result);
            resultItem.remove(resultItem.size() - 1);
        }
    }

    private int ipLength(List<String> resultItem) {
        int result = 0;
        for (String s : resultItem) {
            result += s.length();
        }
        return result;
    }
}
