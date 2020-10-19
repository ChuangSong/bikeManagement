package com.bdillab.service.impl;

import com.bdillab.entity.BikeInfo;
import com.bdillab.dto.FieldQuery;
import com.bdillab.repository.BikeRepository;
import com.bdillab.repository.EsBikeRepository;
import com.bdillab.service.SearchService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private EsBikeRepository esBikeRepository;

    @Autowired
    private BikeRepository bikeRepository;

    private Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Override
    public Long importEsFromMysql() {
        List<BikeInfo> bikeInfos = bikeRepository.findAll();
        Iterable<BikeInfo> bikeInfoIterable = esBikeRepository.saveAll(bikeInfos);
        Iterator<BikeInfo> iterator = bikeInfoIterable.iterator();
        long result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public Page<BikeInfo> findByNumber(String number, Integer pageNum, Integer pageSize) {
        return esBikeRepository.findByNumber(number, PageRequest.of(pageNum, pageSize));
    }

    @Override
    public Page<BikeInfo> findByType(String type, Integer pageNum, Integer pageSize) {
        return esBikeRepository.findByType(type, PageRequest.of(pageNum, pageSize));
    }


    @Override
    public Page<BikeInfo> matchQuery(String field, String val) {
        //构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //构建分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery(field,val));
        Page<BikeInfo> res = esBikeRepository.search(queryBuilder.build());
        return res;
    }

    @Override
    public Page<BikeInfo> termQuery(String field, String val) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.termQuery(field, val));
        Page<BikeInfo> res = esBikeRepository.search(queryBuilder.build());
        return res;
    }

    @Override
    public Page<BikeInfo> fuzzyQuery(String field, String val) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.fuzzyQuery(field, val));
        Page<BikeInfo> res = esBikeRepository.search(queryBuilder.build());
        return res;
    }

    @Override
    public Page<BikeInfo> booleanQuery(List<FieldQuery> queries, Integer pageNum, Integer pageSize) {
        //查询构造器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        //分页
        queryBuilder.withPageable(pageRequest);
        //布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (FieldQuery fieldQuery : queries) {
            String field = fieldQuery.getField();
            String val = fieldQuery.getVal();
            FieldQuery.FieldOpt opt = fieldQuery.getOpt();
            switch (opt) {
                case MUST:
                    boolQueryBuilder.must(QueryBuilders.termQuery(field,val));
                    break;
                case SHOULD:
                    boolQueryBuilder.should(QueryBuilders.termQuery(field, val));
                    break;
                case MUST_NOT:
                    boolQueryBuilder.mustNot(QueryBuilders.termQuery(field, val));
                    break;
            }
        }
        queryBuilder.withQuery(boolQueryBuilder);
        NativeSearchQuery searchQuery = queryBuilder.build();
        logger.info("查询语句DSL: " + searchQuery.getQuery().toString());
        Page<BikeInfo> res = esBikeRepository.search(searchQuery);
        return res;
    }

    @Override
    public Page<BikeInfo> rangeQuery(String field, Double from, Double to) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.rangeQuery(field).from(from).to(to));
        Page<BikeInfo> res = esBikeRepository.search(queryBuilder.build());
        return res;
    }

    @Override
    public Page<BikeInfo> aggregationQuery(String filed) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //设置查询所有
        queryBuilder.withQuery(QueryBuilders.matchAllQuery());
        //对指定字段做聚合处理
        TermsAggregationBuilder fields = AggregationBuilders.terms("fields").field(filed);
        queryBuilder.addAggregation(fields);
        Page<BikeInfo> res = esBikeRepository.search(queryBuilder.build());
        return res;
    }
}
