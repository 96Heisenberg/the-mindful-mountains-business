package com.themindfulmountains.business.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BookingRequest {
    private BigDecimal additionalCosts;
    private BigDecimal discountAmount;
    private BigDecimal finalCost;
    private BigDecimal advancePaid;
    private BigDecimal due;
    private String summary;
}

