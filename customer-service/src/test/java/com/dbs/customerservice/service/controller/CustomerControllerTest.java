package com.dbs.customerservice.service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.dbs.customerservice.controller.CustomerController;
import com.dbs.customerservice.dto.request.CustomerRequest;
import com.dbs.customerservice.dto.response.CustomerResponse;
import com.dbs.customerservice.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void createCustomer_ShouldReturnCreated() throws Exception {

		CustomerRequest request = CustomerRequest.builder().firstName("Rahul").lastName("Mali").email("rahul@gmail.com")
				.mobileNumber("9876543210").build();

		CustomerResponse response = CustomerResponse.builder().customerId(1L).firstName("Rahul").lastName("Mali")
				.email("rahul@gmail.com").build();

		when(customerService.createCustomer(any(CustomerRequest.class))).thenReturn(response);

		mockMvc.perform(post("/api/customers")

				.contentType(MediaType.APPLICATION_JSON)

				.content(objectMapper.writeValueAsString(request)))

				.andExpect(status().isCreated())

				.andExpect(jsonPath("$.data.customerId").value(1))

				.andExpect(jsonPath("$.data.firstName").value("Rahul"));

	}

	@Test
	void getCustomerById_ShouldReturnCustomer() throws Exception {

		CustomerResponse response = CustomerResponse.builder().customerId(1L).firstName("Rahul")
				.email("rahul@gmail.com").build();

		when(customerService.getCustomerById(1L)).thenReturn(response);

		mockMvc.perform(get("/api/customers/1"))

				.andExpect(status().isOk())

				.andExpect(jsonPath("$.data.customerId").value(1))

				.andExpect(jsonPath("$.data.email").value("rahul@gmail.com"));

	}

	@Test
	void getAllCustomers_ShouldReturnList() throws Exception {

		CustomerResponse response = CustomerResponse.builder().customerId(1L).firstName("Rahul").build();

		when(customerService.getAllCustomers()).thenReturn(List.of(response));

		mockMvc.perform(get("/api/customers"))

				.andExpect(status().isOk())

				.andExpect(jsonPath("$.data.length()").value(1));

	}

	@Test
	void deleteCustomer_ShouldReturnSuccess() throws Exception {

		mockMvc.perform(delete("/api/customers/1"))

				.andExpect(status().isOk())

				.andExpect(jsonPath("$.message").value("Customer deleted successfully"));

	}

}