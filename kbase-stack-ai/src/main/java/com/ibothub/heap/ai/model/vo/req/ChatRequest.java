/*
 * powered by https://ekozhan.com
 */
package com.ibothub.heap.ai.model.vo.req;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @since 2025/4/11 15:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest implements Serializable {
  private String message;
}
