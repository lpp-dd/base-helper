package org.example.base.helper.alg;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author panfudong
 * @description
 */
public class BinaryTreeTest {

    public static void main(String[] args) {
    }

    /**
     * 使用递归的方式计算二叉树的深度
     * @param bTree
     * @return
     */
    public static int getTreeDepth(BinaryTree bTree) {
        //如果是null，那就返回0
        if (bTree == null) {
            return 0;
        }
        //获取左子树的最大长度
        int leftDepth = getTreeDepth(bTree.getLeft());
        //获取右子树的最大长度
        int rightDepth = getTreeDepth(bTree.getRight());
        //返回两个长度最大值同时加1
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 首先我们通过数学公式能够得到如下结论
     * 当使用数组存储二叉树的时候 令索引=i 那么以索引位置为头节点的 左子树的索引位置为2i+1 右子树的索引位置为2i+2
     * 然后 当我们声明二叉树的层高为n  当存储的二叉树为满二叉树时，每层存储的节点个数为 2^(n-1)  ，根据等比数列求和公式:数组的长度为 2^n-1
     * 通过数组存储二叉树节点的公式规则，需要保证所有null节点都会占位，模拟出来一个满二叉树  否则将会导致索引错乱。
     * @param arr
     */
    public static BinaryTree arrToBTree(Integer[] arr, int index) {
        if (index > arr.length - 1 || arr[index] == null) {
            return null;
        }
        //声明一个二叉树根节点
        BinaryTree head = new BinaryTree();
        head.value = arr[index];
        head.left = arrToBTree(arr, 2 * index + 1);
        head.right = arrToBTree(arr, 2 * index + 2);
        return head;
    }

    @Getter
    @Setter
    public static class BinaryTree {
        private BinaryTree left;
        private BinaryTree right;
        private int value;
    }

    /**
     * 这里面 利用队列的FIFO思路 将二叉树每层的节点进队列打印，出队列获取左右子树进队列 这样的方式进行
     * @param result
     * @param bTree
     */
    public void bfs(List<BinaryTree> result, BinaryTree bTree) {
        if (bTree == null) {
            return;
        }
        Queue<BinaryTree> queue = new ArrayDeque<>();
        queue.add(bTree);
        while (!queue.isEmpty()) {
            BinaryTree first = queue.poll();
            result.add(first);
            if (first.left != null) {
                queue.add(first.left);
            }
            if (first.right != null) {
                queue.add(first.right);
            }
        }

    }


    public void dfs(List<BinaryTree> result, BinaryTree bTree) {
        if (bTree == null) {
            return;
        }
        result.add(bTree);
        dfs(result, bTree.getLeft());
        dfs(result, bTree.getRight());
    }

    /**
     * 给定一个二叉树，设计一个算法来打印出该二叉树的最右侧节点。最右侧节点是指在每一层中，最靠右的节点。
     * 这里面有一个点是：之前按照层遍历的时候，我们是节点维度遍历，现在是改成了按照层维度遍历
     * 按照层维度遍历，我们每次遍历的时候，队列的长度就是当前层节点的个数
     * @param bTree
     * @return
     */
    public static List<BinaryTree> right(BinaryTree bTree) {
        if (bTree == null) {
            return null;
        }
        List<BinaryTree> result = new ArrayList<>();
        Queue<BinaryTree> queue = new ArrayDeque<>();
        queue.add(bTree);
        while (!queue.isEmpty()) {
            int currentLevelItemSize = queue.size();
            for (int i = 0; i < currentLevelItemSize; i++) {
                BinaryTree poll = queue.poll();
                if (i == currentLevelItemSize - 1) {
                    result.add(poll);
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return result;
    }

}
