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

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/29 21:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_perm")
public class Perm implements Serializable {

    @TableId(type= IdType.AUTO)
    private Integer id;

    private String name;

    @TableField("url_")
    private String url;

    @TableField("type_")
    private Integer type;
}
