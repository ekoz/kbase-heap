/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ibothub.heap.shiro.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/15 21:49
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTests {

    @Resource
    UserMapper userMapper;

    @Test
    public void testFind(){
        userMapper.selectList(null).forEach(user -> System.out.println(user.toString()));
    }

    @Test
    public void testSave(){
        User user = User.builder()
                .username("ekoz1")
                .password("ekoz88")
                .age(18)
                .sex(0)
                .usernameCN("展昭1")
                .build();
        userMapper.insert(user);
    }

    @Test
    public void testDelete(){
//        int i = userMapper.deleteById(5);
//        System.out.println(i);
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class);
        queryWrapper.eq(User::getUsername, "ekoz1");
        int i = userMapper.delete(queryWrapper);
        System.out.println(i);


    }

}
