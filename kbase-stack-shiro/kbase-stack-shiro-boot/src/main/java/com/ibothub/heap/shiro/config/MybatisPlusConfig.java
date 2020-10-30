/*
 * powered by https://zhengxinacc.com
 */
package com.ibothub.heap.shiro.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/10/30 21:54
 */
@Configuration
public class MybatisPlusConfig {

//    @Bean
//    public PaginationInnerInterceptor paginationInnerInterceptor(){
//        return new PaginationInnerInterceptor();
//    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
