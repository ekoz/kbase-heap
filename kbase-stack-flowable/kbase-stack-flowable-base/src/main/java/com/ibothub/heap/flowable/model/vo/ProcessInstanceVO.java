package com.ibothub.heap.flowable.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/2/24 17:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessInstanceVO implements Serializable {

    private String id;

    private String startTime;

    private String endTime;

    private Long durationInMillis;

    private String startUserId;

    private String name;

    private String processDefinitionKey;


}
