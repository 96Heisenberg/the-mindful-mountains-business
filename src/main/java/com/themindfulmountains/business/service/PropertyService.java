package com.themindfulmountains.business.service;

import com.themindfulmountains.business.model.Property;
import com.themindfulmountains.business.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository repository;

    public void onboardProperty(Property property) {
        //TODO Fix Property Id field and API by using separate payload
        repository.save(property);
    }

    // ✅ Get All Properties
    public List<Property> getAllProperties() {
        return repository.findAll();
    }

    // ✅ Get Property by ID
    public Optional<Property> getPropertyById(String propertyId) {
        return repository.findById(propertyId);
    }

    // ✅ Update Property by ID
    public boolean updateProperty(String propertyId, Property updatedProperty) {
        Optional<Property> existingProperty = repository.findById(propertyId);
        if (existingProperty.isPresent()) {
            Property property = existingProperty.get();
            property.setActive(updatedProperty.isActive());
            property.setName(updatedProperty.getName());
            property.setLocation(updatedProperty.getLocation());
            property.setContactNo(updatedProperty.getContactNo());
            property.setEmailId(updatedProperty.getEmailId());
            property.setImageUrls(updatedProperty.getImageUrls());
            repository.save(property);
            return true;
        }
        return false;
    }

}
