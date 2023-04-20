package com.ibothub.heap.stack.mongo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2023/4/18 14:07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CameraLocation {

  /**
   * https://datatracker.ietf.org/doc/html/rfc7946#section-3.1.1
   */
  private String type;

  /**
   *
   */
  private double[] coordinates;

}
