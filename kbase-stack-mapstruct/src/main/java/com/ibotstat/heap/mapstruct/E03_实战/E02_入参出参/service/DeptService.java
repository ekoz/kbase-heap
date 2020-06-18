package com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.service;

import com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.entity.Dept;

import java.util.UUID;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/17 14:12
 */
public class DeptService {

    public Dept findOne(String id){
        return Dept.builder().name("IBOTPLUS").id("ROOT").build();
    }

    public String save(Dept dept) {
        return UUID.randomUUID().toString();
    }
}
