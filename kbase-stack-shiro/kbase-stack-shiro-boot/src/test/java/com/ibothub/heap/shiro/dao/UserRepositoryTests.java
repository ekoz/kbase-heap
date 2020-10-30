/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ibothub.heap.shiro.model.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/15 21:49
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTests {

    @Resource
    UserMapper userMapper;

    @Test
    public void testFind(){
        userMapper.selectList(null).forEach(user -> System.out.println(user.toString()));
    }

    @Test
    public void testSave(){
        User user = User.builder()
                .username("ekoz1")
                .password("ekoz88")
                .age(18)
                .sex(0)
                .usernameCN("展昭1")
                .build();
        userMapper.insert(user);
    }

    @Test
    public void testSelect(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(user -> System.out.println(user.toString()));

    }

    @Test
    public void testSelectByMap(){
        Map<String, Object> map = Maps.newHashMap();
        // java.sql.SQLSyntaxErrorException: Unknown column 'usernameCN' in 'where clause'
//        map.put("usernameCN", "展昭");
        map.put("username_cn", "展昭");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(user -> System.out.println(user.toString()));
    }

    @Test
    public void testSelectByWrapper(){
//        QueryWrapper<User> query = Wrappers.query();
////        query.like("username_cn", "展");
//        query.likeRight("username_cn", "展");

        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery(User.class)
                .likeRight(User::getUsernameCN, "展")
                .orderByAsc(User::getUsernameCN);

        List<User> users = userMapper.selectList(query);
        users.forEach(user -> System.out.println(user.toString()));
    }

    @Test
    public void testSelectExcludeField(){
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery(User.class).select(User::getId, User::getUsername, User::getUsernameCN)
                .likeRight(User::getUsernameCN, "展");

        List<User> users = userMapper.selectList(query);
        users.forEach(user -> System.out.println(user.toString()));
    }

    @Test
    public void testSelectByCondition(){
        String username = "展";
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery(User.class)
                .like(StringUtils.isNotBlank(username), User::getUsernameCN, username);
        List<User> users = userMapper.selectList(query);
        users.forEach(user -> System.out.println(user.toString()));

    }

    @Test
    public void testSelectByXml(){
        List<User> users = userMapper.selectByRoleIds();
        users.forEach(user -> System.out.println(user.toString()));
    }

    @Test
    public void testSelectPages(){
        QueryWrapper<User> query = Wrappers.query();
//        query.lt("id", 2);
        Page<User> userPage = new Page<>(1, 2);

        IPage<User> result = userMapper.selectPage(userPage, query);

        System.out.println(result.getSize());
        System.out.println(result.getPages());

        List<User> users = result.getRecords();
        users.forEach(user -> System.out.println(user.toString()));

    }

    @Test
    public void testDelete(){
//        int i = userMapper.deleteById(5);
//        System.out.println(i);
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class);
        queryWrapper.eq(User::getUsername, "ekoz1");
        int i = userMapper.delete(queryWrapper);
        System.out.println(i);


    }

}
