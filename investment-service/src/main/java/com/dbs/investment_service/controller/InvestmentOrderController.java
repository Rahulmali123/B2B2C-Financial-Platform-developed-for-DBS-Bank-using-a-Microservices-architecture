package com.dbs.investment_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.investment_service.dto.request.InvestmentOrderRequest;
import com.dbs.investment_service.dto.response.InvestmentOrderResponse;
import com.dbs.investment_service.service.InvestmentOrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/investments")
@RequiredArgsConstructor
public class InvestmentOrderController {

	private final InvestmentOrderService service;

	/*
	 * Customer Buy Investment
	 */
	@PostMapping("/buy")
	public ResponseEntity<InvestmentOrderResponse> buyInvestment(@Valid @RequestBody InvestmentOrderRequest request) {

		InvestmentOrderResponse response = service.buyInvestment(request);

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	/*
	 * Customer Sell Investment
	 */
	@PostMapping("/sell")
	public ResponseEntity<InvestmentOrderResponse> sellInvestment(@Valid @RequestBody InvestmentOrderRequest request) {

		InvestmentOrderResponse response = service.sellInvestment(request);

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	/*
	 * Customer Investment History
	 */
	@GetMapping("/orders/{customerId}")
	public ResponseEntity<List<InvestmentOrderResponse>> getOrders(@PathVariable Long customerId) {

		return ResponseEntity.ok(service.getOrdersByCustomer(customerId));

	}

	@PutMapping("/{orderId}/complete")
	public ResponseEntity<InvestmentOrderResponse> completeInvestment(@PathVariable Long orderId) {

		return ResponseEntity.ok(service.completeInvestment(orderId));
	}

}
