package org.example.base.helper.alg;

/**
 * @author panfudong
 * @description
 */
public class DpTest {


    public static void main(String[] args) {

        int result = maxSubStrLength(new int[]{6,2,3,4,4,3,6,7});
        System.out.println(result);

    }


    public static int maxSubStrLength(int[] num) {
        int[] dp = new int[num.length];
        dp[0] = 1;
        for (int i = 0; i < num.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (num[i] > num[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        int result = dp[0];
        for (int j : dp) {
            result = Math.max(result, j);
        }
        return result;
    }
}
