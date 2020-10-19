package com.bdilab.repository;

import com.bdilab.entity.BikeOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeOwnerRepository extends JpaRepository<BikeOwner,Long> {
}
