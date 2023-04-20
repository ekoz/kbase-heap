package com.ibothub.heap.stack.mongo.service;

import com.ibothub.heap.stack.mongo.model.entity.Camera;
import java.io.IOException;
import java.util.List;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2023/4/18 15:54
 */
public interface BaiduMapService {

  /**
   * 批量插入所有数据
   */
  void insert();

  /**
   * 爬取百度地图上武汉的地铁点位，存储至 camera 表中
   *
   * @param cityCode 百度地图返回的城市代码
   * @return List<Camera>
   * @throws IOException
   */
  List<Camera> loadCameras(int cityCode) throws IOException;

  /**
   * 爬取百度地图上所有地铁点位，存储至 camera 表中
   * @return List<Camera>
   * @throws IOException
   */
  List<Camera> loadAllCameras() throws IOException;
}
