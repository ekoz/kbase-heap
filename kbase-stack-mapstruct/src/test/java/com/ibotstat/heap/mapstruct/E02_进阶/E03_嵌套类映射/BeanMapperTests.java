package com.ibotstat.heap.mapstruct.E02_进阶.E03_嵌套类映射;

import com.alibaba.fastjson2.JSON;
import com.ibotstat.heap.mapstruct.E02_进阶.E03_嵌套类映射.vo.DeptVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E03_嵌套类映射.entity.Dept;
import org.junit.Test;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 17:45
 */
public class BeanMapperTests {

    @Test
    public void testDtoToEntity(){

        DeptVO deptDto = DeptVO.builder()
                .name("武汉产品研发部")
                .parentName("IBOTPLUS")
                .parentId("ROOT")
                .build();

        Dept dept = BeanMapper.INSTANCE.voToEntity(deptDto);
        System.out.println(JSON.toJSON(dept).toString());
    }

    @Test
    public void testEntityToDto(){
        Dept dept = Dept.builder()
                .name("武汉产品研发部")
                .parent(
                        Dept.builder()
                        .name("IBOTPLUS")
                        .build()
                )
                .build();

        DeptVO deptDto = BeanMapper.INSTANCE.entityToVO(dept);
        System.out.println(deptDto.toString());
    }
}
