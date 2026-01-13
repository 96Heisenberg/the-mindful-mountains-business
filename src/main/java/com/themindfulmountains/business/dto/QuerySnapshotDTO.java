package com.themindfulmountains.business.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class QuerySnapshotDTO {
    private UUID queryId;
    private UUID customerId;
    private Integer noOfAdults;
    private Integer noOfChilds;
    private BigDecimal b2bTotalItineraryCost;
    private BigDecimal b2cTotalItineraryCost;
    private List<DaySnapshot> days;
}

