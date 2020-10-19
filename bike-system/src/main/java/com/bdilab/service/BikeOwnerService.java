package com.bdilab.service;

import com.bdilab.entity.BikeOwner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public interface BikeOwnerService {

    BikeOwner findById(Long id);

    BikeOwner addBikeOwner(BikeOwner owner);

    BikeOwner updateBikeOwner(BikeOwner owner);

    void deleteByIds(Set<Long> ids);
}
