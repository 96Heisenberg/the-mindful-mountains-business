package com.themindfulmountains.business.api;

import com.themindfulmountains.business.model.Property;
import com.themindfulmountains.business.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PropertyController {

   //TODO Need To Add Property Feature and Room Feature

    @Autowired
    PropertyService service;

    @PostMapping("/property")
    public ResponseEntity<String> onboardProperty(@RequestBody Property property){
        service.onboardProperty(property);
        return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

}
