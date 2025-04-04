package com.themindfulmountains.business.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PropertyRequestPayload {
    private String name;
    private String location;
    private String contactNo;
    private String emailId;
    private List<String> imageUrls;
    private boolean active;
}
