package com.ibotstat.heap.mapstruct.E03_实战.E01_继承.entity;

import com.ibotstat.heap.mapstruct.E03_实战.E01_继承.common.BaseEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/17 9:38
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String name;
    private Integer age;
    private LocalDate birthday;

    @Builder
    public User(String id, String createUser, String modifyUser, LocalDateTime createDate, LocalDateTime modifyDate, String name, Integer age, LocalDate birthday) {
        super(id, createUser, modifyUser, createDate, modifyDate);
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

}
