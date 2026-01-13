package com.themindfulmountains.business.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DaySnapshot {
    private Date itineraryDay;
    private boolean roomsOpted;
    private boolean transportOpted;
    private Integer noOfUnits;
    private List<RoomSnapshot> rooms;
    private List<TransportSnapshot> transports;
}

