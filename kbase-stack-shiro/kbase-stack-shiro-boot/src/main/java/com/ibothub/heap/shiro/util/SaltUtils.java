/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.util;

import java.util.UUID;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/19 21:43
 */
public class SaltUtils {

    /**
     * 获取4位 salt
     * @return
     */
    public static String getSalt(){
        return getSalt(4);
    }

    /**
     * 获取指定位数的salt
     * @param n
     * @return
     */
    public static String getSalt(int n){
        return UUID.randomUUID().toString().substring(0, n);
    }

    public static void main(String[] args) {
        System.out.println(getSalt());
    }
}
