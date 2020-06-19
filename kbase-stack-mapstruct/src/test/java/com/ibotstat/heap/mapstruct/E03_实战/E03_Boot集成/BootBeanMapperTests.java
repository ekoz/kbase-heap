package com.ibotstat.heap.mapstruct.E03_实战.E03_Boot集成;

import com.ibotstat.heap.mapstruct.E01_官方demo.entity.Car;
import com.ibotstat.heap.mapstruct.E01_官方demo.entity.CarType;
import com.ibotstat.heap.mapstruct.E01_官方demo.entity.ColorType;
import com.ibotstat.heap.mapstruct.E01_官方demo.vo.CarVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E05_真的很快.CompareTests;
import com.ibotstat.heap.mapstruct.E03_实战.E03_Boot集成.samples01.CarMapper;
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
public class BootBeanMapperTests extends CompareTests {

    @Resource
    private CarMapper carMapper;

    @Test
    public void test(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        CarVO carVO = carMapper.backword(Car.builder().seatNum(4).type(CarType.NORMAL).color(ColorType.BLACK).build());
        System.out.println(carVO.toString());

        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
    }
}
