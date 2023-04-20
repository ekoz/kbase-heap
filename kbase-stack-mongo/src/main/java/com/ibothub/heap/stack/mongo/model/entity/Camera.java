package com.ibothub.heap.stack.mongo.model.entity;

import com.ibothub.heap.stack.mongo.model.vo.CameraLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2023/4/18 14:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("camera")
public class Camera {

  @Id
  private String id;

  /**
   * 相机id
   */
  private String cameraId;

  /**
   * 相机名称
   */
  private String cameraName;

  /**
   * 相机点位坐标
   */
  @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
  private CameraLocation location;

}
