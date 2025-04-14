package com.ibothub.heap.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author eko.zhan
 * @version 1.0
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ibothub.heap.ai.dao")
public class KbaseAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KbaseAiApplication.class, args);
	}

}
