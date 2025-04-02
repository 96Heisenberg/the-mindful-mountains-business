package com.themindfulmountains.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transport {
    private String name;
    private String description;
    private String contactNo;
    private String bookingVia; //SELF / AGENT
    private BigDecimal b2cTariff;
    private BigDecimal b2bTariff;

}
