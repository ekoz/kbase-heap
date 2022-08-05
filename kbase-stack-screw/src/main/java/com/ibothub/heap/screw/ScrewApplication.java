package com.ibothub.heap.screw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/8/3 20:43
 */
@SpringBootApplication(scanBasePackages = {"com.ibothub.heap.screw", "com.ibothub.heap.base"})
public class ScrewApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrewApplication.class, args);
    }
}
