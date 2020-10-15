/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.dao;

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
    UserRepository userRepository;

    @Test
    public void testFind(){
        userRepository.findAll().forEach(user -> System.out.println(user.toString()));
    }

    @Test
    public void testSave(){
        User user = User.builder()
                .username("ekoz")
                .password("ekoz88")
                .age(18)
                .sex(0)
                .usernameCN("展昭")
                .build();
        userRepository.save(user);
    }

}
