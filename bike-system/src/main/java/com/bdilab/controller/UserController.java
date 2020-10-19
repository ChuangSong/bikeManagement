package com.bdilab.controller;

import com.bdilab.aop.Log;
import com.bdilab.entity.User;
import com.bdilab.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Api(tags = "UserController",description = "系统管理：平台用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Log("添加用户")
    @ApiOperation("添加用户")
    @PutMapping("/add")
    public ResponseEntity<User> addUser(@Validated @RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }

    @Log("修改用户")
    @ApiOperation("修改用户")
    @PutMapping("/update")
    public ResponseEntity<User> modifyUser(@Validated @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @Log("删除用户")
    @ApiOperation("删除用户")
    @DeleteMapping
    public ResponseEntity<Object> delUsers(Set<Long> ids) {
        userService.delUsers(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
