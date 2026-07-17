package com.dbs.investment_service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dbs.investment_service.dto.request.InvestmentProductRequest;
import com.dbs.investment_service.dto.response.InvestmentProductResponse;
import com.dbs.investment_service.entity.InvestmentProduct;
import com.dbs.investment_service.exception.ResourceNotFoundException;
import com.dbs.investment_service.repository.InvestmentProductRepository;
import com.dbs.investment_service.service.InvestmentProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestmentProductServiceImpl implements InvestmentProductService {

	private final InvestmentProductRepository repository;

	@Override
	public InvestmentProductResponse createProduct(InvestmentProductRequest request) {

		InvestmentProduct product = InvestmentProduct.builder()

				.productName(request.getProductName())

				.productType(request.getProductType())

				.category(request.getCategory())

				.riskLevel(request.getRiskLevel())

				.price(request.getPrice())

				.status(request.getStatus())

				.createdDate(LocalDateTime.now())

				.build();

		InvestmentProduct savedProduct = repository.save(product);

		return mapToResponse(savedProduct);
	}

	@Override
	public List<InvestmentProductResponse> getAllProducts() {

		return repository.findAll()

				.stream()

				.map(this::mapToResponse)

				.toList();

	}

	@Override
	public InvestmentProductResponse getProductById(Long id) {

		InvestmentProduct product = repository.findById(id)

				.orElseThrow(() -> new ResourceNotFoundException("Investment Product not found : " + id));

		return mapToResponse(product);

	}

	@Override
	public InvestmentProductResponse updateProduct(Long id, InvestmentProductRequest request) {

		InvestmentProduct product = repository.findById(id)

				.orElseThrow(() -> new ResourceNotFoundException("Investment Product not found : " + id));

		product.setProductName(request.getProductName());

		product.setProductType(request.getProductType());

		product.setCategory(request.getCategory());

		product.setRiskLevel(request.getRiskLevel());

		product.setPrice(request.getPrice());

		product.setStatus(request.getStatus());

		InvestmentProduct updated = repository.save(product);

		return mapToResponse(updated);

	}

	@Override
	public void deleteProduct(Long id) {

		InvestmentProduct product = repository.findById(id)

				.orElseThrow(() -> new ResourceNotFoundException("Investment Product not found : " + id));

		repository.delete(product);

	}

	private InvestmentProductResponse mapToResponse(InvestmentProduct product) {

		return InvestmentProductResponse.builder()

				.id(product.getId())

				.productName(product.getProductName())

				.productType(product.getProductType())

				.category(product.getCategory())

				.riskLevel(product.getRiskLevel())

				.price(product.getPrice())

				.status(product.getStatus())

				.createdDate(product.getCreatedDate())

				.build();

	}

}
