/*
 * Powered by http://www.xiaoi.com
 */
package com.eastrobot.heap.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2019/7/17 14:52
 */
public class TempTests {

    @Test
    public void booleanEquals(){
        Boolean a = Boolean.FALSE;
        Boolean b = Boolean.FALSE;
        Map<String, Boolean> map = new HashMap<String, Boolean>(){
            {
                put("c", Boolean.FALSE);
            }
        };
        System.out.println(a == map.get("c"));
    }
}
