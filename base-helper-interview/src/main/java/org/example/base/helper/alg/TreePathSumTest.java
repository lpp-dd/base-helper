package org.example.base.helper.alg;

import org.example.base.helper.alg.support.TreeNode;

import java.util.*;

/**
 * @author panfudong
 * @description
 * 求根节点到叶节点数字之和
 * https://leetcode.cn/problems/3Etpl5/description/
 */
public class TreePathSumTest {

    public static void main(String[] args) {
        TreePathSumTest test = new TreePathSumTest();
        int sum = test.sumNumbers(TreeNode.arrToBTree(new int[]{4,9,0,5,1}, 0));
        System.out.println(sum);
    }

    public int sumNumbers(TreeNode root) {
        /*
        回溯
        List<List<Integer>> result = new ArrayList<>();
        sumNumbersBk(root, new ArrayList<>(), result);
        return result.stream().map(list -> intListToInt(list)).mapToInt(item -> item).sum();*/

        //return sumNumberDfs(root, 0);

        return sumNumberBfs(root, new ArrayDeque<>(), new ArrayDeque<>());
    }


    /**
     * 使用回溯法遍历求解
     * @param node
     * @param resultItem
     * @param result
     */
    public void sumNumbersBk(TreeNode node, List<Integer> resultItem, List<List<Integer>> result) {
        resultItem.add(node.val);
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(resultItem));
            return;
        }
        if (node.left != null) {
            sumNumbersBk(node.left, resultItem, result);
            resultItem.remove(resultItem.size() - 1);
        }
        if (node.right != null) {
            sumNumbersBk(node.right, resultItem, result);
            resultItem.remove(resultItem.size() - 1);
        }
    }

    private int intListToInt(List<Integer> nums) {
        int sum = 0;
        for (int i = nums.size() - 1; i >= 0; i--) {
            sum += nums.get(i) * Math.pow(10, nums.size() - 1 - i);
        }
        return sum;
    }

    /**
     * 深度优先遍历
     * @param node
     * @param prevNum
     * @return
     */
    public int sumNumberDfs(TreeNode node, int prevNum) {
        if (node == null) {
            return 0;
        }
        prevNum = prevNum * 10 + node.val;
        if (node.left == null && node.right == null) {
            return prevNum;
        }
        int leftValue = sumNumberDfs(node.left, prevNum);
        int rightValue = sumNumberDfs(node.right, prevNum);
        return leftValue + rightValue;
    }

    /**
     * 广度优先遍历
     * @param node
     * @param queue
     * @param sumQueue
     * @return
     */
    public int sumNumberBfs(TreeNode node, ArrayDeque<TreeNode> queue, ArrayDeque<Integer> sumQueue) {
        int sum = 0;
        queue.addLast(node);
        sumQueue.addLast(node.val);
        while (!queue.isEmpty()) {
            int levelLength = queue.size();
            for (int i = 0; i < levelLength; i++) {
                TreeNode cur = queue.pollFirst();
                Integer curVal = sumQueue.pollFirst();
                if (cur.left != null) {
                    queue.addLast(cur.left);
                    sumQueue.addLast(curVal * 10 + cur.left.val);
                }
                if (cur.right != null) {
                    queue.addLast(cur.right);
                    sumQueue.addLast(curVal * 10 + cur.right.val);
                }
                if (cur.left == null && cur.right == null) {
                    sum += curVal;
                }
            }
        }
        return sum;
    }
}
