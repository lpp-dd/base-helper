package org.example.base.helper.alg;

/**
 * @author panfudong
 * @description
 */
public class LinkedListTest {

    public static void main(String[] args) {
        Node head = new Node();
        head.value = 1;
        Node node1 = new Node();
        node1.value = 2;
        Node node2 = new Node();
        node2.value = 3;
        Node node3 = new Node();
        node3.value = 4;

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        //reverse(head);
        Node newHead = rec(head);
        System.out.println(newHead);
    }

    /**
     * 双指针解决链表反转
     * @param head
     * @return
     */
    public static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        //声明一个变量代表你的新儿子
        Node prev = null;
        // A -> B -> C
        Node current = head;
        while (current != null) {
            //B -> C  首先获取你目前的儿子
            Node next = current.next;
            //A -> null  将你的新儿子给你
            current.next = prev;
            //将 A->null作为 prev节点  你和你的新儿子将打包作为你旧儿子的新儿子
            prev = current;
            //B -> C重新遍历
            current = next;
        }
        return prev;
    }

    public static Node rec(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        //递归找儿子
        Node newHead = rec(node.next);
        //儿子要当爹
        node.next.next = node;
        node.next = null;
        return newHead;
    }


    public static class Node {
        private Node next;
        private int value;
    }
}
