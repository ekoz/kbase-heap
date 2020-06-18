package com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参;

import com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.entity.Dept;
import com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.service.DeptService;
import com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.vo.req.DeptReq;
import com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.vo.resp.DeptResp;
import org.junit.Test;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/17 14:05
 */
public class BeanMapperTests {

    @Test
    public void testEntityToVO(){
        Dept parent = Dept.builder()
                .name("IBOTPLUS")
                .id("ROOT")
                .build();

        Dept dept = Dept.builder()
                .name("武汉产品开发部")
                .id("wuhan")
                .parent(parent)
                .build();

        DeptResp deptResp = BeanMapper.INSTANCE.entityToVO(dept);
        System.out.println(deptResp.toString());
    }

    @Test
    public void testVOToEntity(){
        DeptReq deptReq = DeptReq.builder()
                .name("武汉产品开发部")
                .parentId("ROOT")
                .build();


        DeptService deptService = new DeptService();

        Dept parent = deptService.findOne(deptReq.getParentId());

        Dept dept = BeanMapper.INSTANCE.voToEntity(deptReq);
        dept.setParent(parent);
        deptService.save(dept);

        System.out.println(dept.toString());

    }
}
