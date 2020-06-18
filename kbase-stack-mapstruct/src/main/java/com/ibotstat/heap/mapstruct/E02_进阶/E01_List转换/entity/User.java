package com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 15:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String id;
    private String name;
    private Integer age;
    private LocalDate birthday;

}
