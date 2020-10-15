/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.web.controller;

import com.ibothub.heap.shiro.model.entity.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/14 23:58
 */
@Api("用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public User get(String id){
        return User.builder().username("ekoz").build();
    }

    @PostMapping
    public User save(User user){
        return user;
    }
}
