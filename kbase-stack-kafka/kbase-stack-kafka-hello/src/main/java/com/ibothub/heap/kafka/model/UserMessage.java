package com.ibothub.heap.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2021/1/30 下午1:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMessage implements Serializable {

    private Integer id;
    private String username;
    private Integer age;
    private Integer sex;
    private LocalDate birthday;
    private String idCard;
    private String password;

}
