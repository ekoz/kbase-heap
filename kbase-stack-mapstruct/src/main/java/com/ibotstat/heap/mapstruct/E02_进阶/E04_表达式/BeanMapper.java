package com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式;

import com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式.vo.DeptVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式.entity.Dept;
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

    @Mapping(target = "parent", expression = "java(Dept.builder().id(vo.getParentId()).name(vo.getParentName()).build())")
    Dept voToEntity(DeptVO vo);

}
