package org.example.base.helper.alg.support;

/**
 * @author panfudong
 * @description
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public static TreeNode arrToBTree(int[] arr, int index) {
        if (index > arr.length - 1) {
            return null;
        }
        //声明一个二叉树根节点
        TreeNode head = new TreeNode();
        head.val = arr[index];
        head.left = arrToBTree(arr, 2 * index + 1);
        head.right = arrToBTree(arr, 2 * index + 2);
        return head;
    }
}
