package com.themindfulmountains.business.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryResponse {

    private String queryId;

    private Integer noOfAdults;
    private Integer noOfChilds;

    private BigDecimal b2bTotalItineraryCost;
    private BigDecimal b2cTotalItineraryCost;

    private String queryPdfLink;

    private CustomerSummaryResponse customer;

    private List<DayItineraryResponse> dayItineraries;
}