package com.themindfulmountains.business.api;

import com.themindfulmountains.business.model.Property;
import com.themindfulmountains.business.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")  // Allow all origins //TODO Remove this later
@Controller
@RequestMapping("/property")
public class PropertyController {

   //TODO Need To Add Property Feature and Room Feature

    @Autowired
    PropertyService service;

    // Onboard A New Property API
    @PostMapping
    public ResponseEntity<String> onboardProperty(@RequestBody Property property){
        service.onboardProperty(property);
        return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

    // Get All Properties API
    @GetMapping("/all")
    public ResponseEntity<List<Property>> getAllProperties() {
        return ResponseEntity.ok(service.getAllProperties());
    }

    // Get Property by ID API
    @GetMapping("/{propertyId}")
    public ResponseEntity<Property> getPropertyById(@PathVariable String propertyId) {
        Optional<Property> property = service.getPropertyById(propertyId);
        return property.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Property by ID API
    @PutMapping("/{propertyId}")
    public ResponseEntity<String> updateProperty(@PathVariable String propertyId, @RequestBody Property updatedProperty) {
        boolean isUpdated = service.updateProperty(propertyId, updatedProperty);
        if (isUpdated) {
            return ResponseEntity.ok("Property updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
