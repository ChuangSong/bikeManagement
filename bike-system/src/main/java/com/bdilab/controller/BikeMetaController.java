package com.bdilab.controller;

import com.bdilab.controller.response.MetaData;
import com.bdilab.entity.BikeMeta;
import com.bdilab.exception.ApiError;
import com.bdilab.exception.BadRequestException;
import com.bdilab.service.impl.BikeMetaServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Api(tags = "BikeMetaController",description = "车辆管理：元数据管理")
@RestController
@RequestMapping("/bike/meta")
public class BikeMetaController {

    @Autowired
    private BikeMetaServiceImpl bikeMetaService;

    @ApiOperation(value = "查询车辆信息")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBikeMeta(@PathVariable(value = "id") Long id) {
        BikeMeta bikeMetaInfo = bikeMetaService.selectBikeMeta(id);
        if (bikeMetaInfo == null) {
            return new ResponseEntity<>("记录不存在", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bikeMetaInfo,HttpStatus.OK);
    }

    @ApiOperation(value = "修改车辆信息")
    @PutMapping("/update")
    public ResponseEntity<Object> updateBikeMeta(@RequestBody BikeMeta bikeMeta) {
        bikeMetaService.updateBikeMeta(bikeMeta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "新增车辆信息")
    @PutMapping("/add")
    public ResponseEntity<Object> addBikeMeta(@Validated @RequestBody BikeMeta bikeMeta) {
        if (bikeMeta.getId() != null) {
            throw new BadRequestException("A new bike meta cannot already have an ID");
        }
        bikeMetaService.addBikeMeta(bikeMeta);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("删除车辆信息")
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteBikeMeta(@RequestParam Set<Long> ids) {
        bikeMetaService.deleteBikeMeta(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
