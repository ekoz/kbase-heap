package com.ibotstat.heap.mapstruct.E03_实战.E01_继承;

import com.ibotstat.heap.mapstruct.E03_实战.E01_继承.entity.User;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/17 9:42
 */
public class UserTests {

    @Test
    public void testToString(){
        User user = User.builder()
                .name("小红")
                .age(18)
                .birthday(LocalDate.of(2000, 10, 1))
                .modifyUser("admin")
                .modifyDate(LocalDateTime.now())
                .build();

        System.out.println(user.toString());
    }
}
