/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.web.controller;

import com.ibothub.heap.shiro.dao.UserRepository;
import com.ibothub.heap.shiro.model.entity.User;
import com.ibothub.heap.shiro.service.UserService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/14 23:58
 */
@Api("用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

//    @RequiresRoles("user")
    @GetMapping
    public User get(String id){
        return User.builder().username("ekoz").build();
    }

//    @RequiresRoles({"admin", "user"})
    @PostMapping
    public User save(User user){
        return user;
    }

    @PostMapping("/register")
    public User register(String username, String password){
        return userService.register(username, password);
    }

}
