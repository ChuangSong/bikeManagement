package com.bdilab.bikemanagement.service;

import com.bdilab.bikemanagement.entity.BikeMeta;

import java.util.Set;

public interface BikeMetaService {

    BikeMeta selectBikeMeta(Long id);

    BikeMeta addBikeMeta(BikeMeta meta);

    BikeMeta updateBikeMeta(BikeMeta meta);

    void deleteBikeMeta(Set<Long> ids);

}
