package com.ibothub.heap.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author eko.zhan
 */
@SpringBootApplication
@MapperScan("com.ibothub.heap.shiro.dao")
public class ShiroBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroBootApplication.class, args);
    }

}
