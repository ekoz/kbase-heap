/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.web.controller;

import com.ibothub.heap.shiro.model.entity.User;
import com.ibothub.heap.shiro.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/14 23:58
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/{id}")
    public User get(@PathVariable String id){
        return userService.getById(id);
    }

    @GetMapping()
    public List<User> list(){
        return userService.list();
    }

    @RequiresPermissions({"user:*"})
    @PostMapping
    public User save(User user){
        userService.save(user);
        return user;
    }

    @RequiresPermissions({"user:*"})
    @PutMapping
    public User update(User user){
        return user;
    }


    @PostMapping("/register")
    public User register(String username, String password){
        return userService.register(username, password);
    }

}
