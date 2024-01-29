package org.example.base.helper.alg;

import java.util.List;

/**
 * @author panfudong
 * @description
 */
public class LinkedListTest {

    public static void main(String[] args) {
        ListNode head = new ListNode();
        head.val = 3;
        ListNode node1 = new ListNode();
        node1.val = 5;
        head.next = node1;
        //reverse(head);
        ListNode newHead = reverseBetween(head, 1, 2);
        System.out.println(newHead);
    }

    /**
     * 双指针解决链表反转
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //声明一个变量代表你的新儿子
        ListNode prev = null;
        // A -> B -> C
        ListNode current = head;
        while (current != null) {
            //B -> C  首先获取你目前的儿子
            ListNode next = current.next;
            //A -> null  将你的新儿子给你
            current.next = prev;
            //将 A->null作为 prev节点  你和你的新儿子将打包作为你旧儿子的新儿子
            prev = current;
            //B -> C重新遍历
            current = next;
        }
        return prev;
    }

    public static ListNode rec(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        //递归找儿子
        ListNode newHead = rec(node.next);
        //儿子要当爹
        node.next.next = node;
        node.next = null;
        return newHead;
    }


    public static class ListNode {
        private ListNode next;
        private int val;
    }

    /**
     * https://leetcode.cn/problems/reverse-linked-list-ii/description/
     * 反转部分链表
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) {
            return head;
        }
        ListNode start = null;
        ListNode end = null;
        ListNode cur = head;
        ListNode prev = null;
        ListNode sourceLeft = null;
        ListNode sourceRight = null;
        for (int i = 1; i <= right; i++) {
            if (i == left) {
                sourceLeft = cur;
            }
            if (i == right) {
                end = cur.next;
                sourceRight = cur;
            }
            if (i >= left) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            } else {
                start = cur;
                cur = cur.next;
            }
        }
        if (sourceLeft != null) {
            sourceLeft.next = end;
        }
        if (start != null) {
            start.next = sourceRight;
            return head;
        }
        return prev;
    }
}
