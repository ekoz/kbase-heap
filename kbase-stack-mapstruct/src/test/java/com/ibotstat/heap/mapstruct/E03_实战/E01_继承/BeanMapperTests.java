package com.ibotstat.heap.mapstruct.E03_实战.E01_继承;

import com.ibotstat.heap.mapstruct.E03_实战.E01_继承.vo.UserVO;
import com.ibotstat.heap.mapstruct.E03_实战.E01_继承.entity.User;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/17 10:50
 */
public class BeanMapperTests {

    @Test
    public void testUserToDto(){
        User user = User.builder()
                .name("小红")
                .age(18)
                .birthday(LocalDate.of(2000, 10, 1))
                .modifyUser("admin")
                .modifyDate(LocalDateTime.now())
                .build();

        UserVO userDto = BeanMapper.INSTANCE.entityToVO(user);
        System.out.println(userDto.toString());
        System.out.println(userDto.getModifyUser());
        System.out.println(userDto.getModifyDate());
    }

    @Test
    public void testDtoToUser(){
        UserVO userDto = UserVO.builder()
                .name("小明")
                .age("12")
                .birthday("2003-06-18")
                .build();

        User user = BeanMapper.INSTANCE.voToEntity(userDto);
        System.out.println(user.toString());
        System.out.println(user.getBirthday().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
