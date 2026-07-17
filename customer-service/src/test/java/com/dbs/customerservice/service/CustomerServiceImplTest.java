package com.dbs.customerservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dbs.customerservice.dto.request.CustomerRequest;
import com.dbs.customerservice.dto.response.CustomerResponse;
import com.dbs.customerservice.entity.Customer;
import com.dbs.customerservice.exception.CustomerNotFoundException;
import com.dbs.customerservice.mapper.CustomerMapper;
import com.dbs.customerservice.repository.CustomerRepository;
import com.dbs.customerservice.service.impl.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private CustomerMapper customerMapper;

	@InjectMocks
	private CustomerServiceImpl customerService;

	private Customer customer;

	private CustomerRequest request;

	private CustomerResponse response;

	@BeforeEach
	void setup() {

		customer = Customer.builder().customerId(1L).firstName("Rahul").lastName("Mali").email("rahul@gmail.com")
				.mobileNumber("9876543210").build();

		request = CustomerRequest.builder().firstName("Rahul").lastName("Mali").email("rahul@gmail.com")
				.mobileNumber("9876543210").build();

		response = CustomerResponse.builder().customerId(1L).firstName("Rahul").lastName("Mali")
				.email("rahul@gmail.com").build();

	}

	// CREATE CUSTOMER TEST

	@Test
	void createCustomer_ShouldReturnCustomerResponse() {

		when(customerMapper.toEntity(request)).thenReturn(customer);

		when(customerRepository.save(customer)).thenReturn(customer);

		when(customerMapper.toResponse(customer)).thenReturn(response);

		CustomerResponse result = customerService.createCustomer(request);

		assertNotNull(result);

		assertEquals("Rahul", result.getFirstName());

		verify(customerRepository).save(customer);

	}

	// GET CUSTOMER BY ID TEST

	@Test
	void getCustomerById_ShouldReturnCustomer() {

		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

		when(customerMapper.toResponse(customer)).thenReturn(response);

		CustomerResponse result = customerService.getCustomerById(1L);

		assertEquals(1L, result.getCustomerId());

		verify(customerRepository).findById(1L);

	}

	// CUSTOMER NOT FOUND TEST

	@Test
	void getCustomerById_ShouldThrowException() {

		when(customerRepository.findById(100L)).thenReturn(Optional.empty());

		assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(100L));

	}

	// DELETE CUSTOMER TEST

	@Test
	void deleteCustomer_ShouldDeleteCustomer() {

		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

		customerService.deleteCustomer(1L);

		verify(customerRepository).delete(customer);

	}

}