package com.ibotstat.heap.mapstruct.E01_官方demo;

import com.ibotstat.heap.mapstruct.E01_官方demo.entity.Car;
import com.ibotstat.heap.mapstruct.E01_官方demo.vo.CarVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 13:04
 */
@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mappings({
            @Mapping(source = "seatNum", target = "seatCount")
    })
    CarVO entityToVO(Car car);
}
