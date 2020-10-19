/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.dao;

import com.ibothub.heap.shiro.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/15 21:48
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * 根据指定的用户名获取用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);
}
