package org.example.base.helper.alg;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author panfudong
 * @description
 */
public class SortTest {

    public static void main(String[] args) {
        List<Integer> sourceList = new ArrayList<>();

        sourceList.add(3);
        sourceList.add(1);
        sourceList.add(6);
        sourceList.add(9);
        sourceList.add(2);
        sourceList.add(4);
        sourceList.add(8);
        quickSorted(sourceList);
        System.out.println(sourceList);


    }

    /**
     * 归并排序
     * @param list
     * @return
     */
    public static List<Integer> mergeSorted(List<Integer> list) {
        if (Objects.isNull(list) || list.size() <= 1) {
            return list;
        }
        mergeSorted(list, 0, list.size());
        return list;
    }

    private static void mergeSorted(List<Integer> list, int i, int j) {
        if (i >= j) {
            return;
        }
        int mid = (i + j) / 2;
        mergeSorted(list, i, mid);
        mergeSorted(list, mid, j);
        List<Integer> first = list.subList(i, mid);
        List<Integer> second = list.subList(mid, j);
        merge(first, second);

    }

    private static List<Integer> merge(List<Integer> s1, List<Integer> s2) {
        //合并两个有序链表
        return null;
    }


    public static void quickSorted(List<Integer> list) {
        if (list == null || list.size() <= 1) {
            return;
        }
        quickSorted(list, 0, list.size() - 1);
    }

    public static void quickSorted(List<Integer> list, int l, int r) {
        int base = list.get(l);
        int left = l + 1;
        int right = r;
        while (left <= right) {
            while (list.get(left) < base && left <= right) {
                left++;
            }
            while (list.get(right) > base && left <= right) {
                right--;
            }
            if (left < right) {
                swap(list, left, right);
            }
        }
        swap(list, l, left);
        quickSorted(list, l, left - 1);
        quickSorted(list, left + 1, r);
    }

    private static void swap(List<Integer> list, int a, int b) {
        int temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }

}
