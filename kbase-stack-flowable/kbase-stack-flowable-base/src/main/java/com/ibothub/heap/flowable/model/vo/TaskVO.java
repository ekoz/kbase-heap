package com.ibothub.heap.flowable.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/2/23 18:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskVO<T> implements Serializable {

    private String id;

    private String processInstanceId;

    private String name;

    private String description;

    private Integer priority;

    private String owner;

    private String assignee;

    private String createTime;

    private String taskName;

    private String startUserId;


    private T entity;

}
