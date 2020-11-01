/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.ibothub.heap.shiro.dao.UserMapper;
import com.ibothub.heap.shiro.model.entity.User;
import com.ibothub.heap.shiro.service.UserService;
import com.ibothub.heap.shiro.util.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/19 21:34
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;
    @Value("${shiro.md5:false}")
    Boolean enableShiroMd5;


    @Override
    public User register(String username, String password) {
        String salt = SaltUtils.getSalt();

        if (enableShiroMd5){
            // 铭文密码进行 md5 + salt + hash 散列
            Md5Hash md5Hash = new Md5Hash(password, salt, 1024);
            password = md5Hash.toHex();
        }

        User user = User.builder()
                .username(username)
                .password(password)
                .salt(salt)
                .build();

        userMapper.insert(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("username", username);
        List<User> userList = userMapper.selectByMap(queryMap);
        if (userList.size()>0) return userList.get(0);
        return null;
    }
}
