package com.ibothub.heap.stack.mongo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.ibothub.heap.stack.mongo.dao.CameraRepository;
import com.ibothub.heap.stack.mongo.model.entity.Camera;
import com.ibothub.heap.stack.mongo.model.vo.CameraLocation;
import com.ibothub.heap.stack.mongo.service.BaiduMapService;
import com.ibothub.heap.stack.mongo.util.BaiduMokatorToLngLat;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import jakarta.annotation.Resource;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2023/4/18 15:55
 */
@Service
public class BaiduMapServiceImpl implements BaiduMapService {


  @Resource
  CameraRepository cameraRepository;

  /**
   * 批量插入所有数据
   */
  @Override
  public void insert() {

    try {
      List<Camera> cameras = loadAllCameras();
      cameraRepository.deleteAll();
      cameraRepository.saveAll(cameras);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Camera> loadCameras(int cityCode) throws IOException {
    // http://map.baidu.com/?qt=subwayscity
    // http://map.baidu.com/?qt=bsi&c=218

    List<Camera> cameraList = Lists.newArrayList();

    OkHttpClient okHttpClient = new OkHttpClient();
    final Request request = new Request.Builder()
        .url("http://map.baidu.com/?qt=bsi&c=" + cityCode)
        .get()//默认就是GET请求，可以不写
        .build();
    Call call = okHttpClient.newCall(request);
    Response execute = call.execute();

    if (Objects.nonNull(execute.body())){
      JSONObject result = JSON.parseObject(execute.body().string());
      JSONArray arr = result.getJSONArray("content");

      if (Objects.nonNull(arr)) {
        for (int i=0;i<arr.size();i++){
          JSONObject jsonObject = arr.getJSONObject(i);
          String lineName = jsonObject.getString("line_name");
          JSONArray stops = jsonObject.getJSONArray("stops");

          for (int j=0;j<stops.size();j++){
            JSONObject stop = stops.getJSONObject(j);

            double x = stop.getDouble("x");
            double y = stop.getDouble("y");
            String name = stop.getString("name");

            CameraLocation cameraLocation = CameraLocation.builder()
                .type("Point")
                .coordinates(convert(x, y))
                .build();

            Camera camera = Camera.builder()
                .cameraName(lineName + "_" + name)
                .location(cameraLocation)
                .build();

            cameraList.add(camera);
          }
        }
      }
    }

    return cameraList;
  }

  /**
   * 爬取百度地图上所有地铁点位，存储至 camera 表中，共 39025 条数据
   */
  @Override
  public List<Camera> loadAllCameras() throws IOException {
    List<Camera> cameraList = Lists.newArrayList();

    Response response = doGet("http://map.baidu.com/?qt=subwayscity");

    if (Objects.nonNull(response.body())) {
      JSONObject result = JSON.parseObject(response.body().string());
      JSONArray cities = result.getJSONObject("subways_city").getJSONArray("cities");

      for (int i=0;i<cities.size();i++){
        int cityCode = cities.getJSONObject(i).getInteger("code");
        cameraList.addAll(loadCameras(cityCode));
      }
    }

    return cameraList;

  }

  private Response doGet(String url) throws IOException {
    OkHttpClient okHttpClient = new OkHttpClient();
    Request request = new Request.Builder()
        .url(url)
        .get()//默认就是GET请求，可以不写
        .build();
    Call call = okHttpClient.newCall(request);
    return call.execute();
  }


  /**
   * 百度地图坐标转换
   * @param x
   * @param y
   * @return
   */
  private double[] convert(double x, double y){
    Map<String, Double> coordinates = BaiduMokatorToLngLat.convertMC2LL(x, y);
    return new double[]{coordinates.get("lng"), coordinates.get("lat")};
  }

}
