package com.ibothub.heap.flowable.service.impl;

import com.ibothub.heap.flowable.model.vo.TaskInstanceVO;
import com.ibothub.heap.flowable.model.vo.TaskVO;
import com.ibothub.heap.flowable.service.BeanConverterContext;
import org.flowable.engine.HistoryService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/3/22 12:12
 */
@Component
public class BeanConverterContextImpl implements BeanConverterContext {

    @Resource
    HistoryService historyService;

    @Override
    public void fillTaskInstanceVO(TaskInstanceVO vo) {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(vo.getProcessInstanceId())
                .singleResult();

        vo.setTaskName(historicProcessInstance.getName());
        vo.setStartUserId(historicProcessInstance.getStartUserId());
    }

    @Override
    public void fillTaskInstanceVO(List<TaskInstanceVO> voList) {
        voList.forEach(this::fillTaskInstanceVO);
    }

    @Override
    public void fillTaskVO(TaskVO vo) {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(vo.getProcessInstanceId())
                .singleResult();

        vo.setTaskName(historicProcessInstance.getName());
        vo.setStartUserId(historicProcessInstance.getStartUserId());
    }

    @Override
    public void fillTaskVO(List<TaskVO> voList) {
        voList.forEach(this::fillTaskVO);
    }

}
