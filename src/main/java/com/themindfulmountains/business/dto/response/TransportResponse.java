package com.themindfulmountains.business.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransportResponse {

    private String name;
    private String description;
    private String contactNo;
    private String bookingVia;

    private BigDecimal b2cTariff;
    private BigDecimal b2bTariff;
}
