/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ibothub.heap.shiro.dao.PermMapper;
import com.ibothub.heap.shiro.model.entity.Perm;
import com.ibothub.heap.shiro.service.PermService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/30 22:58
 */
@Service
public class PermServiceImpl extends ServiceImpl<PermMapper, Perm> implements PermService {

    @Resource
    PermMapper permMapper;

    @Override
    public List<String> getKeysByUsername(String username) {
        return permMapper.selectKeysByUsername(username);
    }
}
