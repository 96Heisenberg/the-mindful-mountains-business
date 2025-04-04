package com.themindfulmountains.business.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RoomRequestPayload {
    private String roomName;
    private String services;
    private BigDecimal roomTariffB2C;
    private BigDecimal roomTariffB2B;
}

