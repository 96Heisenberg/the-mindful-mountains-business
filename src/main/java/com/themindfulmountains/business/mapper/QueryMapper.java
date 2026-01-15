package com.themindfulmountains.business.mapper;

import com.themindfulmountains.business.dto.response.*;
import com.themindfulmountains.business.model.*;

import java.util.stream.Collectors;

public class QueryMapper {

    public static QueryResponse toResponse(QueryItinerary query) {

        return QueryResponse.builder()
                .queryId(query.getItineraryId())
                .noOfAdults(query.getNoOfAdults())
                .noOfChilds(query.getNoOfChilds())
                .b2bTotalItineraryCost(query.getB2bTotalItineraryCost())
                .b2cTotalItineraryCost(query.getB2cTotalItineraryCost())
                .queryPdfLink(query.getQueryPdfLink())
                .customer(toCustomer(query.getCustomer()))
                .dayItineraries(
                        query.getDayItineraries()
                                .stream()
                                .map(QueryMapper::toDayItinerary)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private static CustomerSummaryResponse toCustomer(Customer c) {
        return CustomerSummaryResponse.builder()
                .customerId(c.getCustomerId())
                .name(c.getName())
                .email(c.getEmail())
                .contactNo(c.getContactNo())
                .build();
    }

    private static DayItineraryResponse toDayItinerary(DayItinerary d) {
        return DayItineraryResponse.builder()
                .dayId(d.getDayId())
                .itineraryDay(d.getItineraryDay())
                .roomsOpted(d.isRoomsOpted())
                .transportOpted(d.isTransportOpted())
                .rooms(
                        d.getRooms()
                                .stream()
                                .map(r -> RoomSummaryResponse.builder()
                                        .propertyId(r.getProperty().getPropertyId())
                                        .roomId(r.getRoomId())
                                        .roomName(r.getRoomName())
                                        .roomTariffB2C(r.getRoomTariffB2C())
                                        .roomTariffB2B(r.getRoomTariffB2B())
                                        .build()
                                )
                                .collect(Collectors.toList())
                )
                .transports(
                        d.getTransports()
                                .stream()
                                .map(t -> TransportResponse.builder()
                                        .name(t.getName())
                                        .description(t.getDescription())
                                        .contactNo(t.getContactNo())
                                        .bookingVia(t.getBookingVia())
                                        .b2cTariff(t.getB2cTariff())
                                        .b2bTariff(t.getB2bTariff())
                                        .build()
                                )
                                .collect(Collectors.toList())
                )
                .build();
    }
}