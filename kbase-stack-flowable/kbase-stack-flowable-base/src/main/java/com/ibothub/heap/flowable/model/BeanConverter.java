package com.ibothub.heap.flowable.model;

import com.ibothub.heap.flowable.model.vo.ProcessInstanceVO;
import com.ibothub.heap.flowable.model.vo.TaskInstanceVO;
import com.ibothub.heap.flowable.model.vo.TaskVO;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/2/23 18:04
 */
@Mapper(componentModel = "spring")
public interface BeanConverter {

    @Mapping(source = "createTime", target="createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    TaskVO forwardTask(Task entity);

    List<TaskVO> forwardTask(List<Task> list);

    @Mapping(source = "startTime", target="startTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "endTime", target="endTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ProcessInstanceVO forwardProcessInstance(HistoricProcessInstance entity);

    List<ProcessInstanceVO> forwardProcessInstance(List<HistoricProcessInstance> list);

    @Mapping(source = "startTime", target="startTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "endTime", target="endTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    TaskInstanceVO forwardTaskInstance(HistoricTaskInstance entity);

    List<TaskInstanceVO> forwardTaskInstance(List<HistoricTaskInstance> list);

}
