package com.bdilab.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<ApiError> handleException(Throwable e) {
        log.error(e.getMessage());
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }


    /**
     * 处理请求错误异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException e) {
        log.error(e.getMessage());
        return buildResponseEntity(ApiError.error(e.getStatus(), e.getMessage()));
    }

    /**
     * 处理实体存在异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityExistException.class)
    public ResponseEntity<ApiError> handleEntityExistException(EntityExistException e) {
        log.error(e.getMessage());
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    /**
     * 处理实体不存在异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error(e.getMessage());
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    /**
     * 统一返回结果
     * @param error
     * @return
     */
    private ResponseEntity<ApiError> buildResponseEntity(ApiError error) {
        return new ResponseEntity<>(error, HttpStatus.valueOf(error.getStatus()));
    }
}
