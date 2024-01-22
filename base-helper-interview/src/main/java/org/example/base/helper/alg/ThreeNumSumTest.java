package org.example.base.helper.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * @author panfudong
 * @description
 */
public class ThreeNumSumTest {
    public static void main(String[] args) {
        List<List<Integer>> result = threeSum(new int[]{-1,0,1,2,-1,-4});
        System.out.println(result);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        nums = sortArray(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> twoNumResult = twoSum(nums, i + 1, nums.length, -nums[i]);
            if (twoNumResult.size() > 0) {
                for (List<Integer> twoNumResultItem : twoNumResult) {
                    if (result.size() > 0) {
                        List<Integer> last = result.get(result.size() - 1);
                        if (last.get(0) == nums[i] && last.get(1).intValue() == twoNumResultItem.get(0) && last.get(2).intValue() == twoNumResultItem.get(1)) {
                            continue;
                        }
                    }
                    List<Integer> resultItem = new ArrayList<>();
                    resultItem.add(nums[i]);
                    resultItem.addAll(twoNumResultItem);
                    result.add(resultItem);
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> twoSum(int[] nums, int start, int end, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int i = start;
        int j = end - 1;
        while (i < j) {
            int curTarget = nums[i] + nums[j];
            if (curTarget > target) {
                j--;
            }
            if (curTarget < target) {
                i++;
            }
            if (curTarget == target) {
                List<Integer> resultItem = new ArrayList<>();
                resultItem.add(nums[i]);
                resultItem.add(nums[j]);
                result.add(resultItem);
                i++;
                j--;
            }
        }
        return result;
    }

    static int[] tmp;
    public static int[] sortArray(int[] nums) {
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
    private static void mergeSort(int[] nums, int left, int right) {
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
}
