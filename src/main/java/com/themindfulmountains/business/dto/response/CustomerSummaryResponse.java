package com.themindfulmountains.business.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSummaryResponse {

    private String customerId;
    private String name;
    private String email;
    private String contactNo;
}