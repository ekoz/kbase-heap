package com.ibotstat.heap.mapstruct.E02_进阶.E05_真的很快;

import com.ibotstat.heap.mapstruct.E02_进阶.E05_真的很快.entity.User;
import com.ibotstat.heap.mapstruct.E02_进阶.E05_真的很快.vo.UserVO;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 19:34
 */
public class CompareTests {

    List<User> userList = Lists.newArrayList();
    List<UserVO> userDtoList = Lists.newArrayList();

    @Test
    public void testBeanUtils(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        userList.forEach(user -> {
            UserVO userDto = UserVO.builder().build();
            BeanUtils.copyProperties(user, userDto);
            userDtoList.add(userDto);
        });
        System.out.println(userDtoList.size());
        System.out.println(userDtoList.get(0).toString());

        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
    }

    @Test
    public void testMapStruct(){

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<UserVO> userDtoList = BeanMapper.INSTANCE.entityToVO(userList);
        System.out.println(userDtoList.size());
        System.out.println(userDtoList.get(0).toString());

        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());

    }

    @Before
    public void setUp(){
        for (int i=0;i<10000;i++){
            userList.add(
                    User.builder()
                    .name("小红_" + i)
                    .age(i)
                    .phone(i+"")
                    .email("xiaohong_"+i+"@outlook.com")
                            .sex(1)
                            .birthday(LocalDate.of(1990, 5, 1))
                            .createDate(LocalDateTime.now())
                            .createUser("admin")
                            .modifyDate(LocalDateTime.now())
                            .modifyUser("admin")
                    .build()
            );
        }
    }
}
