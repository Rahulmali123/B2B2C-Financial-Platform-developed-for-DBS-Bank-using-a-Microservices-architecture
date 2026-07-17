package com.dbs.portfolio_service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dbs.portfolio_service.dto.reponse.PortfolioResponse;
import com.dbs.portfolio_service.dto.request.PortfolioRequest;
import com.dbs.portfolio_service.entity.Portfolio;
import com.dbs.portfolio_service.exception.DuplicatePortfolioException;
import com.dbs.portfolio_service.exception.PortfolioNotFoundException;
import com.dbs.portfolio_service.mapper.PortfolioMapper;
import com.dbs.portfolio_service.repository.PortfolioRepository;
import com.dbs.portfolio_service.service.PortfolioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PortfolioServiceImpl implements PortfolioService 
{

	private final PortfolioRepository portfolioRepository;

	private final PortfolioMapper portfolioMapper;

	@Override
	public PortfolioResponse createPortfolio(PortfolioRequest request) {

		log.info("Creating portfolio : {}", request.getPortfolioName());

		if (portfolioRepository.existsByPortfolioName(request.getPortfolioName())) {
			throw new DuplicatePortfolioException("Portfolio already exists with name : " + request.getPortfolioName());
		}

		Portfolio portfolio = portfolioMapper.toEntity(request);

		portfolio.setCreatedAt(LocalDateTime.now());
		portfolio.setUpdatedAt(LocalDateTime.now());

		Portfolio savedPortfolio = portfolioRepository.save(portfolio);

		log.info("Portfolio created successfully with id : {}", savedPortfolio.getId());

		return portfolioMapper.toResponse(savedPortfolio);
	}

	@Override
	public PortfolioResponse getPortfolioById(Long id) {

		log.info("Fetching portfolio with id : {}", id);

		Portfolio portfolio = portfolioRepository.findById(id)
				.orElseThrow(() -> new PortfolioNotFoundException("Portfolio not found with id : " + id));

		log.info("Portfolio found : {}", portfolio.getPortfolioName());

		return portfolioMapper.toResponse(portfolio);
	}

	@Override
	public List<PortfolioResponse> getAllPortfolios() {

		log.info("Fetching all portfolios");

		List<Portfolio> portfolios = portfolioRepository.findAll();

		log.info("Total portfolios found : {}", portfolios.size());

		return portfolios.stream().map(portfolioMapper::toResponse).toList();
	}

	@Override
	public PortfolioResponse updatePortfolio(Long id, PortfolioRequest request) {

		log.info("Updating portfolio with id : {}", id);

		Portfolio portfolio = portfolioRepository.findById(id)
				.orElseThrow(() -> new PortfolioNotFoundException("Portfolio not found with id : " + id));

		portfolio.setCustomerId(request.getCustomerId());
		portfolio.setPortfolioName(request.getPortfolioName());
		portfolio.setPortfolioType(request.getPortfolioType());
		portfolio.setInvestedAmount(request.getInvestedAmount());
		portfolio.setCurrentValue(request.getCurrentValue());
		portfolio.setProfitLoss(request.getProfitLoss());
		portfolio.setRiskLevel(request.getRiskLevel());
		portfolio.setStatus(request.getStatus());
		portfolio.setUpdatedAt(LocalDateTime.now());

		Portfolio updatedPortfolio = portfolioRepository.save(portfolio);

		log.info("Portfolio updated successfully with id : {}", updatedPortfolio.getId());

		return portfolioMapper.toResponse(updatedPortfolio);
	}

	@Override
	public void deletePortfolio(Long id) {

		log.info("Deleting portfolio with id : {}", id);

		Portfolio portfolio = portfolioRepository.findById(id)
				.orElseThrow(() -> new PortfolioNotFoundException("Portfolio not found with id : " + id));

		portfolioRepository.delete(portfolio);

		log.info("Portfolio deleted successfully with id : {}", id);
	}

}
