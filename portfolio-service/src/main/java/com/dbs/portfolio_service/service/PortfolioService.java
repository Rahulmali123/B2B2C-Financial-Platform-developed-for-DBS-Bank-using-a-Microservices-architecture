package com.dbs.portfolio_service.service;

import java.util.List;

import com.dbs.portfolio_service.dto.reponse.PortfolioResponse;
import com.dbs.portfolio_service.dto.request.PortfolioRequest;

public interface PortfolioService {

    PortfolioResponse createPortfolio(PortfolioRequest request);

    PortfolioResponse getPortfolioById(Long id);

    List<PortfolioResponse> getAllPortfolios();

    PortfolioResponse updatePortfolio(Long id, PortfolioRequest request);

    void deletePortfolio(Long id);

}
