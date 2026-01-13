package com.themindfulmountains.business.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class RoomSnapshot {
    private UUID roomId;
    private String roomName;
    private BigDecimal b2bTariff;
    private BigDecimal b2cTariff;
}

