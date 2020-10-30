/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibothub.heap.shiro.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/29 22:13
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> selectByRoleIds();

    IPage<User> selectByUsername(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
