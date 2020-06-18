package com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.vo.RoleVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.entity.Role;
import com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.entity.User;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 16:36
 */
public class BeanMapperTests {

    @Test
    public void testRoleEntityToDto(){
        List<User> userList = Lists.newArrayList();
        userList.add(User.builder()
            .name("小明")
            .age(12)
            .birthday(LocalDate.of(1990, 5, 1))
            .build());

        userList.add(User.builder()
                .name("小红")
                .age(18)
                .birthday(LocalDate.of(1992, 5, 1))
                .build());

        Role role = Role.builder()
                .name("管理员")
                .userList(userList)
                .build();

        RoleVO roleDto = BeanMapper.INSTANCE.entityToVO(role);
        System.out.println(JSON.toJSON(roleDto).toString());
    }
}
