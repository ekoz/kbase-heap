package com.ibotstat.heap.mapstruct.E03_实战.E01_继承;

import com.ibotstat.heap.mapstruct.E03_实战.E01_继承.entity.User;
import com.ibotstat.heap.mapstruct.E03_实战.E01_继承.entity.User.UserBuilder;
import com.ibotstat.heap.mapstruct.E03_实战.E01_继承.vo.UserVO;
import com.ibotstat.heap.mapstruct.E03_实战.E01_继承.vo.UserVO.UserVOBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        if ( user.getModifyDate() != null ) {
            userVO.modifyDate( DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm" ).format( user.getModifyDate() ) );
        }
        userVO.id( user.getId() );
        userVO.createUser( user.getCreateUser() );
        userVO.modifyUser( user.getModifyUser() );
        if ( user.getCreateDate() != null ) {
            userVO.createDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( user.getCreateDate() ) );
        }
        userVO.name( user.getName() );
        if ( user.getAge() != null ) {
            userVO.age( String.valueOf( user.getAge() ) );
        }
        if ( user.getBirthday() != null ) {
            userVO.birthday( DateTimeFormatter.ISO_LOCAL_DATE.format( user.getBirthday() ) );
        }

        return userVO.build();
    }

    @Override
    public User voToEntity(UserVO vo) {
        if ( vo == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( vo.getId() );
        user.createUser( vo.getCreateUser() );
        user.modifyUser( vo.getModifyUser() );
        if ( vo.getCreateDate() != null ) {
            user.createDate( LocalDateTime.parse( vo.getCreateDate() ) );
        }
        if ( vo.getModifyDate() != null ) {
            user.modifyDate( LocalDateTime.parse( vo.getModifyDate() ) );
        }
        user.name( vo.getName() );
        if ( vo.getAge() != null ) {
            user.age( Integer.parseInt( vo.getAge() ) );
        }
        if ( vo.getBirthday() != null ) {
            user.birthday( LocalDate.parse( vo.getBirthday() ) );
        }

        return user.build();
    }
}
