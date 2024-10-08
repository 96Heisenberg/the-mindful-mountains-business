package com.themindfulmountains.business.utils;

import com.themindfulmountains.business.payload.UserEnquiry;

public class EmailUtil {
    public static String populateSendEnquiryEmail(UserEnquiry userEnquiry) {
        return String.format(
                "New Enquiry Details:\n" +
                        "Name: %s\n" +
                        "Phone Number: %s\n" +
                        "Number of Heads: %d\n" +
                        "Number of Rooms: %d\n" +
                        "Start Location: %s\n" +
                        "End Location: %s\n" +
                        "Start DateTime: %s\n" +
                        "End DateTime: %s\n" +
                        "Opted for Newsletter: %b\n",
                userEnquiry.name(),
                userEnquiry.phoneNumber(),
                userEnquiry.noOfHeads(),
                userEnquiry.noOfRooms(),
                userEnquiry.startLocation(),
                userEnquiry.endLocation(),
                userEnquiry.startDateTime(),
                userEnquiry.endDateTime(),
                userEnquiry.optedForNewsLetter()
        );
    }
}
