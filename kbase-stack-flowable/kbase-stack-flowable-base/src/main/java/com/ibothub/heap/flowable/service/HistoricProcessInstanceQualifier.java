package com.ibothub.heap.flowable.service;

import org.flowable.engine.HistoryService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/3/21 20:54
 */
@Component
public class HistoricProcessInstanceQualifier {

    @Resource
    HistoryService historyService;

    @Named("getTaskNameByHistoricTaskInstance")
    public String getTaskNameByHistoricTaskInstance(String processInstanceId){

        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

        return historicProcessInstance.getName();
    }
}
