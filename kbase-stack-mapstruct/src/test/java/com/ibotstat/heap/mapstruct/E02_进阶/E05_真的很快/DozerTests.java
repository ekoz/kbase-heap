package com.ibotstat.heap.mapstruct.E02_进阶.E05_真的很快;

import com.github.dozermapper.core.Mapper;
import com.ibotstat.heap.mapstruct.E02_进阶.E05_真的很快.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/16 9:55
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class DozerTests extends CompareTests {

    @Resource
    private Mapper mapper;

    @Test
    public void testDozer(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

//        mapper.map(userList, userDtoList);
        userList.forEach(user -> {
            UserVO userDto = UserVO.builder().build();
            mapper.map(user, userDto);
            userDtoList.add(userDto);
        });

        System.out.println(userDtoList.size());
        System.out.println(userDtoList.get(0).toString());

        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
    }
}
