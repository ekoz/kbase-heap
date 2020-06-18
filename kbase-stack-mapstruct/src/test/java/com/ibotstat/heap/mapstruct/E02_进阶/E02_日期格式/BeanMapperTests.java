package com.ibotstat.heap.mapstruct.E02_进阶.E02_日期格式;

import com.ibotstat.heap.mapstruct.E02_进阶.E02_日期格式.vo.UserVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E02_日期格式.entity.User;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 19:21
 */
public class BeanMapperTests {

    @Test
    public void testEntityToDto(){

        User user = User.builder()
                .name("小花")
                .age(15)
                .birthday(LocalDate.of(2005, 10, 8))
                .createDate(LocalDateTime.now())
                .build();


        UserVO userDto = BeanMapper.INSTANCE.entityToVO(user);

        System.out.println(userDto.toString());
    }
}
