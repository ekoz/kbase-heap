package com.ibotstat.heap.mapstruct.E02_进阶.E05_真的很快;

import com.ibotstat.heap.mapstruct.E02_进阶.E05_真的很快.entity.User;
import com.ibotstat.heap.mapstruct.E02_进阶.E05_真的很快.vo.UserVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E05_真的很快.vo.UserVO.UserVOBuilder;
import java.time.format.DateTimeFormatter;
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
        if ( user.getBirthday() != null ) {
            userVO.birthday( DateTimeFormatter.ISO_LOCAL_DATE.format( user.getBirthday() ) );
        }
        userVO.sex( user.getSex() );
        userVO.email( user.getEmail() );
        userVO.phone( user.getPhone() );
        if ( user.getCreateDate() != null ) {
            userVO.createDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( user.getCreateDate() ) );
        }
        if ( user.getModifyDate() != null ) {
            userVO.modifyDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( user.getModifyDate() ) );
        }
        userVO.createUser( user.getCreateUser() );
        userVO.modifyUser( user.getModifyUser() );
        userVO.delFlag( user.getDelFlag() );

        return userVO.build();
    }

    @Override
    public List<UserVO> entityToVO(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserVO> list = new ArrayList<UserVO>( userList.size() );
        for ( User user : userList ) {
            list.add( entityToVO( user ) );
        }

        return list;
    }
}
