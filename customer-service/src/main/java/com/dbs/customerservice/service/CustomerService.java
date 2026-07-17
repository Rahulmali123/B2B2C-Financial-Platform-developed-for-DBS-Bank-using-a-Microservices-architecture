package com.dbs.customerservice.service;

import java.util.List;

import com.dbs.customerservice.dto.request.CustomerRequest;
import com.dbs.customerservice.dto.response.CustomerResponse;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);

    CustomerResponse getCustomerById(Long customerId);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse updateCustomer(Long customerId, CustomerRequest request);

    void deleteCustomer(Long customerId);

}