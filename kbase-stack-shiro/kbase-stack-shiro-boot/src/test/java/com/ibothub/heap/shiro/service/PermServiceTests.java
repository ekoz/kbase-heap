/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ibothub.heap.shiro.model.entity.Perm;
import com.ibothub.heap.shiro.model.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/30 22:59
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PermServiceTests {

    @Resource
    PermService permService;

    @Test
    public void testGetRoleListByUsername(){
        permService.getKeysByUsername("ekozhan")
                .forEach(System.out::println);

    }
}
