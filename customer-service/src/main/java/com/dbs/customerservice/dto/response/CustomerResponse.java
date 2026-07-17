package com.dbs.customerservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {

    private Long customerId;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;

    private String gender;

    private String dateOfBirth;

    private String panNumber;

    private String aadhaarNumber;

}