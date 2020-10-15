/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.web.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/15 20:45
 */
@Api("登录")
@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public Integer login(String username, String password){
        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
            System.out.println("认证状态，" + subject.isAuthenticated());
            return 1;
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("认证失败，用户名不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("认证失败，密码错误");
        }
        return 0;
    }
}
