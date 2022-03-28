/*
 * powered by http://ibothub.com
 */
package com.ibothub.heap.flowable.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;

/**
 * @author <a href="mailto:eko.zhan@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/2/26 10:27
 */
public class FlowableUtils {

    public static String getTitle(String processDefinitionName, String userName){
        return String.format("[%s] %s的%s", DateFormatUtils.format(Calendar.getInstance().getTime(), "yyyyMMddHHmmss"), userName, processDefinitionName);
    }
}
