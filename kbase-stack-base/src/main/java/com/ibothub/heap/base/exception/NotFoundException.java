package com.ibothub.heap.base.exception;

import com.ibothub.heap.base.model.vo.ResponseEntity;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2019-06-13 11:37
 */
@RestController
public class NotFoundException implements ErrorController {

    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ResponseEntity error() {
        return ResponseEntity.failure(ResultCode.PAGE_NOT_FOUND);
    }
}
