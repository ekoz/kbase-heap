package com.ibotstat.heap.mapstruct.E03_实战.E01_继承.vo;

import com.ibotstat.heap.mapstruct.E03_实战.E01_继承.common.BaseVO;
import lombok.*;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/17 9:40
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserVO extends BaseVO {

    private String name;
    private String age;
    private String birthday;


    @Builder
    public UserVO(String id, String createUser, String modifyUser, String createDate, String modifyDate, String name, String age, String birthday) {
        super(id, createUser, modifyUser, createDate, modifyDate);
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }
}
