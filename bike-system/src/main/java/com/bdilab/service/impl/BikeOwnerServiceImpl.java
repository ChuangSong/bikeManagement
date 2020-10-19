package com.bdilab.service.impl;

import com.bdilab.entity.BikeOwner;
import com.bdilab.repository.BikeOwnerRepository;
import com.bdilab.service.BikeOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

@Service
@CacheConfig(cacheNames = "bike_owner")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = Exception.class)
public class BikeOwnerServiceImpl implements BikeOwnerService {

    @Autowired
    private BikeOwnerRepository bikeOwnerRepository;

    @Override
    @Cacheable
    public BikeOwner findById(Long id) {
        return bikeOwnerRepository.findById(id).orElse(null);
    }

    @Override
    public BikeOwner addBikeOwner(BikeOwner owner) {
        return bikeOwnerRepository.save(owner);
    }

    @Override
    public BikeOwner updateBikeOwner(BikeOwner owner) {
        return bikeOwnerRepository.save(owner);
    }

    @Override
    public void deleteByIds(Set<Long> ids) {
        for (Long id : ids) {
            bikeOwnerRepository.deleteById(id);
        }
    }
}
