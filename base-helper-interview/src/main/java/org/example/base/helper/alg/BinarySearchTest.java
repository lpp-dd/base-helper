package org.example.base.helper.alg;

/**
 * @author panfudong
 * @description 搜索旋转排序数组
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/description/
 */
public class BinarySearchTest {


    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int result = search(nums, 6);
        System.out.println(result);
    }

    public static int search(int[] nums, int target) {
        if (nums[nums.length - 1] == target) {
            return nums.length - 1;
        }
        if (nums[0] == target) {
            return 0;
        }
        return search(nums, 0, nums.length - 1, target);
    }


    private static int search(int[] nums, int left, int right, int target) {
        while(left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (left == right - 1) {
                return -1;
            }
            if (nums[left] < nums[mid]) {
                //左边是有序的
                if (nums[left] < target && target < nums[mid]) {
                    //目标值在左边
                    right = mid;
                } else {
                    //目标值在右边的乱序里面
                    left = mid;
                }
            } else {
                //右边是有序的
                if (nums[right] > target && target > nums[mid]) {
                    //目标值在右边
                    left = mid;
                } else {
                    //目标值在左边乱序里面
                    right = mid;
                }
            }
        }
        return -1;
    }
}
