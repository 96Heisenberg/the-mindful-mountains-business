package com.themindfulmountains.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DayItinerary {
    private boolean roomsOpted;
    private boolean transportOpted;
    private Date itineraryDay;
    private List<Room> rooms;
    private List<Transport> transports;


}
