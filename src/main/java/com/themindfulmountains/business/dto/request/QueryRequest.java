package com.themindfulmountains.business.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QueryRequest {

    private Integer noOfAdults;
    private Integer noOfChilds;
    private List<DayItineraryRequest> dayItineraries;
}

