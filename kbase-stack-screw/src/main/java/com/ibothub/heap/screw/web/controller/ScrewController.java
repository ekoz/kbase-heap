package com.ibothub.heap.screw.web.controller;

import com.ibothub.heap.base.model.vo.ResponseEntity;
import com.ibothub.heap.screw.model.vo.req.DriverReq;
import com.ibothub.heap.screw.service.ScrewService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/8/3 20:55
 */
@RestController
@RequestMapping("/screw")
public class ScrewController {

    @Resource
    ScrewService screwService;

    @PostMapping
    public ResponseEntity create(@RequestBody DriverReq request){
        screwService.create(request);
        return ResponseEntity.ok();
    }
}
