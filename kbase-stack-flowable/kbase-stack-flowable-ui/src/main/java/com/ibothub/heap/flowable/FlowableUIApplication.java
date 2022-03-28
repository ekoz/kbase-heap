/*
 * powered by http://ibothub.com
 */
package com.ibothub.heap.flowable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author <a href="mailto:eko.zhan@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/2/21 20:44
 */
//@Import({
//        ApplicationConfiguration.class,
//        AppDispatcherServletConfiguration.class
//})
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.ibothub.heap.base.config", "com.ibothub.heap.flowable"})
public class FlowableUIApplication {


    public static void main(String[] args) {
        SpringApplication.run(FlowableUIApplication.class, args);
    }

}
