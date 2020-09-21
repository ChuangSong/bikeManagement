package com.bdillab.service.impl;

import com.bdillab.entity.BikeInfo;
import com.bdillab.entity.FieldQuery;
import com.bdillab.repository.BikeRepository;
import com.bdillab.service.SearchService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private BikeRepository bikeRepository;

    @Override
    public Page<BikeInfo> matchQuery(String field, String val) {
        //构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //构建分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery(field,val));
        Page<BikeInfo> res = bikeRepository.search(queryBuilder.build());
        return res;
    }

    @Override
    public Page<BikeInfo> termQuery(String field, String val) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.termQuery(field, val));
        Page<BikeInfo> res = bikeRepository.search(queryBuilder.build());
        return res;
    }

    @Override
    public Page<BikeInfo> fuzzyQuery(String field, String val) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.fuzzyQuery(field, val));
        Page<BikeInfo> res = bikeRepository.search(queryBuilder.build());
        return res;
    }

    @Override
    public Page<BikeInfo> booleanQuery(List<FieldQuery> queries) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
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
        Page<BikeInfo> res = bikeRepository.search(queryBuilder.build());
        return res;
    }

    @Override
    public Page<BikeInfo> rangeQuery(String field, Double from, Double to) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.rangeQuery(field).from(from).to(to));
        Page<BikeInfo> res = bikeRepository.search(queryBuilder.build());
        return res;
    }
}
