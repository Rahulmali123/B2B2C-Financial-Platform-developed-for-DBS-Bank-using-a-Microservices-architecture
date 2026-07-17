package com.dbs.customerservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dbs.customerservice.dto.request.CustomerRequest;
import com.dbs.customerservice.dto.response.CustomerResponse;
import com.dbs.customerservice.entity.Customer;
import com.dbs.customerservice.exception.CustomerNotFoundException;
import com.dbs.customerservice.mapper.CustomerMapper;
import com.dbs.customerservice.repository.CustomerRepository;
import com.dbs.customerservice.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	private final CustomerMapper customerMapper;

	@Override
	public CustomerResponse createCustomer(CustomerRequest request) {

		log.info("Creating customer with email: {}", request.getEmail());

		Customer customer = customerMapper.toEntity(request);

		Customer savedCustomer = customerRepository.save(customer);

		log.info("Customer created successfully with id: {}", savedCustomer.getCustomerId());

		return customerMapper.toResponse(savedCustomer);
	}

	@Override
	public CustomerResponse getCustomerById(Long customerId) {

		log.info("Fetching customer by id: {}", customerId);

		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> {

			log.error("Customer not found with id: {}", customerId);

			return new CustomerNotFoundException("Customer not found with id: " + customerId);

		});

		return customerMapper.toResponse(customer);
	}

	@Override
	public List<CustomerResponse> getAllCustomers() {

		log.info("Fetching all customers");

		List<CustomerResponse> customers = customerRepository.findAll().stream().map(customerMapper::toResponse)
				.toList();

		log.info("Total customers fetched: {}", customers.size());

		return customers;
	}

	@Override
	public CustomerResponse updateCustomer(Long customerId, CustomerRequest request) {

		log.info("Updating customer id: {}", customerId);

		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> {

			log.error("Customer not found for update id: {}", customerId);

			return new CustomerNotFoundException("Customer not found with id: " + customerId);

		});

		customerMapper.updateCustomerFromRequest(request, customer);

		Customer updatedCustomer = customerRepository.save(customer);

		log.info("Customer updated successfully id: {}", updatedCustomer.getCustomerId());

		return customerMapper.toResponse(updatedCustomer);
	}

	@Override
	public void deleteCustomer(Long customerId) {

		log.warn("Delete customer request received id: {}", customerId);

		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> {

			log.error("Customer not found for deletion id: {}", customerId);

			return new CustomerNotFoundException("Customer not found with id: " + customerId);

		});

		customerRepository.delete(customer);

		log.info("Customer deleted successfully id: {}", customerId);
	}

}