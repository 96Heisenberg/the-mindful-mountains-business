package com.themindfulmountains.business.service;

import com.themindfulmountains.business.model.QueryItinerary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QueryService {
    public void raiseQuery(QueryItinerary queryItinerary) {
        log.info("Query Itinerary is received for {}", queryItinerary.getCustomer().getName());
    }
}
