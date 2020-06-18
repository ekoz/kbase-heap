package com.ibotstat.heap.mapstruct.E02_进阶.E05_真的很快.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 19:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {

    private String id;
    private String name;
    private Integer age;
    private String birthday;
    private Integer sex;
    private String email;
    private String phone;
    private String createDate;
    private String modifyDate;
    private String createUser;
    private String modifyUser;
    private Integer delFlag;

}