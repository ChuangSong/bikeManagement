package com.bdillab.service;

import com.bdillab.entity.BikeInfo;
import com.bdillab.dto.FieldQuery;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SearchService {

//    void createIndex();//创建索引
//
//    void deleteIndex();//删除索引
//
//    void query(String index, String type, List<Long> ids);

    Long importEsFromMysql();//从mysql导入全部数据到es

    Page<BikeInfo> findByNumber(String number, Integer pageNum, Integer pageSize);

    Page<BikeInfo> findByType(String type, Integer pageNum, Integer pageSize);

    Page<BikeInfo> matchQuery(String field, String val);//词条匹配(先分词，再匹配)

    Page<BikeInfo> termQuery(String field, String val);//词条匹配，不分词

    Page<BikeInfo> fuzzyQuery(String field, String val);//模糊匹配

    Page<BikeInfo> booleanQuery(List<FieldQuery> queries, Integer pageNum, Integer pageSize);//组合查询

    Page<BikeInfo> rangeQuery(String field, Double from, Double to);//范围查询

    Page<BikeInfo> aggregationQuery(String filed);//聚合查询

}
