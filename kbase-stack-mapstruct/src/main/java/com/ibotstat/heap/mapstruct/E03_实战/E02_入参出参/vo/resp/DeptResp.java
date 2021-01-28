package com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.vo.resp;

import com.ibotstat.heap.mapstruct.E03_实战.E01_继承.common.BaseVO;
import lombok.*;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/17 13:51
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeptResp extends BaseVO {

    private String name;
    private DeptResp parent;

    @Builder
    public DeptResp(String id, String createUser, String modifyUser, String createDate, String modifyDate, String name, DeptResp parent) {
        super(id, createUser, modifyUser, createDate, modifyDate, 0);
        this.name = name;
        this.parent = parent;
    }
}
