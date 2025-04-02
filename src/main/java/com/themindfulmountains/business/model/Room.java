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
public class Room {
    private String roomId;
    private String propertyId;
    private String services; //Any meal plan included or not etc.
    private String roomName;
    private String propertyName;
    private String bookingVia; //SELF / AGENT
    private String propertyLocation;
    private BigDecimal roomTariffB2C;
    private BigDecimal roomTariffB2B;


}
