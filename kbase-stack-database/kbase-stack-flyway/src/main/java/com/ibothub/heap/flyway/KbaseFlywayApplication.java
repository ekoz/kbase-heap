package com.ibothub.heap.flyway;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/9/20 17:51
 */
@SpringBootApplication
public class KbaseFlywayApplication {

  public static void main(String[] args) {
    SpringApplication.run(KbaseFlywayApplication.class, args);
  }

  /**
   * flyway 初始化伪代码
   */
  private static void flywaySetup(){

    // create configuration
    // 详见 org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
    ClassicConfiguration classicConfiguration = new ClassicConfiguration();

    /// setConfiguration ...
    // classicConfiguration.setDataSource(...);

    // create Flyway
    // 详见 org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration#flywayInitializer
    // 详见 org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer#afterPropertiesSet
    Flyway flyway = new Flyway(classicConfiguration);

    // do migrate
    flyway.migrate();
  }

}
