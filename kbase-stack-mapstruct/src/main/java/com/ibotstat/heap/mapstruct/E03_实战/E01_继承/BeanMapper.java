package com.ibotstat.heap.mapstruct.E03_实战.E01_继承;


import com.ibotstat.heap.mapstruct.E03_实战.E01_继承.vo.UserVO;
import com.ibotstat.heap.mapstruct.E03_实战.E01_继承.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 16:18
 */
@Mapper
public interface BeanMapper {

    BeanMapper INSTANCE = Mappers.getMapper(BeanMapper.class);

    @Mapping(source = "modifyDate", target = "modifyDate", dateFormat = "yyyy-MM-dd HH:mm")
    UserVO entityToVO(User user);

    User voToEntity(UserVO vo);
}
