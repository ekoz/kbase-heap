package com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参;

import com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.entity.Dept;
import com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.vo.req.DeptReq;
import com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.vo.resp.DeptResp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/17 14:00
 */
@Mapper
public interface BeanMapper {

    BeanMapper INSTANCE = Mappers.getMapper(BeanMapper.class);

    DeptResp entityToVO(Dept dept);

    Dept voToEntity(DeptReq vo);
}
