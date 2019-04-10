/*
 * Power by www.xiaoi.com
 */
package com.eastrobot.heap.proxy;

import lombok.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * https://blog.csdn.net/Dome_/article/details/82427386
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version 1.0
 * @date 2019/4/9 14:41
 */
class JdkDynamicAgent {

    class User {
        @Getter
        @Setter
        private String name;

    }
    interface UserService {
        void addUser(User user);
    }
    class UserServiceImpl implements UserService{

        @Override
        public void addUser(User user) {
            System.out.println("添加用户[" + user.getName() + "]");
        }
    }

    interface RoleService{
        void addRole(String name);
    }
    class RoleServiceImpl implements RoleService{
        @Override
        public void addRole(String name) {
            System.out.println("添加角色[" + name + "]");
        }
    }
    class ServiceHandler implements InvocationHandler{

        /**
         * 被代理对象，Object类型
         */
        private Object target;

        ServiceHandler(Object target){
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("准备向数据库中插入数据");
            Object returnValue = method.invoke(target, args);
            System.out.println("插入数据库成功");
            return returnValue;
        }
    }

    class StudentService{
        void add(String name){
            System.out.println("添加学生 [" + name + "]");
        }
    }
}

