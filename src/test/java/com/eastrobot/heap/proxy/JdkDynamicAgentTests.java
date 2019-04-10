/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version 1.0
 * @date 2019/4/9 17:10
 */
public class JdkDynamicAgentTests {

    @Test
    public void test(){
        //添加用户
        JdkDynamicAgent jdkDynamicAgent = new JdkDynamicAgent();
        JdkDynamicAgent.UserService userServiceTarget = jdkDynamicAgent.new UserServiceImpl();
        JdkDynamicAgent.ServiceHandler handler = jdkDynamicAgent.new ServiceHandler(userServiceTarget);
        JdkDynamicAgent.UserService userService = (JdkDynamicAgent.UserService)Proxy.newProxyInstance(JdkDynamicAgentTests.class.getClassLoader(),
                userServiceTarget.getClass().getInterfaces(), handler);
        JdkDynamicAgent.User user = jdkDynamicAgent.new User();
        user.setName("ekoz");
        userService.addUser(user);

        //添加角色
        JdkDynamicAgent.RoleService roleServiceTarget = jdkDynamicAgent.new RoleServiceImpl();
        JdkDynamicAgent.ServiceHandler roleHandler = jdkDynamicAgent.new ServiceHandler(roleServiceTarget);
        JdkDynamicAgent.RoleService roleService = (JdkDynamicAgent.RoleService)Proxy.newProxyInstance(JdkDynamicAgentTests.class.getClassLoader(),
                roleServiceTarget.getClass().getInterfaces(), roleHandler);
        roleService.addRole("管理员");

    }

    @Test
    public void test1(){
        // java.lang.ClassCastException: com.sun.proxy.$Proxy4 cannot be cast to com.eastrobot.heap.proxy.JdkDynamicAgent$StudentService
        JdkDynamicAgent jdkDynamicAgent = new JdkDynamicAgent();
        JdkDynamicAgent.StudentService serviceTarget = jdkDynamicAgent.new StudentService();
        JdkDynamicAgent.ServiceHandler handler = jdkDynamicAgent.new ServiceHandler(serviceTarget);
        JdkDynamicAgent.StudentService service = (JdkDynamicAgent.StudentService)Proxy.newProxyInstance(JdkDynamicAgentTests.class.getClassLoader(),
                serviceTarget.getClass().getInterfaces(), handler);
        service.add("张三");
    }
}
