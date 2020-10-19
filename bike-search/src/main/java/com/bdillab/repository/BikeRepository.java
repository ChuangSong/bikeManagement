package com.bdillab.repository;

import com.bdillab.entity.BikeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends JpaRepository<BikeInfo,Long> {

}
