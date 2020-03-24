/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.leetcode.P1108_IP地址无效化;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2019/7/28 18:08
 * https://leetcode-cn.com/problems/defanging-an-ip-address/
 */
class Solution {
    public String defangIPaddr0(String address) {
        String s = "";
        while (address.contains(".")){
            s += address.substring(0, address.indexOf(".")) + "[.]";
            address = address.substring(address.indexOf(".")+1);
        }
        s += address;
        return s;
    }

    public String defangIPaddr(String address) {
        return address.replaceAll("\\.", "[.]");
    }

    public static void main(String[] args) {
        String[] nums = new String[]{"1.1.1.1", "255.100.50.0"};
        for (String num : nums){
            long started = System.currentTimeMillis();
            String result = new Solution().defangIPaddr(num);
            System.out.println("interval: " + (System.currentTimeMillis() - started));
            System.out.println(result);
        }

    }
}


