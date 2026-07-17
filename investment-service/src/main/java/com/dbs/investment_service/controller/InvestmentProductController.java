package com.dbs.investment_service.controller;

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

import com.dbs.investment_service.dto.request.InvestmentProductRequest;
import com.dbs.investment_service.dto.response.InvestmentProductResponse;
import com.dbs.investment_service.service.InvestmentProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class InvestmentProductController {

	private final InvestmentProductService service;

	// CREATE PRODUCT

	@PostMapping
	public ResponseEntity<InvestmentProductResponse> createProduct(
			@Valid @RequestBody InvestmentProductRequest request) {

		InvestmentProductResponse response = service.createProduct(request);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// GET ALL PRODUCTS

	@GetMapping
	public ResponseEntity<List<InvestmentProductResponse>> getAllProducts() {

		return ResponseEntity.ok(service.getAllProducts());

	}

	// GET PRODUCT BY ID

	@GetMapping("/{id}")
	public ResponseEntity<InvestmentProductResponse> getProductById(@PathVariable Long id) {

		return ResponseEntity.ok(service.getProductById(id));

	}

	// UPDATE PRODUCT

	@PutMapping("/{id}")
	public ResponseEntity<InvestmentProductResponse> updateProduct(@PathVariable Long id,
			@Valid @RequestBody InvestmentProductRequest request) {

		return ResponseEntity.ok(service.updateProduct(id, request));

	}

	// DELETE PRODUCT

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

		service.deleteProduct(id);

		return ResponseEntity.ok("Investment Product deleted successfully");

	}

}
