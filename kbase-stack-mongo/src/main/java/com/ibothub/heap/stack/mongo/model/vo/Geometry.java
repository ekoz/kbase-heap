package com.ibothub.heap.stack.mongo.model.vo;

import java.util.List;
import lombok.Data;
import org.gavaghan.geodesy.GlobalCoordinates;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2023/4/18 18:06
 */
@Data
public class Geometry {
  /**
   * https://datatracker.ietf.org/doc/html/rfc7946#section-3.1.1
   */
  private String type;

  /**
   *
   */
  private List<GlobalCoordinates> coordinates;
}
