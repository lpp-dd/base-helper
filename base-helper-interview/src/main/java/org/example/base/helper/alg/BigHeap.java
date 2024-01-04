package org.example.base.helper.alg;

import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author panfudong
 * @description
 *
 * 前 K 个高频元素
 * 题目描述： 给定一个非空整数数组，返回其中出现频率前 k 高的元素。
 * 考察内容： 堆的应用。
 */
public class BigHeap {

    //这个方法是对题目的解答方案
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{};
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int count = countMap.computeIfAbsent(arr[i], j -> 0);
            countMap.put(i, count + 1);
        }
        PriorityQueue<Item> queue = new PriorityQueue<>(Comparator.comparing(Item::getCount).reversed());
        countMap.entrySet().forEach(entry -> {
            queue.add(new Item(entry.getValue(), entry.getKey()));
        });
        while (!queue.isEmpty()) {
            System.out.println(JSON.toJSONString(queue.poll()));
        }
    }

    @Getter
    @Setter
    public static class Item {
        private int count;
        private int value;

        public Item(int count, int value) {
            this.count = count;
            this.value = value;
        }
    }

    private List<BinaryTreeTest.BinaryTree> bigHeap;

    public void add(BinaryTreeTest.BinaryTree bTree) {
        bigHeap.add(bTree);
        //节点上浮
        up(bigHeap.size() - 1);
    }

    public void up(int j) {
        if (j <= 0 || j >= bigHeap.size()) {
            return;
        }
        int i = (j - 1) / 2;
        BinaryTreeTest.BinaryTree sun = bigHeap.get(j);
        BinaryTreeTest.BinaryTree father = bigHeap.get(i);
        if (sun.getValue() > father.getValue()) {
            bigHeap.set(i, sun);
            bigHeap.set(j, father);
        } else {
            return;
        }
        up(i);
    }


}
