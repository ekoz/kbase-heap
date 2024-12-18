/*
 * powered by http://ibothub.com
 */
package com.ibothub.heap.flowable.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ibothub.heap.base.model.vo.ResponseEntity;
import com.ibothub.heap.flowable.model.BeanConverter;
import com.ibothub.heap.flowable.model.entity.Expense;
import com.ibothub.heap.flowable.model.vo.ProcessInstanceVO;
import com.ibothub.heap.flowable.model.vo.TaskVO;
import com.ibothub.heap.flowable.service.BeanConverterContext;
import com.ibothub.heap.flowable.util.FlowableUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.*;
//import org.flowable.engine.common.impl.identity.Authentication;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:eko.zhan@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/2/21 21:50
 */
@Tag(name = "费用报销")
@RestController
@RequestMapping("/api/expense")
@Slf4j
public class ExpenseController {

    private final String processDefinitionKey = "Expense";

    @Resource
    RuntimeService runtimeService;

    @Resource
    ProcessEngine processEngine;

    @Resource
    RepositoryService repositoryService;

    @Resource
    TaskService taskService;

    @Resource
    HistoryService historyService;

    @Resource
    BeanConverter beanConverter;

    @Resource
    BeanConverterContext beanConverterContext;

    @Operation(summary = "test")
    @PostMapping("test")
    public ResponseEntity<String> test() {

        taskService.getVariables("37509").forEach((k, v)->{
            log.debug("{} -> {}", k, v);
        });

        return ResponseEntity.ok();
    }

    @Operation(summary = "发起流程")
    @PostMapping("start")
    public ResponseEntity<String> start(String userId, Double money, String summary) {


        // 设置流程发起人，流程发起人默认是从 security 框架中获取的，可以在这里设置下
        Authentication.setAuthenticatedUserId(userId);

        // 获取 processDefinition
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)
                .latestVersion()
                .singleResult();

        //启动流程
        Expense expense = Expense.builder()
                .money(money)
                .title(FlowableUtils.getTitle(processDefinition.getName(), userId))
                .summary(summary)
                .taskUser(userId)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(expense, Map.class);

        ProcessInstance processInstance = runtimeService.createProcessInstanceBuilder()
                .name(expense.getTitle())
                .processDefinitionKey(processDefinitionKey)
                .variables(map)
                .start();


        // Expense 对应的是 processes/ 下 bmpm20.xml 文件的 process id
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, map);
        return ResponseEntity.ok(String.format("提交成功.流程实例Id为：%s", processInstance.getId()));
    }

    @Operation(summary = "查询所有流程实例")
    @GetMapping(value = "/historyList")
    public ResponseEntity<List<ProcessInstanceVO>> historyList(String userId){


        List<HistoricProcessInstance> list = historyService
                .createHistoricProcessInstanceQuery()
                .startedBy(userId)
                .orderByProcessInstanceStartTime()
                .desc()
                .list();

        list.forEach(historicProcessInstance -> {
            System.out.println(historicProcessInstance.getName());
        });

        return ResponseEntity.ok(beanConverter.forwardProcessInstance(list));
    }

    @Operation(summary = "查询指定用户的待办事项")
    @GetMapping(value = "/taskList")
    public ResponseEntity<List<TaskVO>> taskList(String userId){

        List<Task> list = taskService
                .createTaskQuery()
                .taskAssignee(userId)
                .orderByTaskCreateTime()
                .desc()
                .list();

        List<TaskVO> voList = Lists.newArrayList();
        voList = beanConverter.forwardTask(list, voList, beanConverterContext);

        voList.forEach(taskVO -> {
            Map<String, Object> variables = taskService.getVariables(taskVO.getId());
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Expense expense = objectMapper.readValue(objectMapper.writeValueAsString(variables), Expense.class);
                taskVO.setEntity(expense);
            } catch (JsonProcessingException e) {
                log.warn("[{}] 中的变量转换成实体类失败，原因：{}", taskVO.getId(), e.getMessage());
            }
        });

        return ResponseEntity.ok(voList);
    }

    /**
     * 批准
     *
     * @param taskId 任务ID
     */
    @Operation(summary = "通过")
    @PostMapping(value = "apply")
    public ResponseEntity apply(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程不存在");
        }
        //通过审核
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("acceptType", 1);
        taskService.complete(taskId, map);
        return ResponseEntity.ok();
    }

    /**
     * 拒绝
     */
    @Operation(summary = "拒绝")
    @PostMapping(value = "reject")
    public ResponseEntity reject(String taskId) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("acceptType", 0);
        taskService.complete(taskId, map);
        return ResponseEntity.ok();
    }

    /**
     * 删除所有流程实例
     * @return
     */
    @Operation(summary = "删除所有流程实例")
    @DeleteMapping(value="deleteAll")
    public ResponseEntity deleteAll(){

        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
        System.out.println(String.format("正在流转的流程实例有 %d 条", list.size()));

        List<HistoricProcessInstance> historicList = historyService.createHistoricProcessInstanceQuery().list();
        System.out.println(String.format("流转完毕的流程实例有 %d 条", historicList.size()));

        for (HistoricProcessInstance instance : historicList){
            historyService.deleteHistoricProcessInstance(instance.getId());
        }

        log.debug("流程实例删除完毕");

        return ResponseEntity.ok();
    }

}
