/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.proxy;

import org.junit.Test;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version 1.0
 * @date 2019/4/9 17:51
 */
public class CglibDynamicAgentTests {

    @Test
    public void test(){
        ServiceInterceptor serviceInterceptor = new ServiceInterceptor();

        RoleService roleService = (RoleService)serviceInterceptor.getInstance(new RoleServiceImpl());
        roleService.addRole("管理员");

    }
}
