/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ibothub.heap.shiro.model.entity.Role;

import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/30 22:58
 */
public interface RoleService extends IService<Role> {

    List<String> getKeysByUsername(String username);
}
