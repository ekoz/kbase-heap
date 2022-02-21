/*
 * powered by http://ibothub.com
 */
package com.ibothub.heap.flowable.service;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:eko.zhan@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/2/24 20:46
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RepositoryServiceTests {

    @Resource
    RepositoryService repositoryService;



    @Test
    public void testGetProcessDefinition(){

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("Expense")
                .latestVersion()
                .singleResult();


        System.out.println(processDefinition.getName());
    }


}
