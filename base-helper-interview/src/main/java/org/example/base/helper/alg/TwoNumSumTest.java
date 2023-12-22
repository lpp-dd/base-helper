package org.example.base.helper.alg;

import java.util.HashMap;
import java.util.Map;

/**
 * @author panfudong
 * @description
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在数组中找出和为目标值的两个整数，并返回它们的索引。假设每种输入只对应一个答案，而且不可以重复利用相同的元素。
 * 你可以假设数组中存在且只存在一对符合条件的数。
 * 例如：
 * 输入：nums = [2, 7, 11, 15], target = 9
 * 输出：[0, 1]
 * 解释：nums[0] + nums[1] = 2 + 7 = 9，因此返回 [0, 1]。
 */
public class TwoNumSumTest {

    public static void main(String[] args) {
        //使用hash表来实现  判断差值是否存在
        int[] arr = new int[]{};
        Map<Integer, Integer> map = new HashMap<>();
        int target = 10;
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            int chazhi = target - value;
            if (map.containsKey(chazhi)) {
                System.out.println(map.get(chazhi));
                System.out.println(i);
                return;
            }
            map.put(value, i);
        }
    }

}
