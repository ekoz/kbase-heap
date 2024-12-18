package com.ibothub.heap.base.model.vo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibothub.heap.base.exception.ResultCode;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * wrapper for response entity
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2019-03-02 12:10
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "响应数据")
public class ResponseEntity<T> implements Serializable {

    /**
     * response result code, not http response code
     */
    @Schema(description = "响应码")
    private String code = "";

    /**
     * response result code meaning
     */
    @Schema(description = "响应码含义")
    private String message = "";

    /**
     * response result explanation, usually used to describe exception detail
     */
    @Schema(description = "响应码解释，通常用于描述异常细节")
    private String description = "";

    /**
     * response result data
     */
    @Schema(description = "响应主体")
    private T data;

    /**
     * 反序列化构造方法
     * @param json
     * @throws IOException
     */
    public ResponseEntity(String json) throws IOException {
        ResponseEntity responseEntity = new ObjectMapper().readValue(json, ResponseEntity.class);
        this.code = responseEntity.getCode();
        this.message = responseEntity.getMessage();
        this.description = responseEntity.getDescription();
        this.data = (T)responseEntity.getData();
    }

    /**
     * common success response entity
     */
    public static <T> ResponseEntity<T> ok() {
        return ok(null);
    }

    /**
     * common success response entity
     */
    public static <T> ResponseEntity<T> ok(T data) {
        return new ResponseEntity<T>().setCode(ResultCode.OK.code).setMessage(ResultCode.OK.message).setData(data);
    }

    /**
     * common failure response entity with detail failure message
     */
    public static ResponseEntity failure(String description) {
        return new ResponseEntity()
                .setCode(ResultCode.FAILURE.code)
                .setMessage(ResultCode.FAILURE.code)
                .setDescription(description);
    }

    /**
     * common failure response entity with ResultCode and detail explanation
     */
    public static ResponseEntity failure(ResultCode resultCode) {
        return new ResponseEntity().setCode(resultCode.code).setMessage(resultCode.message);
    }

    /**
     * common failure response entity with ResultCode and detail explanation
     */
    public static ResponseEntity failure(ResultCode resultCode, String description) {
        return new ResponseEntity().setCode(resultCode.code).setMessage(resultCode.message).setDescription(description);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}