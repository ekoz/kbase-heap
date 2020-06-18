package com.ibotstat.heap.mapstruct.E01_官方demo;

import com.alibaba.fastjson.JSON;
import com.ibotstat.heap.mapstruct.E01_官方demo.vo.CarVO;
import com.ibotstat.heap.mapstruct.E01_官方demo.entity.Car;
import com.ibotstat.heap.mapstruct.E01_官方demo.entity.CarType;
import com.ibotstat.heap.mapstruct.E01_官方demo.entity.ColorType;
import org.junit.Test;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 13:10
 */
public class CarMapperTests {

    @Test
    public void testEntityToDto(){
        Car car = Car.builder()
                .color(ColorType.BLACK)
                .seatNum(4)
                .type(CarType.NORMAL)
                .build();

        CarVO carDto = CarMapper.INSTANCE.entityToVO(car);
        System.out.println(JSON.toJSON(carDto).toString());

    }
}
