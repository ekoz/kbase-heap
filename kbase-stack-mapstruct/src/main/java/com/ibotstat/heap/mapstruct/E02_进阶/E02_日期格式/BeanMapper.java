package com.ibotstat.heap.mapstruct.E02_进阶.E02_日期格式;

import com.ibotstat.heap.mapstruct.E02_进阶.E02_日期格式.vo.UserVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E02_日期格式.entity.User;
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

    @Mapping(source = "createDate", target = "createDate", dateFormat = "yyyy-MM-dd HH:mm")
    UserVO entityToVO(User user);

}
