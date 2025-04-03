package com.themindfulmountains.business.service;

import com.themindfulmountains.business.model.Property;
import com.themindfulmountains.business.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository repository;

    public void onboardProperty(Property property) {
        //TODO Fix Property Id field and API by using separate payload
        repository.save(property);
    }
}
