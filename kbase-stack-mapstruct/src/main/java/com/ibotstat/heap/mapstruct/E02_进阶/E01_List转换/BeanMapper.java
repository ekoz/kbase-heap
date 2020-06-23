package com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换;

import com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.vo.RoleVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.vo.UserVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.entity.Role;
import com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 16:18
 */
@Mapper
public interface BeanMapper {

    BeanMapper INSTANCE = Mappers.getMapper(BeanMapper.class);

    UserVO entityToVO(User user);

    RoleVO entityToVO(Role role);

    List<UserVO> entityToVO(List<User> userList);
}
