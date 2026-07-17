package com.dbs.investment_service.service;

import java.util.List;

import com.dbs.investment_service.dto.request.InvestmentProductRequest;
import com.dbs.investment_service.dto.response.InvestmentProductResponse;

public interface InvestmentProductService {

	InvestmentProductResponse createProduct(InvestmentProductRequest request);

	List<InvestmentProductResponse> getAllProducts();

	InvestmentProductResponse getProductById(Long id);

	InvestmentProductResponse updateProduct(Long id, InvestmentProductRequest request);

	void deleteProduct(Long id);

}