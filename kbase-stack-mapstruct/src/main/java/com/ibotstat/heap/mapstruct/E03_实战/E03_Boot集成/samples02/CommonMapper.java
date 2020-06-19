package com.ibotstat.heap.mapstruct.E03_实战.E03_Boot集成.samples02;

import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:zhanzhao@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/6/19 19:54
 */
@Component
public class CommonMapper {

    Boolean convert(Integer num){
        return num==1;
    }

    Integer convert(Boolean b){
        return b?1:0;
    }


}
