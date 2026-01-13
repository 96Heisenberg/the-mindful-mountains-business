package com.themindfulmountains.business.api;

import com.themindfulmountains.business.dto.response.BookingResponse;
import com.themindfulmountains.business.model.Booking;
import com.themindfulmountains.business.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/booking")
@RestController
public class BookingController {

    @Autowired
    BookingService service;

    @GetMapping
    public List<BookingResponse> getAll() {
        return service.getAllBookingsWithCustomerName();
    }


    @PutMapping("/{bookingId}")
    public Booking update(@PathVariable String bookingId, @RequestBody Booking updated) {
        return service.updateBooking(bookingId, updated);
    }

}
