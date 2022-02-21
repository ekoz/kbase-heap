/*
 * powered by http://ibothub.com
 */
package com.ibothub.heap.flowable.service;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:eko.zhan@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/2/26 10:21
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TaskServiceTests {

    @Resource
    TaskService taskService;

    @Test
    public void testGetVariables(){
        taskService.getVariables("37509").forEach((k, v)->{
            log.debug("{} -> {}", k, v);
        });
    }
}
