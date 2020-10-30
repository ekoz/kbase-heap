/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ibothub.heap.shiro.model.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/30 22:59
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleServiceTests {

    @Resource
    RoleService roleService;

    @Test
    public void testGetOne(){
        Role role = roleService.getOne(Wrappers.lambdaQuery(), false);
        System.out.println(role.toString());
    }
}
