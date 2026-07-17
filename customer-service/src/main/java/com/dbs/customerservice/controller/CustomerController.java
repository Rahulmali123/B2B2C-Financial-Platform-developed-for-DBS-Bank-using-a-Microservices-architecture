package com.dbs.customerservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.customerservice.dto.request.CustomerRequest;
import com.dbs.customerservice.dto.response.ApiResponse;
import com.dbs.customerservice.dto.response.CustomerResponse;
import com.dbs.customerservice.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
@Tag(
        name = "Customer API",
        description = "Customer management operations"
)
public class CustomerController {


    private final CustomerService customerService;



    // Create Customer

    @PostMapping
    @Operation(
            summary = "Create Customer",
            description = "Creates a new customer"
    )
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(
            @Valid @RequestBody CustomerRequest request) {


        log.info(
                "Create customer request received with email: {}",
                request.getEmail()
        );


        CustomerResponse response =
                customerService.createCustomer(request);


        log.info(
                "Customer created successfully with id: {}",
                response.getCustomerId()
        );


        return new ResponseEntity<>(
                ApiResponse.success(
                        "Customer created successfully",
                        response
                ),
                HttpStatus.CREATED
        );
    }




    // Get Customer By Id

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Customer By ID",
            description = "Fetch customer details using customer id"
    )
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(
            @PathVariable Long id) {


        log.info(
                "Fetching customer with id: {}",
                id
        );


        CustomerResponse response =
                customerService.getCustomerById(id);


        log.info(
                "Customer fetched successfully id: {}",
                id
        );


        return ResponseEntity.ok(
                ApiResponse.success(
                        "Customer fetched successfully",
                        response
                )
        );
    }




    // Get All Customers

    @GetMapping
    @Operation(
            summary = "Get All Customers",
            description = "Fetch all customer details"
    )
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomers() {


        log.info("Fetching all customers");


        List<CustomerResponse> response =
                customerService.getAllCustomers();


        log.info(
                "Total customers found: {}",
                response.size()
        );


        return ResponseEntity.ok(
                ApiResponse.success(
                        "Customers fetched successfully",
                        response
                )
        );
    }





    // Update Customer

    @PutMapping("/{id}")
    @Operation(
            summary = "Update Customer",
            description = "Update existing customer details"
    )
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest request) {


        log.info(
                "Update customer request received id: {}",
                id
        );


        CustomerResponse response =
                customerService.updateCustomer(id, request);


        log.info(
                "Customer updated successfully id: {}",
                id
        );


        return ResponseEntity.ok(
                ApiResponse.success(
                        "Customer updated successfully",
                        response
                )
        );
    }





    // Delete Customer

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Customer",
            description = "Delete customer using customer id"
    )
    public ResponseEntity<ApiResponse<String>> deleteCustomer(
            @PathVariable Long id) {


        log.warn(
                "Delete request received for customer id: {}",
                id
        );


        customerService.deleteCustomer(id);


        log.info(
                "Customer deleted successfully id: {}",
                id
        );


        return ResponseEntity.ok(
                ApiResponse.success(
                        "Customer deleted successfully",
                        null
                )
        );
    }

}