/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.proxy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * https://blog.csdn.net/Dome_/article/details/82427386
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version 1.0
 * @date 2019/4/9 14:41
 */
class CglibDynamicAgent {
    // 内部类引发 java.lang.IllegalArgumentException: Superclass has no null constructors but no arguments were given 的问题
}
interface RoleService{
    void addRole(String name);
}
class RoleServiceImpl implements RoleService {
    @Override
    public void addRole(String name) {
        System.out.println("添加角色[" + name + "]");
    }
}
class ServiceInterceptor implements MethodInterceptor {

    public Object getInstance(Object target){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        //设置回调方法
        enhancer.setCallback(this);
        //创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("准备向数据库中插入数据");
        Object o = proxy.invokeSuper(obj, args);
        System.out.println("插入数据库成功");
        return o;
    }
}


