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
public class Booking {
    private String bookingId;
    private String bookingPdfLink;
    private Integer noOfAdults;
    private Integer noOfChilds;
    private BigDecimal advancePaid;
    private BigDecimal amountDue;
    private BigDecimal b2bTotalItineraryCost;
    private BigDecimal b2cTotalItineraryCost;
    private BigDecimal profit;
    private Customer customer;
    private List<DayItinerary> dayItineraryList;

}
