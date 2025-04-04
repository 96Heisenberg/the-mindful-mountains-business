package com.themindfulmountains.business.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponsePayload {
    private String roomId;
    private String roomName;
    private String services;
    private String propertyId;
    private String propertyName;
    private String propertyLocation;
    private BigDecimal roomTariffB2C;
    private BigDecimal roomTariffB2B;
}
