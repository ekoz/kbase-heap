package com.ibothub.heap.stack.mongo.util;

import static org.gavaghan.geodesy.Ellipsoid.WGS84;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2023/4/19 9:45
 */
public class PolygonUtil {

  private static GeodeticCalculator geodeticCalculator = new GeodeticCalculator();

  /**
   *
   * 提供一个起始点，扇形角度，以及距离，返回一个扇形 geometry 坐标字符串
   *
   * @param start
   * @param startBearing 比如 22.5°
   * @param angle 比如，45°
   * @param distance 20*1000 20千米（公里）
   * @return { type: "Polygon", coordinates: [[[114.3006,30.598618], [114.10779168198872,30.667512222143216], [114.10806489484457,30.5294377516621], [114.3006,30.598618]]] }
   */
  public static Polygon getSectorArea(GlobalCoordinates start, Double startBearing, Double angle, double distance){
    GlobalCoordinates left = geodeticCalculator
        .calculateEndingGlobalCoordinates(WGS84, start, startBearing, distance);

    GlobalCoordinates right = geodeticCalculator
        .calculateEndingGlobalCoordinates(WGS84, start, startBearing + angle, distance);

    // 需要是一个闭环，数组以 start 开头，以 start 结尾
    ArrayList<Point> points = Lists
        .newArrayList(new Point(start.getLongitude(), start.getLatitude()),
            new Point(left.getLongitude(), left.getLatitude()),
            new Point(right.getLongitude(), right.getLatitude()),
            new Point(start.getLongitude(), start.getLatitude())
        );

    return new Polygon(points);
  }
}
