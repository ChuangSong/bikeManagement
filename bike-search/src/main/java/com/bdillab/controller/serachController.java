package com.bdillab.controller;

import com.bdillab.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "es:车辆信息检索")
@RestController
@RequestMapping("/bike/search")
public class serachController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/index")
    @ApiOperation("查询车辆")
    public ResponseEntity<Object> findByBikeInfo() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @ApiOperation(value = "基于")
//    public ResponseEntity<Object> searchBike() {
//
//    }
}
