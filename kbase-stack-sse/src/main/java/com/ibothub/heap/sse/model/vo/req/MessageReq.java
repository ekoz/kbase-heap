/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.sse.model.vo.req;

import java.io.Serializable;
import lombok.Data;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2024/12/18 15:12
 */
@Data
public class MessageReq implements Serializable {

  private String message;
}
