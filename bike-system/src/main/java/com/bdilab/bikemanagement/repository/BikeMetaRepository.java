package com.bdilab.bikemanagement.repository;

import com.bdilab.bikemanagement.entity.BikeMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeMetaRepository extends JpaRepository<BikeMeta,Long> {

}
