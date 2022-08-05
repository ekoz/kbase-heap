package com.ibothub.heap.screw.web.controller;

import com.ibothub.heap.base.model.vo.ResponseEntity;
import com.ibothub.heap.screw.model.vo.req.DriverReq;
import com.ibothub.heap.screw.service.ScrewService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2022/8/3 20:55
 */
@Api
@RestController
@RequestMapping("/screw")
@Slf4j
public class ScrewController {

    @Resource
    ScrewService screwService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody DriverReq request){
        String fileName = screwService.create(request);
        return ResponseEntity.ok(fileName);
    }

    @GetMapping
    public org.springframework.http.ResponseEntity<byte[]> download(String fileName){
        try {
            File file = screwService.getFile(fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);
            return new org.springframework.http.ResponseEntity<>(IOUtils.toByteArray(new FileInputStream(file)), headers, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            log.error("文件[" + fileName + "]不存在, " + e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
