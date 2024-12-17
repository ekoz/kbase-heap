/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ibothub.heap.shiro.dao.RoleMapper;
import com.ibothub.heap.shiro.model.entity.Role;
import com.ibothub.heap.shiro.model.entity.User;
import com.ibothub.heap.shiro.service.RoleService;
import com.ibothub.heap.shiro.service.UserService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/30 22:58
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    RoleMapper roleMapper;

    @Override
    public List<String> getKeysByUsername(String username) {
        return roleMapper.selectKeysByUsername(username);
    }
}
