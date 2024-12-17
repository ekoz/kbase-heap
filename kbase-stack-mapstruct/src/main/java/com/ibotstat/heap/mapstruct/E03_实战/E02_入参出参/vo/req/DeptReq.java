package com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.vo.req;

import com.ibotstat.heap.mapstruct.E03_实战.E01_继承.common.BaseVO;
import lombok.*;

import jakarta.validation.constraints.NotEmpty;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/17 13:50
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeptReq extends BaseVO {

    @NotEmpty
    private String name;
    private String parentId;

    @Builder
    public DeptReq(String id, String createUser, String modifyUser, String createDate, String modifyDate, @NotEmpty String name, String parentId) {
        super(id, createUser, modifyUser, createDate, modifyDate, 0);
        this.name = name;
        this.parentId = parentId;
    }
}
