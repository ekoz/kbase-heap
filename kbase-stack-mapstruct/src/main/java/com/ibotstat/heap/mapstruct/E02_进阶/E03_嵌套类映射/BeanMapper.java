package com.ibotstat.heap.mapstruct.E02_进阶.E03_嵌套类映射;

import com.ibotstat.heap.mapstruct.E02_进阶.E03_嵌套类映射.vo.DeptVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E03_嵌套类映射.entity.Dept;
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

    @Mapping(source = "parent.id", target = "parentId", defaultValue = "ROOT")
    @Mapping(source = "parent.name", target = "parentName")
    DeptVO entityToVO(Dept dept);

    @Mapping(source = "parentId", target = "parent.id")
    @Mapping(source = "parentName", target = "parent.name")
    Dept voToEntity(DeptVO vo);

}
