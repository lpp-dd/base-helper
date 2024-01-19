package org.example.base.helper.alg;

/**
 * @author panfudong
 * @description
 */
public class MergeTwoSortedList {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,4,5};
        int[] nums2 = new int[]{2,5,6};
        merge(nums1, nums1.length, nums2, nums2.length);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int[] result = new int[m+n];
        int z = 0;
        while (i < m && j < n) {
            int value = nums1[i];
            if (nums1[i] > nums2[j]) {
                value = nums2[j];
                j++;
            } else {
                i++;
            }
            result[z] = value;
            z++;
        }
        while (i < m) {
            result[z] = nums1[i];
            i++;
            z++;
        }
        while (j < n) {
            result[z] = nums2[j];
            j++;
            z++;
        }
        System.out.println(result);
    }

    /**
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void mergeWithO1(int[] nums1, int m, int[] nums2, int n) {
        int lastIndex = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            int value = nums1[i];
            if (nums1[i] < nums2[j]) {
                value = nums2[j];
                j--;
            } else {
                i--;
            }
            nums1[lastIndex] = value;
            lastIndex--;
        }
        while (j >= 0) {
            nums1[lastIndex] = nums2[j];
            lastIndex--;
            j--;
        }
    }
}
