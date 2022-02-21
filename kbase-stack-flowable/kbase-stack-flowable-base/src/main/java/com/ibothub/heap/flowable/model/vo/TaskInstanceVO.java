/*
 * powered by http://ibothub.com
 */
package com.ibothub.heap.flowable.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * HistoricTaskInstance
 * @author <a href="mailto:eko.zhan@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/3/14 21:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskInstanceVO implements Serializable {


    private String id;

    private String startTime;

    private String endTime;

    private Long durationInMillis;

    private String startUserId;

    private String name;

    private String processDefinitionKey;

}
