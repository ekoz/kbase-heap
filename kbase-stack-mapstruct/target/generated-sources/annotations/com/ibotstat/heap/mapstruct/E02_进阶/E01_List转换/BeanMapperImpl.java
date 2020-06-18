package com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换;

import com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.entity.Role;
import com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.entity.User;
import com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.vo.RoleVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.vo.RoleVO.RoleVOBuilder;
import com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.vo.UserVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E01_List转换.vo.UserVO.UserVOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-17T14:08:41+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_201-2-redhat (Oracle Corporation)"
)
public class BeanMapperImpl implements BeanMapper {

    @Override
    public UserVO entityToVO(User user) {
        if ( user == null ) {
            return null;
        }

        UserVOBuilder userVO = UserVO.builder();

        userVO.id( user.getId() );
        userVO.name( user.getName() );
        userVO.age( user.getAge() );
        userVO.birthday( user.getBirthday() );

        return userVO.build();
    }

    @Override
    public RoleVO entityToVO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleVOBuilder roleVO = RoleVO.builder();

        roleVO.id( role.getId() );
        roleVO.name( role.getName() );
        roleVO.userList( userListToUserVOList( role.getUserList() ) );

        return roleVO.build();
    }

    protected List<UserVO> userListToUserVOList(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<UserVO> list1 = new ArrayList<UserVO>( list.size() );
        for ( User user : list ) {
            list1.add( entityToVO( user ) );
        }

        return list1;
    }
}
