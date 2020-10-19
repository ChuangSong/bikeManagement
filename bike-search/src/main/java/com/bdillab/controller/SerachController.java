package com.bdillab.controller;

import com.bdilab.aop.Log;
import com.bdillab.dto.FieldQuery;
import com.bdillab.entity.BikeInfo;
import com.bdillab.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "EsBikeController",description = "es:车辆信息检索")
@RestController
@RequestMapping("/es")
public class SerachController {

    @Autowired
    private SearchService searchService;

    @Log("从mysql导入数据到es")
    @ApiOperation("导入全部数据到es")
    @GetMapping("/import")
    public ResponseEntity<Object> importAll() {
        long sum = searchService.importEsFromMysql();
        Map<String, Long> res = new HashMap<>() {{
            put("导入数据总量", sum);
        }};
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @Log("基于车辆编号检索")
    @ApiOperation("基于车辆编号检索")
    @GetMapping("/search/number")
    public ResponseEntity<Object> searchForNumber(@RequestParam(value = "number") String number,
                                         @RequestParam(required = false,defaultValue = "0") Integer pageNum,
                                         @RequestParam(required = false,defaultValue = "5") Integer pageSize) {
        Page<BikeInfo> res = searchService.findByNumber(number, pageNum, pageSize);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @Log("基于车辆类型检索")
    @ApiOperation("基于车辆类型检索")
    @GetMapping("/search/type")
    public ResponseEntity<Object> searchForType(@RequestParam(value = "type") String type,
                                         @RequestParam(required = false,defaultValue = "0") Integer pageNum,
                                         @RequestParam(required = false,defaultValue = "5") Integer pageSize) {
        Page<BikeInfo> res = searchService.findByType(type, pageNum, pageSize);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Log("基于车辆多字段组合检索")
    @ApiOperation("基于车辆多字段组合检索")
    @PostMapping("/search/multiquery")
    public ResponseEntity<Object> search(@RequestParam(name = "字段组合查询") List<FieldQuery> fieldQueries,
                                         @RequestParam(required = false,defaultValue = "0") Integer pageNum,
                                         @RequestParam(required = false,defaultValue = "5") Integer pageSize) {
        Page<BikeInfo> res = searchService.booleanQuery(fieldQueries, pageNum, pageSize);
        if (res.getTotalElements() > 0) {
            return new ResponseEntity<>(res.getContent(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
