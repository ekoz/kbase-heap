package com.ibotstat.heap.mapstruct.E03_实战.E03_Boot集成;

import com.google.common.collect.Lists;
import com.ibotstat.heap.mapstruct.E03_实战.E03_Boot集成.samples02.BeanMapper;
import com.ibotstat.heap.mapstruct.E03_实战.E03_Boot集成.samples02.entity.User;
import com.ibotstat.heap.mapstruct.E03_实战.E03_Boot集成.samples02.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/19 20:16
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserBeanMapperTests {

    @Resource
    BeanMapper beanMapper;

    @Test
    public void testForward(){
        UserVO userVO = UserVO.builder()
                .name("小明")
                .age(12)
                .birthday("2001-09-01")
                .delFlag(1)
                .build();
        User user = beanMapper.forward(userVO);
        System.out.println(user.toString());

    }

}
