package com.dbs.customerservice.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.dbs.customerservice.dto.request.CustomerRequest;
import com.dbs.customerservice.dto.response.CustomerResponse;
import com.dbs.customerservice.entity.Customer;


@Mapper(componentModel = "spring")
public interface CustomerMapper {


    CustomerResponse toResponse(Customer customer);


    Customer toEntity(CustomerRequest request);


    void updateCustomerFromRequest(
            CustomerRequest request,
            @MappingTarget Customer customer
    );

}
