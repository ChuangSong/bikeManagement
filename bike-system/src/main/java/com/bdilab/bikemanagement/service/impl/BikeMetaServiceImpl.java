package com.bdilab.bikemanagement.service.impl;

import com.bdilab.bikemanagement.entity.BikeMeta;
import com.bdilab.bikemanagement.repository.BikeMetaRepository;
import com.bdilab.bikemanagement.service.BikeMetaService;
import com.bdilab.exception.EntityExistException;
import com.bdilab.exception.EntityNotFoundException;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;


@Service
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
@CacheConfig(cacheNames = "bike_meta")
public class BikeMetaServiceImpl implements BikeMetaService {

    @Autowired
    private BikeMetaRepository bikeMetaRepository;

    @Override
    public BikeMeta selectBikeMeta(Long id) {
        Optional<BikeMeta> meta = bikeMetaRepository.findById(id);
        if (meta.isEmpty()) {
            throw new EntityNotFoundException(BikeMeta.class, "id", String.valueOf(id));
        }
        return meta.get();
    }

    @Override
    public BikeMeta addBikeMeta(BikeMeta meta) {
        long id = meta.getId();
        if (bikeMetaRepository.findById(id).isPresent()) {
            throw new EntityExistException(BikeMeta.class, "id", String.valueOf(id));
        }
        return bikeMetaRepository.save(meta);
    }

    @Override
    public BikeMeta updateBikeMeta(BikeMeta meta) {
        Long id = meta.getId();
        if (id == null || bikeMetaRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(BikeMeta.class, "id", String.valueOf(id));
        }
        return bikeMetaRepository.save(meta);
    }

    @Override
    public void deleteBikeMeta(Set<Long> ids) {
        for (Long id : ids){
            bikeMetaRepository.deleteById(id);
        }
    }
}
