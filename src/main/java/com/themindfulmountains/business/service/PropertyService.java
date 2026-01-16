package com.themindfulmountains.business.service;

import com.themindfulmountains.business.model.Property;
import com.themindfulmountains.business.payload.request.PropertyRequestPayload;
import com.themindfulmountains.business.payload.response.PropertyResponsePayload;
import com.themindfulmountains.business.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository repository;

    public void onboardProperty(PropertyRequestPayload payload) {
        Property property = mapToProperty(payload);
        repository.save(property);
    }

    public List<PropertyResponsePayload> getAllProperties() {
        return repository.findAll()
                .stream()
                .map(this::mapPropertyToPropertyResponsePayload)
                .collect(Collectors.toList());
    }

    public Optional<PropertyResponsePayload> getPropertyById(String propertyId) {
        return repository.findById(propertyId)
                .map(this::mapPropertyToPropertyResponsePayload);
    }

    private PropertyResponsePayload mapPropertyToPropertyResponsePayload(Property property) {
        PropertyResponsePayload payload = new PropertyResponsePayload();
        payload.setPropertyId(property.getPropertyId());
        payload.setName(property.getName());
        payload.setLocation(property.getLocation());
        payload.setContactNo(property.getContactNo());
        payload.setEmailId(property.getEmailId());
        payload.setImageUrls(property.getImageUrls());
        payload.setActive(property.isActive());
        return payload;
    }


    public boolean updateProperty(String propertyId, PropertyRequestPayload payload) {
        Optional<Property> existingProperty = repository.findById(propertyId);
        if (existingProperty.isPresent()) {
            Property property = existingProperty.get();
            property.setName(payload.getName());
            property.setLocation(payload.getLocation());
            property.setContactNo(payload.getContactNo());
            property.setEmailId(payload.getEmailId());
            if(!payload.getImageUrls().isEmpty()){
                property.getImageUrls().addAll(payload.getImageUrls());
            }
            property.setActive(payload.isActive());

            repository.save(property);
            return true;
        }
        return false;
    }

    private Property mapToProperty(PropertyRequestPayload payload) {
        Property property = new Property();
        property.setName(payload.getName());
        property.setLocation(payload.getLocation());
        property.setContactNo(payload.getContactNo());
        property.setEmailId(payload.getEmailId());
        property.setImageUrls(payload.getImageUrls());
        property.setActive(payload.isActive());
        return property;
    }
}
