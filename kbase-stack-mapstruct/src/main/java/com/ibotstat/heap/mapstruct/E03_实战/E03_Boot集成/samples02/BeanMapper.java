package com.ibotstat.heap.mapstruct.E03_实战.E03_Boot集成.samples02;

import com.ibotstat.heap.mapstruct.E03_实战.E03_Boot集成.samples02.entity.User;
import com.ibotstat.heap.mapstruct.E03_实战.E03_Boot集成.samples02.vo.UserVO;
import org.mapstruct.Mapper;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 16:18
 */
@Mapper(componentModel = "spring", uses = CommonMapper.class)
public interface BeanMapper {

    User forward(UserVO vo);
    UserVO backword(User bean);

}
