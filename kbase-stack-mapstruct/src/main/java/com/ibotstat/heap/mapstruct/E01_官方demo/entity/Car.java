package com.ibotstat.heap.mapstruct.E01_官方demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 13:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {

    private String id;
    private Integer seatNum;
    private ColorType color;
    private CarType type;
}
