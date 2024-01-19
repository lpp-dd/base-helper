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

    int[] tmp;
    public int[] sortArray(int[] nums) {
        tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * 归并排序
     * @param nums
     * @param left
     * @param right
     */
    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        int i = left, j = mid + 1;
        int cnt = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[cnt++] = nums[i++];
            } else {
                tmp[cnt++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[cnt++] = nums[i++];
        }
        while (j <= right) {
            tmp[cnt++] = nums[j++];
        }
        for (int k = 0; k < right - left + 1; ++k) {
            nums[k + left] = tmp[k];
        }
    }

    public static void quickSorted(List<Integer> list) {
        if (list == null || list.size() <= 1) {
            return;
        }
        quickSorted(list, 0, list.size() - 1);
    }

    public static void quickSorted(List<Integer> list, int l, int r) {
        if (l > r) {
            return;
        }
        int base = list.get(r);
        int left = l;
        int right = r - 1;
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
        if (list.get(left) >= list.get(r)) {
            swap(list, left, r);
        } else {
            left++;
        }
        quickSorted(list, l, left - 1);
        quickSorted(list, left + 1, r);
    }

    private static void swap(List<Integer> list, int a, int b) {
        int temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }

}
