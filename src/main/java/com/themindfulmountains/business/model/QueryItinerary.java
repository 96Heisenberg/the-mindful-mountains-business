package com.themindfulmountains.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryItinerary {
    private String itineraryId;
    private String queryPdfLink;
    private Integer noOfAdults;
    private Integer noOfChilds;
    private BigDecimal b2bTotalItineraryCost;
    private BigDecimal b2cTotalItineraryCost;
    private Customer customer;
    private List<Customer> coTravellers;
    private List<DayItinerary> dayItineraryList;

}
