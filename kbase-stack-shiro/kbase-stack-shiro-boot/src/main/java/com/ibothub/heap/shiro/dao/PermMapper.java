/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ibothub.heap.shiro.model.entity.Perm;

import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/29 22:53
 */
public interface PermMapper extends BaseMapper<Perm> {

    List<String> selectKeysByUsername(String username);
}
