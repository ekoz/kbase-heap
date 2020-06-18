package com.ibotstat.heap.mapstruct.E01_官方demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 13:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarVO {

    private String id;
    private Integer seatCount;
    private String color;
    private String type;
}
