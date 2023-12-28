package org.example.base.helper.alg;

import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.HashMap;
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

}
