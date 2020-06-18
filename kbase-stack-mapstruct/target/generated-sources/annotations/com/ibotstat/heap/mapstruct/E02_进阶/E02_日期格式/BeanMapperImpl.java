package com.ibotstat.heap.mapstruct.E02_进阶.E02_日期格式;

import com.ibotstat.heap.mapstruct.E02_进阶.E02_日期格式.entity.User;
import com.ibotstat.heap.mapstruct.E02_进阶.E02_日期格式.vo.UserVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E02_日期格式.vo.UserVO.UserVOBuilder;
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

        if ( user.getCreateDate() != null ) {
            userVO.createDate( DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm" ).format( user.getCreateDate() ) );
        }
        userVO.id( user.getId() );
        userVO.name( user.getName() );
        userVO.age( user.getAge() );
        if ( user.getBirthday() != null ) {
            userVO.birthday( DateTimeFormatter.ISO_LOCAL_DATE.format( user.getBirthday() ) );
        }

        return userVO.build();
    }
}
