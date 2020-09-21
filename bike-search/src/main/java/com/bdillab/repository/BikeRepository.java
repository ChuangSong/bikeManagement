package com.bdillab.repository;

import com.bdillab.entity.BikeInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends ElasticsearchRepository<BikeInfo,Long> {
    int findBikeInfoByArea_id(long areaId);
}
