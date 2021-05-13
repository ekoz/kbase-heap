package com.ibotstat.heap.mapstruct.E03_实战.E03_Boot集成.samples02.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 16:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {
    private String id;
    private String name;
    private String nameCN;
    private Integer age;
    private String birthday;
    private Integer delFlag;
}
