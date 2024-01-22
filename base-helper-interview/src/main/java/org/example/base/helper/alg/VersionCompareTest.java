package org.example.base.helper.alg;

/**
 * @author panfudong
 * @description
 */
public class VersionCompareTest {

    public int compareVersion(String version1, String version2) {
        String[] v1Arr = version1.split("\\.");
        String[] v2Arr = version2.split("\\.");
        int shortLength = Math.min(v1Arr.length, v2Arr.length);
        int i = 0;
        while (i < shortLength) {
            int v1 = Integer.parseInt(v1Arr[i]);
            int v2 = Integer.parseInt(v2Arr[i]);
            if (v1 > v2) {
                return 1;
            }
            if (v1 < v2) {
                return -1;
            }
            i++;
        }
        if (v1Arr.length > v2Arr.length) {
            while (i < v1Arr.length) {
                int v = Integer.parseInt(v1Arr[i]);
                if (v > 0) {
                    return 1;
                }
                i++;
            }
        }
        if (v1Arr.length < v2Arr.length) {
            while (i < v2Arr.length) {
                int v = Integer.parseInt(v2Arr[i]);
                if (v > 0) {
                    return -1;
                }
                i++;
            }
        }
        return 0;
    }

}
