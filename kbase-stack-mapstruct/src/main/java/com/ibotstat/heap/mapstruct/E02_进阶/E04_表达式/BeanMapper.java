package com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式;

import com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式.entity.Dept;
import com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式.vo.DeptVO;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.MapUtils;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
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
    @Mapping(target = "alias", source = "id", qualifiedByName = "getAlias")
    Dept voToEntity(DeptVO vo, @Context Map<String, String> deptAliasMap);

    List<Dept> voToEntityList(List<DeptVO> vo, @Context Map<String, String> deptAliasMap);

    @Named("getAlias")
    default String getAlias(String id, @Context Map<String, String> deptAliasMap){
        return MapUtils.getString(deptAliasMap, id, "Unknown");
    }
}
