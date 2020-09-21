package com.bdilab.exception;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 异常实体类
 */
@Data
public class ApiError {
    /**
     * 错误码
     */
    private Integer status = 400;
    /**
     * 错误内容
     */
    private String message;
    /**
     * 当前时间
     */
    private LocalDateTime timestamp;

    public ApiError() {
        timestamp = LocalDateTime.now();
    }

    public static ApiError error(String message) {
        ApiError error = new ApiError();
        error.setMessage(message);
        return error;
    }

    public static ApiError error(Integer status,String message) {
        ApiError error = new ApiError();
        error.setStatus(status);
        error.setMessage(message);
        return error;
    }

}
