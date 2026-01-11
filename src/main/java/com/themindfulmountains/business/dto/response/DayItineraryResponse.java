package com.themindfulmountains.business.dto.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayItineraryResponse {

    private String dayId;

    private Date itineraryDay;

    private boolean roomsOpted;
    private boolean transportOpted;

    private List<RoomSummaryResponse> rooms;
    private List<TransportResponse> transports;
}
