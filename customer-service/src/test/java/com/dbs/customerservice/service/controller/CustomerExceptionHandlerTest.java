package com.dbs.customerservice.service.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.dbs.customerservice.controller.CustomerController;
import com.dbs.customerservice.exception.CustomerNotFoundException;
import com.dbs.customerservice.service.CustomerService;



@WebMvcTest(CustomerController.class)
class CustomerExceptionHandlerTest {



    @Autowired
    private MockMvc mockMvc;



    @MockBean
    private CustomerService customerService;





    @Test
    void getCustomerById_WhenCustomerNotFound_ShouldReturn404()
            throws Exception {



        Long customerId = 999L;



        when(customerService.getCustomerById(customerId))
                .thenThrow(
                    new CustomerNotFoundException(
                        "Customer not found with id: " + customerId
                    )
                );



        mockMvc.perform(
                get("/api/customers/{id}", customerId)
        )


        .andExpect(status().isNotFound())


        .andExpect(
            jsonPath("$.message")
            .value("Customer not found with id: 999")
        );

    }


}