package com.bdilab.controller;

import com.bdilab.aop.Log;
import com.bdilab.service.LogService;
import com.bdilab.service.impl.LogServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags = "LogController",description = "系统管理：日志管理")
@RestController
@RequestMapping(value = "/log")
public class LogController {

    @Autowired
    private LogServiceImpl logService;

    @Log("查询全部日志")
    @GetMapping(value = "/all")
    @ApiOperation(value = "查询全部日志")
    public ResponseEntity<Object> queryAll(Pageable pageable) {
        return new ResponseEntity<>(logService.queryAll(pageable), HttpStatus.OK);
    }

    @Log("查询当前用户日志")
    @GetMapping(value = "/user")
    @ApiOperation("查询当前用户日志")
    public ResponseEntity<Object> queryUserLog(Pageable pageable) {
        return new ResponseEntity<>(logService.queryUserLog(pageable),HttpStatus.OK);
    }

    @Log("基于日志类别查询")
    @GetMapping(value = "/{type}")
    @ApiOperation("基于日志类别查询")
    public ResponseEntity<Object> queryLogByType(@PathVariable(value = "type") String logType, Pageable pageable) {
        return new ResponseEntity<>(logService.queryByLogType(logType,pageable), HttpStatus.OK);
    }
}
