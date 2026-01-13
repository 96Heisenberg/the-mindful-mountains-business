package com.themindfulmountains.business.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BookingResponse {

    private UUID bookingId;
    private UUID queryId;
    private UUID customerId;
    private String customerName;

    private BigDecimal b2bTotal;
    private BigDecimal b2cTotal;
    private BigDecimal additionalCosts;
    private BigDecimal discountAmount;
    private BigDecimal finalCost;
    private BigDecimal advancePaid;
    private BigDecimal due;

    private String summary;
    private String status;
    private LocalDateTime createdAt;
}

