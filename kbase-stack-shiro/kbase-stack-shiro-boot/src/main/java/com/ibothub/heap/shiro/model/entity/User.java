/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/14 23:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class User implements Serializable {

    @TableId(type= IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String salt;
    @TableField("username_cn")
    private String usernameCN;
    private String remark;
    private String email;
    private String phone;
    private Integer age;
    private Integer sex;
    private LocalDate birthday;

    @TableField(exist = false)
    private String summary;
}
