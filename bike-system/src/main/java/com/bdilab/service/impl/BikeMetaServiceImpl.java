package com.bdilab.service.impl;

import com.bdilab.entity.BikeMeta;
import com.bdilab.repository.BikeMetaRepository;
import com.bdilab.service.BikeMetaService;
import com.bdilab.exception.EntityExistException;
import com.bdilab.exception.EntityNotFoundException;
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
        if (bikeMetaRepository.findByName(meta.getName())!=null) {
            throw new EntityExistException(BikeMeta.class, "name", meta.getName());
        }
        return bikeMetaRepository.save(meta);
    }

    @Override
    public BikeMeta updateBikeMeta(BikeMeta meta) {
        if (bikeMetaRepository.findByName(meta.getName())==null) {
            throw new EntityNotFoundException(BikeMeta.class, "name", meta.getName());
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
