package org.example.base.helper.alg;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author panfudong
 * @description
 */
public class Backtracking {

    /**
     * 题目
     *
     * 给定一个字符串，代表一个IPv4地址，例如："192.168.0.1"。请编写一个函数，将该字符串分割成所有有效的IPv4地址组合，并返回一个包含这些地址的列表。
     *
     * 输入："192.168.0.1"
     * 输出：["192.168.0.1"]
     *
     * 输入："25525511135"
     * 输出：["255.255.11.135", "255.255.111.35"]
     */
    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(restoreIp("25525511135")));
    }

    public static List<String> restoreIp(String ipAddress) {
        if (StringUtils.isBlank(ipAddress) || ipAddress.length() < 4 || ipAddress.length() > 12) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        List<String> ipSegmentList = new ArrayList<>();
        backtracking(ipAddress, 0, ipSegmentList, result);
        return result;
    }

    public static void backtracking(String ipAddress, int start, List<String> ipSegmentList, List<String> result) {
        if (ipSegmentList.size() == 4 && start == ipAddress.length()) {
            result.add(String.join(".", ipSegmentList));
            return;
        }
        if (ipSegmentList.size() > 4) {
            return;
        }
        for (int end = start + 1; end <= Math.min(start + 3, ipAddress.length()); end++) {
            String ipSegment = ipAddress.substring(start, end);
            int ipSegmentNumber = Integer.parseInt(ipSegment);
            if (ipSegmentNumber <= 255) {
                ipSegmentList.add(ipSegment);
                backtracking(ipAddress, end, ipSegmentList, result);
                ipSegmentList.remove(ipSegmentList.size() - 1);
            }
        }
    }
}
