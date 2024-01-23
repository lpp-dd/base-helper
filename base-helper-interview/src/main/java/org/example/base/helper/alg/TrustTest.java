package org.example.base.helper.alg;

/**
 * @author panfudong
 * @description 小镇法官
 * https://leetcode.cn/problems/find-the-town-judge/description/
 */
public class TrustTest {

    public static void main(String[] args) {

        findJudge(2, new int[][]{new int[]{1,2}});
    }

    public static int findJudge(int n, int[][] trust) {
        int[] in = new int[n + 1];
        int[] out = new int[n + 1];

        for (int[] trustItem : trust) {
            ++in[trustItem[1]];
            ++out[trustItem[0]];
        }

        for (int i = 1; i <= n; i++) {
            if (in[i] == (n - 1) && out[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
