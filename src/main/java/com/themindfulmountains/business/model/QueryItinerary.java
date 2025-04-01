package com.themindfulmountains.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryItinerary {
    private Customer customer;
    private Integer noOfAdults;
    private Integer noOfChilds;
    private List<Customer> coTravellers;
    private List<DayItinerary> dayItineraryList;


}
