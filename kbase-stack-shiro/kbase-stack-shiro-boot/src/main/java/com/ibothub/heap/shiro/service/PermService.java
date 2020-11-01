/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ibothub.heap.shiro.model.entity.Perm;

import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/19 21:33
 */
public interface PermService extends IService<Perm> {

    List<String> getKeysByUsername(String username);
}
