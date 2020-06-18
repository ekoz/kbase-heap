package com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 16:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    private String id;
    private String name;
    private List<User> userList;
}
