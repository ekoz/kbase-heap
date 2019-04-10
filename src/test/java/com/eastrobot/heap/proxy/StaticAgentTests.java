/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.proxy;

import org.junit.Test;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version 1.0
 * @date 2019/4/9 14:22
 */
public class StaticAgentTests {

    @Test
    public void test(){
        StaticAgent.Person zhangsan = new StaticAgent.Man("张三");
        StaticAgent.Proxy proxy = new StaticAgent.Proxy(zhangsan);
        proxy.sayHi();
    }
}
