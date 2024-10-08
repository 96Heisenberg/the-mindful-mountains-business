package com.themindfulmountains.business.payload;

import com.themindfulmountains.business.model.ServiceCategories;

import java.time.LocalDateTime;
import java.util.List;

public record UserEnquiry(
        String name,
        String phoneNumber,
        Integer noOfHeads,
        Integer noOfRooms,
        LocalDateTime startLocation,
        LocalDateTime endLocation,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        List<ServiceCategories> requestedServiceCategories,
        Boolean optedForNewsLetter
) {}