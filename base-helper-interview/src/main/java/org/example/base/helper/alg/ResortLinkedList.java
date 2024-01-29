package org.example.base.helper.alg;

import java.util.LinkedList;
import java.util.List;

/**
 * @author panfudong
 * @description 重排链表
 * https://leetcode.cn/problems/reorder-list/
 */
public class ResortLinkedList {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        reorderList(node);
        System.out.println(node);
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        List<ListNode> list = new LinkedList<>();
        list.add(head);
        while (head.next != null) {
            list.add(head.next);
            head = head.next;
        }
        ListNode newHead = new ListNode();
        int i = 0;
        int j = list.size() - 1;
        while (i <= j) {
            if (i == j) {
                newHead.next = list.get(i);
                newHead = newHead.next;
            } else {
                if (i == 0) {
                    newHead = list.get(i);
                    newHead.next = list.get(j);
                    newHead = newHead.next;
                } else {
                    newHead.next = list.get(i);
                    newHead.next.next = list.get(j);
                    newHead = newHead.next.next;
                }
            }
            i++;
            j--;
        }
        newHead.next = null;
        head = list.get(0);
    }

}
