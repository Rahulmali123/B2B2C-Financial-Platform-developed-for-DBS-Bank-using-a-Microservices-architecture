package com.dbs.investment_service.service;

import java.util.List;

import com.dbs.investment_service.dto.request.InvestmentOrderRequest;
import com.dbs.investment_service.dto.response.InvestmentOrderResponse;

public interface InvestmentOrderService {

	InvestmentOrderResponse buyInvestment(InvestmentOrderRequest request);

	InvestmentOrderResponse sellInvestment(InvestmentOrderRequest request);

	List<InvestmentOrderResponse> getOrdersByCustomer(Long customerId);
	
	InvestmentOrderResponse completeInvestment(Long orderId);

}