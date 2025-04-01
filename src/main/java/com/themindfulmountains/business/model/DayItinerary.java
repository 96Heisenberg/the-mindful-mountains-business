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
    private Date itineraryDay;
    private boolean roomsOpted;
    private boolean transportOpted;
    private List<Room> rooms;
    //TODO Add Transport List object & 4,5,6

}
