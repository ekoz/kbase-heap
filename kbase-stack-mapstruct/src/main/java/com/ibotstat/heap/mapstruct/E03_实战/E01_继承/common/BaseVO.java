package com.ibotstat.heap.mapstruct.E03_实战.E01_继承.common;

import lombok.*;

import java.io.Serializable;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/17 9:32
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BaseVO implements Serializable {

    protected String id;
    protected String createUser;
    protected String modifyUser;
    protected String createDate;
    protected String modifyDate;

}
