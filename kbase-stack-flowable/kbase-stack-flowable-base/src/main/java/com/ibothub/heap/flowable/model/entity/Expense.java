/*
 * powered by http://ibothub.com
 */
package com.ibothub.heap.flowable.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 财务报销表单字段
 * @author <a href="mailto:eko.zhan@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/2/26 10:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 任务发起人
     */
    private String taskUser;

    /**
     * 报销事由
     */
    private String summary;

    /**
     * 报销金额
     */
    private Double money;

}
