package com.ibothub.heap.flowable.web.controller;

import com.google.common.collect.Maps;
import com.ibothub.heap.base.model.vo.ResponseEntity;
import com.ibothub.heap.flowable.model.BeanConverter;
import com.ibothub.heap.flowable.model.vo.ProcessInstanceVO;
import com.ibothub.heap.flowable.model.vo.TaskInstanceVO;
import com.ibothub.heap.flowable.model.vo.TaskVO;
import com.ibothub.heap.flowable.util.FlowableUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/3/10 20:41
 */
@Api(tags = "工作流流转接口")
@RestController
@RequestMapping("/api/flowable")
@Slf4j
public class FlowableController {

    private final String processDefinitionKey = "LeaveApply20220302";

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
    BeanConverter beanConverter;


    @ApiOperation("发起流程")
    @PostMapping("start")
    public ResponseEntity<String> start(String userId, String deptLeader) {
        // 设置流程发起人，流程发起人默认是从 security 框架中获取的，可以在这里设置下
        Authentication.setAuthenticatedUserId(userId);

        // 获取 processDefinition
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)
                .latestVersion()
                .singleResult();

        //启动流程
        Map<String, Object> map = Maps.newHashMap();
        map.put("deptLeader", deptLeader);

        ProcessInstance processInstance = runtimeService.createProcessInstanceBuilder()
                .name(FlowableUtils.getTitle(processDefinition.getName(), userId))
                .processDefinitionKey(processDefinitionKey)
                .variables(map)
                .start();

        return ResponseEntity.ok(String.format("提交成功.流程实例Id为：%s", processInstance.getId()));
    }

    /**
     * 批准
     *
     * @param taskId 任务ID
     */
    @ApiOperation("继续流转，通过或拒绝")
    @PostMapping(value = "apply")
    public ResponseEntity apply(String taskId, Integer acceptType, String deptMgr) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程任务不存在");
        }

        HashMap<String, Object> map = Maps.newHashMap();
        map.put("acceptType", acceptType);
        map.put("deptMgr", deptMgr);

        taskService.complete(taskId, map);
        return ResponseEntity.ok();
    }


    @ApiOperation("查询指定用户的待办事项")
    @GetMapping(value = "/listTodo")
    public ResponseEntity<List<TaskVO>> listTodo(String userId){

        List<Task> list = taskService
                .createTaskQuery()
                .taskAssignee(userId)
                .orderByTaskCreateTime()
                .desc()
                .list();

        List<TaskVO> voList = beanConverter.forwardTask(list);
        return ResponseEntity.ok(voList);
    }

    @ApiOperation("查询指定用户的已办事项")
    @GetMapping(value = "/listDone")
    public ResponseEntity<List<TaskInstanceVO>> listDone(String userId){

        List<HistoricTaskInstance> list = historyService
                .createHistoricTaskInstanceQuery()
                .taskAssignee(userId)
                .finished()
                .list();

        log.debug("总共处理 {} 条数据", list.size());

        List<TaskInstanceVO> taskInstanceList = beanConverter.forwardTaskInstance(list);
        return ResponseEntity.ok(taskInstanceList);
    }

    @ApiOperation("查询指定用户的发起事项")
    @GetMapping(value = "/listCreated")
    public ResponseEntity<List<ProcessInstanceVO>> listCreated(String userId){

        List<HistoricProcessInstance> list = historyService
                .createHistoricProcessInstanceQuery()
                .startedBy(userId)
//                .finished()
                .list();

        log.debug("总共发起 {} 条数据", list.size());

        List<ProcessInstanceVO> processInstanceList = beanConverter.forwardProcessInstance(list);
        return ResponseEntity.ok(processInstanceList);
    }

}
