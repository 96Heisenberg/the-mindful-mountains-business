package com.themindfulmountains.business.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyResponsePayload {
    private String propertyId;
    private String name;
    private String location;
    private String contactNo;
    private String emailId;
    private boolean active;
    private List<String> imageUrls;

}
