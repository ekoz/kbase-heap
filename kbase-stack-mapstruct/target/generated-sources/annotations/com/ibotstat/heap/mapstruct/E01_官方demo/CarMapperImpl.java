package com.ibotstat.heap.mapstruct.E01_官方demo;

import com.ibotstat.heap.mapstruct.E01_官方demo.entity.Car;
import com.ibotstat.heap.mapstruct.E01_官方demo.vo.CarVO;
import com.ibotstat.heap.mapstruct.E01_官方demo.vo.CarVO.CarVOBuilder;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-17T14:08:40+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_201-2-redhat (Oracle Corporation)"
)
public class CarMapperImpl implements CarMapper {

    @Override
    public CarVO entityToVO(Car car) {
        if ( car == null ) {
            return null;
        }

        CarVOBuilder carVO = CarVO.builder();

        carVO.seatCount( car.getSeatNum() );
        carVO.id( car.getId() );
        if ( car.getColor() != null ) {
            carVO.color( car.getColor().name() );
        }
        if ( car.getType() != null ) {
            carVO.type( car.getType().name() );
        }

        return carVO.build();
    }
}
