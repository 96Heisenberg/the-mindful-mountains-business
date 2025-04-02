package com.themindfulmountains.business.api;

import com.themindfulmountains.business.model.QueryItinerary;
import com.themindfulmountains.business.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BookingController {

    @Autowired
    QueryService service;

    @PostMapping("/booking")
    public ResponseEntity<String> convertQueryToBooking(@RequestBody QueryItinerary queryItinerary){
                return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

    @GetMapping("/booking/all")
    public ResponseEntity<String> getAllBookings(){
        return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<String> getBookingById(){
        return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

    @PutMapping("/booking/{bookingId}")
    public ResponseEntity<String> updateBookingById(){
        return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

}
