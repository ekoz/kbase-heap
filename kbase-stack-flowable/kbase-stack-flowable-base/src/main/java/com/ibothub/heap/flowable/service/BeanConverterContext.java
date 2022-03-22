package com.ibothub.heap.flowable.service;

import com.ibothub.heap.flowable.model.vo.TaskInstanceVO;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/3/22 12:10
 */
public interface BeanConverterContext {

    @AfterMapping
    void fillTaskInstanceVO(@MappingTarget TaskInstanceVO vo);

    @AfterMapping
    void fillTaskInstanceVO(@MappingTarget List<TaskInstanceVO> voList);
}
