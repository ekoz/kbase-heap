package com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Maps;
import com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式.entity.Dept;
import com.ibotstat.heap.mapstruct.E02_进阶.E04_表达式.vo.DeptVO;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/15 19:29
 */
public class BeanMapperTests {

    private Map<String, String> deptAliasMap = Maps.newHashMap();

    @Before
    public void init(){
        deptAliasMap.put("ROOT", "研发中心");
        deptAliasMap.put("IBOTPLUS", "IBOT+");
        deptAliasMap.put("WUHAN", "武汉产品研发部");
        deptAliasMap.put("SHANGHAI", "上海产品研发部");
    }

    @Test
    public void testDtoToEntity(){

        DeptVO deptDto = DeptVO.builder()
            .id("WUHAN")
                .name("武汉产品研发部")
                .parentName("IBOTPLUS")
                .parentId("ROOT")
                .build();

        Dept dept = BeanMapper.INSTANCE.voToEntity(deptDto, null);
        System.out.println(JSON.toJSON(dept).toString());
    }

    @Test
    public void testQualifiedByName(){

        DeptVO deptDto = DeptVO.builder()
            .id("WUHAN")
            .parentName("IBOTPLUS")
            .parentId("ROOT")
            .build();

        Dept dept = BeanMapper.INSTANCE.voToEntity(deptDto, deptAliasMap);
        System.out.println(JSON.toJSON(dept).toString());
    }
}
