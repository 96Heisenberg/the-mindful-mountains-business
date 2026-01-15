package com.themindfulmountains.business.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomSummaryResponse {
    private String propertyId;
    private String roomId;
    private String roomName;
    private BigDecimal roomTariffB2C;
    private BigDecimal roomTariffB2B;

}
