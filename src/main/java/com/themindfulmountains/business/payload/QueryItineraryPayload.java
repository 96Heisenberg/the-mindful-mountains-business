package com.themindfulmountains.business.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryItineraryPayload {
    private Customer customer;
    private Integer noOfAdults;
    private Integer noOfChilds;
    private List<Customer> coTravellers;


}
