package org.example.base.helper.alg;

/**
 * @author panfudong
 * @description
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/description/
 */
public class DeleteDuplicatesTest {

    public static void main(String[] args) {
        ListNode node = arrToList(new int[]{1,2,3,3,4,4,5});
        deleteDuplicates(node);
        System.out.println(node);
    }

    private static ListNode arrToList(int[] arr) {
        ListNode node = new ListNode(arr[0]);
        ListNode head = node;
        for (int i = 1; i < arr.length; i++) {
            node.next = new ListNode(arr[i]);
            node = node.next;
        }
        return head;
    }

    public static class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dirtyHead = new ListNode(-1, head);
        ListNode result = dirtyHead;
        while (dirtyHead.next != null && dirtyHead.next.next != null) {
            if (dirtyHead.next.val == dirtyHead.next.next.val) {
                int value = dirtyHead.next.val;
                while (dirtyHead.next != null && value == dirtyHead.next.val) {
                    dirtyHead.next = dirtyHead.next.next;
                }
            } else {
                dirtyHead = dirtyHead.next;
            }
        }
        return result.next;
    }
}
