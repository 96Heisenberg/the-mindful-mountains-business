package com.themindfulmountains.business.api;

import com.themindfulmountains.business.model.QueryItinerary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CustomerController {

    //Currently this controller will not be in use
    //Added for latter phase

    @PostMapping("/add")
    public ResponseEntity<String> addCustomerDetails(@RequestBody QueryItinerary queryItinerary){
                return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

    @GetMapping("/customer/all")
    public ResponseEntity<String> getAllCustomers(){
        return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<String> getCustomerById(){
        return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

    @PutMapping("/customer/{customerId}")
    public ResponseEntity<String> updateCustomerById(){
        return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

}
