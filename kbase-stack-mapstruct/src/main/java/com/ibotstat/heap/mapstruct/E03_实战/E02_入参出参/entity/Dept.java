package com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.entity;

import com.ibotstat.heap.mapstruct.E03_实战.E01_继承.common.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/17 13:54
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Dept extends BaseEntity {

    private String name;
    private Dept parent;

    @Builder
    public Dept(String id, String createUser, String modifyUser, LocalDateTime createDate, LocalDateTime modifyDate, String name, Dept parent) {
        super(id, createUser, modifyUser, createDate, modifyDate, 0);
        this.name = name;
        this.parent = parent;
    }
}
