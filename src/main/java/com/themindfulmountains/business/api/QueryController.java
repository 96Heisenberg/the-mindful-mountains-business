package com.themindfulmountains.business.api;

import com.themindfulmountains.business.payload.QueryItineraryPayload;
import com.themindfulmountains.business.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class QueryController {

    @Autowired
    QueryService service;

    @PostMapping("/query")
    public ResponseEntity<String> raiseQuery(@RequestBody QueryItineraryPayload queryItineraryPayload){
                return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

    @GetMapping("/query/all")
    public ResponseEntity<String> getAllQueries(){
        return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

    @GetMapping("/query/{queryId}")
    public ResponseEntity<String> getQueryById(){
        return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

    @PutMapping("/query/{queryId}")
    public ResponseEntity<String> updateQueryById(){
        return ResponseEntity.ok("Thanks for request Submission. Our team will contact you soon!");
    }

}
