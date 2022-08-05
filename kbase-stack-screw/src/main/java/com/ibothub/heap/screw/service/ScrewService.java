package com.ibothub.heap.screw.service;

import com.ibothub.heap.screw.model.vo.req.DriverReq;

import java.io.File;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/8/3 21:01
 */
public interface ScrewService {

    String create(DriverReq request);

    File getFile(String fileName);
}
