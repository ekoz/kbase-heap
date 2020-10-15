/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.model.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/14 23:59
 */
@Data
@Builder
@Entity
@Table(name = "sys_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;
    @Column(name = "username_cn")
    private String usernameCN;
    @Column
    private String remark;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private Integer age;
    @Column
    private Integer sex;
    @Column
    private LocalDate birthday;
}
