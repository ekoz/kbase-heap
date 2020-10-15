/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.web.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/15 20:45
 */
@Api("登出")
@RestController
@RequestMapping("/logout")
public class LogoutController {

    @PostMapping
    public void logout(){
        SecurityUtils.getSubject().logout();
    }
}
