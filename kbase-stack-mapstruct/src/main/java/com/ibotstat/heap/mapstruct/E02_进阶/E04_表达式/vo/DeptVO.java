package com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 16:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeptVO {

    private String id;
    private String name;
    private String parentId;
    private String parentName;
}
