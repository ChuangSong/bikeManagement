package com.bdilab.controller;

import com.bdilab.entity.BikeOwner;
import com.bdilab.exception.BadRequestException;
import com.bdilab.exception.EntityNotFoundException;
import com.bdilab.service.impl.BikeOwnerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Api(tags = "BikeOwnerController",description = "车辆管理：车主信息管理")
@RestController
@RequestMapping(value = "bike/owner")
public class BikeOwnerController {

    @Autowired
    private BikeOwnerServiceImpl bikeOwnerService;

    @ApiOperation(value = "查询车主信息")
    @GetMapping
    public ResponseEntity<BikeOwner> queryById(@PathVariable Long id) {
        BikeOwner owner = bikeOwnerService.findById(id);
        if (owner == null) {
            throw new EntityNotFoundException(BikeOwner.class, "id", String.valueOf(id));
        }
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @ApiOperation(value = "添加车主信息")
    @PutMapping
    public ResponseEntity<BikeOwner> addBikeOwner(@Validated @RequestBody BikeOwner owner) {
        if (owner.getId() != null) {
            throw new BadRequestException("A new bike owner cannot already have an ID");
        }
        return new ResponseEntity<>(bikeOwnerService.addBikeOwner(owner), HttpStatus.OK);
    }

    @ApiOperation(value = "修改车主信息")
    @PutMapping("/modify")
    public ResponseEntity<BikeOwner> modifyBikeOwner(@Validated @RequestBody BikeOwner owner) {
        if (bikeOwnerService.findById(owner.getId()) == null) {
            throw new EntityNotFoundException(BikeOwner.class, "id", String.valueOf(owner.getId()));
        }
        return new ResponseEntity<>(bikeOwnerService.updateBikeOwner(owner), HttpStatus.OK);
    }

    @ApiOperation(value = "删除车主信息")
    @DeleteMapping
    public ResponseEntity<BikeOwner> delBikeOwner(@RequestParam Set<Long> ids) {
        bikeOwnerService.deleteByIds(ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
