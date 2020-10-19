/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.service;

import com.ibothub.heap.shiro.model.entity.User;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/19 21:33
 */
public interface UserService {

    User register(String username, String password);

    User findByUsername(String username);
}
