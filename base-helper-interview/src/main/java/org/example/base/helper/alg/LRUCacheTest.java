package org.example.base.helper.alg;

import java.util.*;

/**
 * @author panfudong
 * @description
 */
public class LRUCacheTest {

    public static void main(String[] args) {
        LRUCacheTest lru = new LRUCacheTest(2);
        lru.put(2, 2);
        lru.put(1,1);
        lru.get(2);
        lru.put(3, 3);
        lru.get(2);
        lru.put(4, 4);
        lru.get(1);
        lru.get(3);
        lru.get(4);
        System.out.println(lru);
    }

    public LRUCacheTest (int capacity) {
        this.capacity = capacity;
        Node newNode = new Node();
        newNode.value = -1;
        Node newLast = new Node();
        newLast.value = -2;
        first = newNode;
        last = newLast;
        first.after = last;
        last.pre = first;
        cacheMap.put(-1, newNode);
        cacheMap.put(-2, newLast);
    }

    private static class Node {
        public int value;
        public Node pre;
        public Node after;
    }

    private int capacity;

    private Node first;
    private Node last;
    private Map<Integer, Node> cacheMap = new HashMap<>();

    public int get(int key) {
        Node node = cacheMap.get(key);
        if (Objects.nonNull(node)) {
            moveToHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        //判断元素是否存在
        Node curNode = cacheMap.get(key);
        if (Objects.isNull(curNode)) {
            if (cacheMap.size() + 1 > capacity) {
                //删除缓存
                cacheMap.remove(last.value);
                //删除队尾元素
                Node pre = last.pre;
                pre.after = null;
                last.pre = null;
                last = pre;
            }
            Node newNode = new Node();
            newNode.value = value;
            cacheMap.put(key, newNode);
            //将当前节点设置为头节点
            newNode.pre = null;
            newNode.after = first;
            first.pre = newNode;
            first = newNode;
        } else {
            //只需要调整元素位置即可
            moveToHead(curNode);
        }
    }

    private void moveToHead(Node node) {
        if (node != first) {
            //将当前节点的左右 节点连上
            node.pre.after = node.after;
            if (node.after != null) {
                node.after.pre = node.pre;
            } else {
                //当前是last节点
                last = last.pre;
            }
            //再将当前节点设置为头节点
            node.pre = null;
            node.after = first;
            first.pre = node;
            first = node;
        }
    }


}
