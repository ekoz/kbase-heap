package com.ibothub.heap.stack.mongo.service.impl;

import com.ibothub.heap.stack.mongo.model.entity.Camera;
import com.ibothub.heap.stack.mongo.service.CameraService;
import com.ibothub.heap.stack.mongo.util.PolygonUtil;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2023/4/19 15:26
 */
@Service
public class CameraServiceImpl implements CameraService {

  @Resource
  MongoTemplate mongoTemplate;

  /**
   * 给定一个坐标点位和半径，求小时空内的所有相机点位
   */
  @Override
  public List<Camera> findByPoint(double lng, double lat, double distance) {
    return findByPoint(lng, lat, distance, null);

  }

  /**
   * 给定一个坐标点位和半径，求小时空内的 limit 个相机点位
   */
  @Override
  public List<Camera> findByPoint(double lng, double lat, double distance, Long limit) {

    Point point = new Point(lng, lat);

    NearQuery query = NearQuery
        .near(point)
        .maxDistance(new Distance(distance, Metrics.MILES));

    if (Objects.nonNull(limit)) {
      query.limit(limit);
    }

    GeoResults<Camera> geoResults = mongoTemplate.geoNear(query, Camera.class);

    return geoResults.getContent().stream().map(GeoResult::getContent).collect(Collectors.toList());
  }

  /**
   * 根据指定的点位坐标，角度，距离，获取该区域内的所有相机点位
   */
  @Override
  public List<Camera> findByCoordinates(double lng, double lat, double startBearing, double distance) {
    // 固定查找 45° 角的扇形区域
    return findByCoordinates(lng, lat, startBearing, 45.0, distance);
  }

  /**
   * 根据指定的点位坐标，角度，距离，获取该区域内的所有相机点位
   */
  @Override
  public List<Camera> findByCoordinates(double lng, double lat, double startBearing, double angle,
      double distance) {

    GlobalCoordinates start = new GlobalCoordinates(lat, lng);
    Polygon sectorArea = PolygonUtil.getSectorArea(start, startBearing, angle, distance);

    Query query = Query.query(Criteria.where("location").within(sectorArea));
    return mongoTemplate.find(query, Camera.class);
  }
}
