package com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 16:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dept {

    private String id;
    private String name;
    private String alias;
    private Dept parent;
}
