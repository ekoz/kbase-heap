package com.ibotstat.heap.mapstruct.E03_实战.E03_Boot集成.samples02;

import com.ibotstat.heap.mapstruct.E03_实战.E03_Boot集成.samples02.entity.User;
import com.ibotstat.heap.mapstruct.E03_实战.E03_Boot集成.samples02.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 16:18
 */
@Mapper(componentModel = "spring", uses = CommonMapper.class)
public interface BeanMapper {

    User forward(UserVO vo);

    @Mapping(source = "id", target = "nameCN", qualifiedByName = "getUsernameById")
    UserVO backward(User bean);


    /**
     * 根据指定的 userId 获取用户中文名
     * @param id
     * @return
     */
    @Named("getUsernameById")
    default String getUsernameById(String id){
        if (StringUtils.isBlank(id)){
            return "";
        }
        return "getUser(id).getNameCN()";
    }

}
