package com.ibothub.heap.flowable.service;

import com.google.common.collect.Maps;
import com.ibothub.heap.flowable.config.FlowableConstants;
import com.ibothub.heap.flowable.model.BeanConverter;
import com.ibothub.heap.flowable.model.vo.TaskInstanceVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.assertj.core.util.Lists;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/3/9 19:27
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class TaskServiceTests {

    @Resource
    TaskService taskService;

    @Resource
    RuntimeService runtimeService;

    @Resource
    RepositoryService repositoryService;

    @Resource
    ProcessEngine processEngine;

    @Resource
    HistoryService historyService;

    @Resource
    BeanConverterContext beanConverterContext;

    @Resource
    BeanConverter beanConverter;


    /**
     * 我发起的
     */
    @Test
    public void testMyStarted(){

        List<HistoricProcessInstance> list = historyService
                .createHistoricProcessInstanceQuery()
                .startedBy(FlowableConstants.ADMIN)
//                .finished()
                .list();

        log.debug("我发起的流程有 {} 条", list.size());

        list.forEach(historicProcessInstance -> {
            log.debug("{}", historicProcessInstance.getName());
        });

    }

    /**
     * 我的已办
     */
    @Test
    public void testMyFinished(){
        List<HistoricTaskInstance> list = historyService
                .createHistoricTaskInstanceQuery()
                .taskAssignee("liubei")
                .finished()
                .list();

        log.debug("已办事项有 {} 条", list.size());

        list.forEach(historicTaskInstance -> {
            log.debug("{}, {}, {}", historicTaskInstance.getDurationInMillis(), historicTaskInstance.getWorkTimeInMillis(), historicTaskInstance.getDescription());
        });

        List<TaskInstanceVO> voList = Lists.newArrayList();
        voList = beanConverter.forwardTaskInstance(list, voList, beanConverterContext);

        voList.forEach(System.out::println);
    }

    /**
     * 继续流转
     */
    @Test
    public void testComplete(){
        String taskId = "2f149c62-9fa6-11ec-bf38-0250f2000002";

        Map<String, Object> map = Maps.newHashMap();

        map.put("deptMgr", "liubei");
        map.put("acceptType", "1");
        taskService.complete(taskId, map);

    }

    /**
     * 获取我的待办
     */
    @Test
    public void testTodoList(){

        String processDefinitionKey = "LeaveApply20220302";

        List<Task> list = taskService.createTaskQuery()
                // 我的待办
                .taskAssignee("liubei")
                // 按照流程分类，查找指定流程的待办事项
                .processDefinitionKey(processDefinitionKey)
                .list();
        list.forEach(task -> {
            // task.getId 有时候会 equals task.getProcessInstanceId
            // 2f149c62-9fa6-11ec-bf38-0250f2000002 -> af9b8e43-9f9a-11ec-adf6-0250f2000002
            log.debug("{} -> {} -> {} -> {} -> {} -> {}", task.getId(), task.getProcessInstanceId(), task.getName(), task.getAssignee(), task.getOwner(), task.getTaskDefinitionKey());
        });
    }

    /**
     * 发起流程
     */
    @Test
    public void testStart(){
        // 设置流程发起人，流程发起人默认是从 security 框架中获取的，可以在这里设置下
        Authentication.setAuthenticatedUserId(FlowableConstants.ADMIN);

        String processDefinitionKey = "LeaveApply20220302";

        // 获取 processDefinition
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)
                .latestVersion()
                .singleResult();
        System.out.println(processDefinition.getName());

        //启动流程
        Map<String, Object> map = Maps.newHashMap();
        map.put("deptLeader", "guanyu");

        ProcessInstance processInstance = runtimeService.createProcessInstanceBuilder()
                .name("请假申请流程")
                .processDefinitionKey(processDefinitionKey)
                .variables(map)
                .start();

        System.out.println(processInstance.getId());
    }



    /**
     * 生成流程图
     * @throws IOException
     */
    @Test
    public void testProcessDiagram() throws IOException {
        String processInstanceId = "af9b8e43-9f9a-11ec-adf6-0250f2000002";

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

        if (processInstance==null){
            log.debug("流程实例已结束，无法输出流程图");
            return ;
        }

        // 获取任务
        Task task = taskService
                .createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

        // 使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        List<Execution> executionList = runtimeService
                .createExecutionQuery()
                .processInstanceId(processInstanceId)
                .list();

        // 得到正在执行的Activity的Id
        List<String> activityList = Lists.newArrayList();
        for (Execution execution : executionList) {
            List<String> ids = runtimeService.getActiveActivityIds(execution.getId());
            activityList.addAll(ids);
        }

        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        ProcessEngineConfiguration engineConfig = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engineConfig.getProcessDiagramGenerator();
        List<String> flowList = Lists.newArrayList();

        OutputStream out = new FileOutputStream("D:/" + task.getId() + ".png");
        InputStream in = diagramGenerator.generateDiagram(bpmnModel,
                "png", activityList, flowList,
                engineConfig.getActivityFontName(), engineConfig.getLabelFontName(),
                engineConfig.getAnnotationFontName(), engineConfig.getClassLoader(),
                1.0, Boolean.TRUE);

        IOUtils.copyLarge(in, out);

    }
}
