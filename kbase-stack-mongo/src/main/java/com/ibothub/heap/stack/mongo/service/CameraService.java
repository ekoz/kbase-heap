package com.ibothub.heap.stack.mongo.service;

import com.ibothub.heap.stack.mongo.model.entity.Camera;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2023/4/19 15:26
 */
public interface CameraService {


  /**
   *
   * 给定一个坐标点位和半径，求小时空内的所有相机点位
   *
   * @param lng
   * @param lat
   * @param distance
   * @return
   */
  List<Camera> findByPoint(double lng, double lat, double distance);

  /**
   *
   * 给定一个坐标点位和半径，求小时空内的 limit 个相机点位
   *
   * @param lng
   * @param lat
   * @param distance
   * @param limit
   * @return
   */
  List<Camera> findByPoint(double lng, double lat, double distance, Long limit);

  /**
   *
   * 根据指定的点位坐标，角度，距离，获取该区域内的所有相机点位
   *
   * @param lng
   * @param lat
   * @param startBearing
   * @param distance
   * @return
   */
  List<Camera> findByCoordinates(double lng, double lat, double startBearing, double distance);

  /**
   *
   * 根据指定的点位坐标，角度，距离，获取该区域内的所有相机点位
   *
   * @param lng
   * @param lat
   * @param startBearing
   * @param angle
   * @param distance
   * @return
   */
  List<Camera> findByCoordinates(double lng, double lat, double startBearing, double angle, double distance);
}
