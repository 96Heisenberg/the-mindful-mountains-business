package com.themindfulmountains.business.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DayItineraryRequest {

    private LocalDate itineraryDay;
    private boolean roomsOpted;
    private boolean transportOpted;

    private List<RoomRef> rooms;
    private Integer noOfUnits;
    private List<TransportRequest> transports;
}

