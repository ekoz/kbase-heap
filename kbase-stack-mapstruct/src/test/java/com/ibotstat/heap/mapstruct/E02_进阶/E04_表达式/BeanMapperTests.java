package com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式;

import com.alibaba.fastjson.JSON;
import com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式.vo.DeptVO;
import com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式.entity.Dept;
import org.junit.Test;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 19:29
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
}
