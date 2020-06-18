package com.ibotstat.heap.mapstruct.E03_实战.E01_继承.common;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/17 9:31
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
//@MappedSuperclass
public class BaseEntity implements Serializable {

//    @Id
    protected String id;
//    @Column()
    protected String createUser;
    protected String modifyUser;
    protected LocalDateTime createDate;
    protected LocalDateTime modifyDate;
}
