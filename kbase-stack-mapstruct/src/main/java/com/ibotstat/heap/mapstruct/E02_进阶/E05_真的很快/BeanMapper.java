package com.ibotstat.heap.mapstruct.E02_进阶.E05_真的很快;

import com.ibotstat.heap.mapstruct.E02_进阶.E05_真的很快.vo.UserVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E05_真的很快.entity.User;
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

    List<UserVO> entityToVO(List<User> userList);

}
