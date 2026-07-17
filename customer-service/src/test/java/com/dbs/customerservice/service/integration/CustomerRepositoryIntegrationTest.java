package com.dbs.customerservice.service.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.dbs.customerservice.entity.Customer;
import com.dbs.customerservice.repository.CustomerRepository;

@Testcontainers
@DataJpaTest
class CustomerRepositoryIntegrationTest {

	@Container
	static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0").withDatabaseName("customer_db")
			.withUsername("root").withPassword("password");

	@DynamicPropertySource
	static void configureDatabase(DynamicPropertyRegistry registry) {

		registry.add("spring.datasource.url", mysql::getJdbcUrl);

		registry.add("spring.datasource.username", mysql::getUsername);

		registry.add("spring.datasource.password", mysql::getPassword);

	}

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	void saveCustomer_ShouldStoreInDatabase() {

		Customer customer = Customer.builder()

				.firstName("Rahul").lastName("Mali").email("rahul@gmail.com").mobileNumber("9876543210")

				.build();

		Customer savedCustomer = customerRepository.save(customer);

		assertThat(savedCustomer.getCustomerId()).isNotNull();

		Customer dbCustomer = customerRepository.findById(savedCustomer.getCustomerId()).get();

		assertThat(dbCustomer.getEmail()).isEqualTo("rahul@gmail.com");

	}

}
