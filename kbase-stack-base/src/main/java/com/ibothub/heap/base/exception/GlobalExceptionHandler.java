package com.ibothub.heap.base.exception;

import com.ibothub.heap.base.model.vo.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

/**
 * 全局异常捕获
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-09-05 10:05
 */
@Slf4j
@ResponseStatus(HttpStatus.OK)
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 参数校验异常
     */
    @ExceptionHandler({ValidationException.class, MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity validateExceptionHandler(Exception ex) {
        ex.printStackTrace();

        FieldError fieldError = null;
        if (ex instanceof MethodArgumentNotValidException) {
            fieldError = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldError();
        } else if (ex instanceof BindException) {
            fieldError = ((BindException) ex).getBindingResult().getFieldError();
        }
        if (fieldError != null) {
            log.error(fieldError.getDefaultMessage());
            return ResponseEntity.failure(ResultCode.PARAMETER_VALIDATE_FAILED, fieldError.getDefaultMessage());
        }

        return ResponseEntity.failure(ResultCode.PARAMETER_VALIDATE_FAILED, ex.getMessage());
    }

    /**
     * Controller异常
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity controllerExceptionHandler(ApiException ex) {
        ex.printStackTrace();
        return ResponseEntity.failure(ResultCode.API_CALLED_FAILED, ex.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity businessExceptionHandler(BusinessException ex) {
        return ResponseEntity.failure(ResultCode.BUSINESS_EXECUTE_FAILED, ex.getMessage());
    }

    /**
     * 错误的实体id
     */
    @ExceptionHandler(WrongEntityIdException.class)
    public ResponseEntity wrongEntityIdException(WrongEntityIdException ex) {
        ex.printStackTrace();
        return ResponseEntity.failure(ResultCode.WRONG_ENTITY_ID_EXCEPTION, ex.getMessage());
    }

    /**
     * 数据库操作异常
     */
    @ExceptionHandler({DaoException.class, DataAccessException.class})
    public ResponseEntity databaseExceptionHandler(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseEntity.failure(ResultCode.DATABASE_OPERATED_FAILED, ex.getMessage());
    }

    /**
     * 其他未定义异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity undefinedExceptionHandler(RuntimeException ex, HttpServletRequest request) {
        ex.printStackTrace();
        return ResponseEntity.failure(ResultCode.UNDEFINED_SERVER_EXCEPTION, ex.getMessage());
    }

}
