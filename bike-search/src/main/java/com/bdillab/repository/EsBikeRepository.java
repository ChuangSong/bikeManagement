package com.bdillab.repository;

import com.bdillab.entity.BikeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EsBikeRepository extends ElasticsearchRepository<BikeInfo,Long> {

    Page<BikeInfo> findByNumber(String number, Pageable pageable);

    Page<BikeInfo> findByType(String type,Pageable pageable);
}
