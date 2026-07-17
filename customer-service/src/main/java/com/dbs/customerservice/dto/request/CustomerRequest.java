package com.dbs.customerservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class CustomerRequest {

    @NotBlank(message = "First Name is required")
    private String firstName;

    private String lastName;

    @Email(message = "Invalid Email")
    private String email;

    private String mobileNumber;

    private String gender;

    private String dateOfBirth;

    private String panNumber;

    private String aadhaarNumber;

}