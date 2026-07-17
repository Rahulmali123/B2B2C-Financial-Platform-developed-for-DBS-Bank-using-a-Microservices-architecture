package com.dbs.portfolio_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.portfolio_service.dto.reponse.PortfolioResponse;
import com.dbs.portfolio_service.dto.request.PortfolioRequest;
import com.dbs.portfolio_service.service.PortfolioService;
import com.dbs.portfolio_service.util.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/portfolios")
@RequiredArgsConstructor
@Validated
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping
    public ResponseEntity<ApiResponse<PortfolioResponse>> createPortfolio(
            @Valid @RequestBody PortfolioRequest request) {

        PortfolioResponse response = portfolioService.createPortfolio(request);

        return new ResponseEntity<>(
                ApiResponse.success("Portfolio created successfully.", response),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PortfolioResponse>> getPortfolioById(
            @PathVariable Long id) {

        PortfolioResponse response = portfolioService.getPortfolioById(id);

        return ResponseEntity.ok(
                ApiResponse.success("Portfolio fetched successfully.", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PortfolioResponse>>> getAllPortfolios() {

        List<PortfolioResponse> response = portfolioService.getAllPortfolios();

        return ResponseEntity.ok(
                ApiResponse.success("Portfolios fetched successfully.", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PortfolioResponse>> updatePortfolio(
            @PathVariable Long id,
            @Valid @RequestBody PortfolioRequest request) {

        PortfolioResponse response = portfolioService.updatePortfolio(id, request);

        return ResponseEntity.ok(
                ApiResponse.success("Portfolio updated successfully.", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletePortfolio(
            @PathVariable Long id) {

        portfolioService.deletePortfolio(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Portfolio deleted successfully.",
                        "Portfolio deleted successfully."));
    }

}