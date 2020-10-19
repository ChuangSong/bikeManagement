package com.bdilab.repository;

import com.bdilab.entity.BikeMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeMetaRepository extends JpaRepository<BikeMeta,Long> {
    BikeMeta findByName(String name);
}
