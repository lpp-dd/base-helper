package org.example.base.helper.alg;

import java.util.List;
import java.util.Objects;

/**
 * @author panfudong
 * @description
 */
public class SortTest {

    public static void main(String[] args) {
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

}
