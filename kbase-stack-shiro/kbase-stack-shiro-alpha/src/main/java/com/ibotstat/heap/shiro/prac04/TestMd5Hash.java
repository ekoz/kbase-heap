/*
 * powered by https://zhengxinacc.com
 */
package com.ibotstat.heap.shiro.prac04;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/9 21:01
 */
public class TestMd5Hash {

    public static void main(String[] args) {
        // 使用 md5
        Md5Hash md5Hash = new Md5Hash("ekoz88");
        String s = md5Hash.toHex();
        System.out.println(s);

        // 使用 md5 + salt
        Md5Hash md5Hash1 = new Md5Hash("ekoz88", "zxacc");
        System.out.println(md5Hash1);

        // 使用 md5 + salt + hash散列
        Md5Hash md5Hash2 = new Md5Hash("ekoz88", "zxacc", 1024);
        System.out.println(md5Hash2);
    }
}
