/*
 * powered by http://ibothub.com
 */
package com.ibothub.heap.flowable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto:eko.zhan@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/2/21 20:44
 */
@SpringBootApplication(scanBasePackages = {"com.ibothub.heap.base.config", "com.ibothub.heap.flowable"})
public class FlowableAlphaApplication {


    public static void main(String[] args) {
        SpringApplication.run(FlowableAlphaApplication.class, args);
    }

}
