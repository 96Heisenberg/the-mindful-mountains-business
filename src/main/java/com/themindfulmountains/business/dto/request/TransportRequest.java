package com.themindfulmountains.business.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransportRequest {
    private String name;
    private String description;
    private String contactNo;
    private String bookingVia;
    private BigDecimal b2cTariff;
    private BigDecimal b2bTariff;
}

