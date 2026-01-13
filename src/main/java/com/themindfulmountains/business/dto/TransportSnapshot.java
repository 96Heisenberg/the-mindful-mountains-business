package com.themindfulmountains.business.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransportSnapshot {
    private String name;
    private String description;
    private String contactNo;
    private BigDecimal b2bTariff;
    private BigDecimal b2cTariff;
}
