package com.bdillab.service;

import com.bdillab.entity.BikeInfo;
import com.bdillab.entity.FieldQuery;
import com.bdillab.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SearchService {

//    void createIndex();//创建索引
//
//    void deleteIndex();//删除索引
//
//    void query(String index, String type, List<Long> ids);

    Page<BikeInfo> matchQuery(String field, String val);//词条匹配(先分词，再匹配)

    Page<BikeInfo> termQuery(String field, String val);//词条匹配，不分词

    Page<BikeInfo> fuzzyQuery(String field, String val);//模糊匹配

    Page<BikeInfo> booleanQuery(List<FieldQuery> queries);//组合查询

    Page<BikeInfo> rangeQuery(String field, Double from, Double to);//范围查询

}
